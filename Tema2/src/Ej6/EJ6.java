package Ej6;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class EJ6 extends Application {

    private ProgressBar progressBar1, progressBar2, progressBar3;
    private Slider slider1, slider2, slider3;
    private Label labelProgreso1, labelProgreso2, labelProgreso3;
    private Button btnIniciar;
    private boolean carreraEnCurso = false;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("CarreraHilos.fxml"));
        primaryStage.setTitle("Carrera de Hilos");
        primaryStage.setScene(new Scene(root, 600, 300));
        primaryStage.show();
    }

    private void comenzarCarrera() {
        if (!carreraEnCurso) {
            carreraEnCurso = true;
            btnIniciar.setDisable(true);

            // Reiniciar el progreso de las barras
            progressBar1.setProgress(0);
            progressBar2.setProgress(0);
            progressBar3.setProgress(0);

            labelProgreso1.setText("Progreso: 0%");
            labelProgreso2.setText("Progreso: 0%");
            labelProgreso3.setText("Progreso: 0%");

            // Crear e iniciar los hilos con la prioridad seleccionada en cada deslizador
            HiloCarrera hilo1 = new HiloCarrera(progressBar1, labelProgreso1, (int) slider1.getValue(), 1);
            HiloCarrera hilo2 = new HiloCarrera(progressBar2, labelProgreso2, (int) slider2.getValue(), 2);
            HiloCarrera hilo3 = new HiloCarrera(progressBar3, labelProgreso3, (int) slider3.getValue(), 3);

            hilo1.start();
            hilo2.start();
            hilo3.start();
        }
    }

    // Clase interna para los hilos de carrera
    private class HiloCarrera extends Thread {
        private final ProgressBar progressBar;
        private final Label labelProgreso;
        private final int prioridad;
        private final int numeroHilo;
        private double progreso = 0;

        public HiloCarrera(ProgressBar progressBar, Label labelProgreso, int prioridad, int numeroHilo) {
            this.progressBar = progressBar;
            this.labelProgreso = labelProgreso;
            this.prioridad = prioridad;
            this.numeroHilo = numeroHilo;
            setPriority(prioridad);
        }

        @Override
        public void run() {
            while (progreso < 1 && carreraEnCurso) {
                progreso += Math.random() * 0.1;

                // Actualizar el progreso de la barra y la etiqueta en el hilo de JavaFX
                double finalProgreso = progreso;
                javafx.application.Platform.runLater(() -> {
                    progressBar.setProgress(finalProgreso);
                    labelProgreso.setText("Progreso: " + (int) (finalProgreso * 100) + "%");
                });

                try {
                    Thread.sleep((long) (Math.random() * 100) + 50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            // Verificar si es el primer hilo en terminar la carrera
            if (progreso >= 1 && carreraEnCurso) {
                carreraEnCurso = false;
                javafx.application.Platform.runLater(() -> {
                    btnIniciar.setDisable(false);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Resultado de la Carrera");
                    alert.setHeaderText(null);
                    alert.setContentText("¡Hilo ganador: HILO " + numeroHilo + "!");
                    alert.showAndWait();
                });
            }
        }
    }
}
