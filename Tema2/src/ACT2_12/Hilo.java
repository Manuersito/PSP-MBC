package ACT2_12;

public class Hilo extends Thread {
    private Main applet;
    private int x = 1; // Posición inicial en x
    private int direccion = 1; // 1: derecha, -1: izquierda
    private boolean moviendo = true; // Controla si el hilo está corriendo
    private boolean detener = false; // Controla si el hilo debe detenerse

    public Hilo(Main applet) {
        this.applet = applet;
    }

    @Override
    public void run() {
        while (!detener) {
            if (moviendo) {
                // Mueve la letra en la dirección actual
                synchronized (this) {
                    x += direccion;
                    // Rebote en los límites
                    if (x >= applet.getLimite()) {
                        direccion = -1; // Cambiar dirección a izquierda
                    }
                    if (x <= 1) {
                        direccion = 1; // Cambiar dirección a derecha
                    }
                    applet.setX(x); // Actualizar posición de la letra
                }
            }

            try {
                Thread.sleep(10); // Pausa para hacer visible el movimiento
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    // Cambia el estado para pausar o reanudar el hilo
    public void cambioEstado() {
        moviendo = !moviendo;
    }

    // Verifica si el hilo está en movimiento
    public boolean isMoviendo() {
        return moviendo;
    }

    // Detiene el hilo
    public void detenerHilo() {
        this.detener = true;
    }
}
