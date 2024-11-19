package ACT2_7;

public class Act2_7Thread extends Thread {
    private static int contador = 0; //variable compartida

    public void run() {
        for (int i = 0; i < 5000; i++) {
            contador++;
        }
    }

    public static void main(String[] args) {
        // Crear e iniciar los hilos de manera independiente
        Act2_7Thread hilo1 = new Act2_7Thread();
        Act2_7Thread hilo2 = new Act2_7Thread();
        Act2_7Thread hilo3 = new Act2_7Thread();
        Act2_7Thread hilo4 = new Act2_7Thread();
        Act2_7Thread hilo5 = new Act2_7Thread();

        hilo1.start();
        hilo2.start();
        hilo3.start();
        hilo4.start();
        hilo5.start();

        // No usamos join(), por lo que los hilos se ejecutan de forma independiente
        System.out.println("Resultado sin sincronización: " + contador);
    }
}
