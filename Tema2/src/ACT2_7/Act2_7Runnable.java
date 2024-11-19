package ACT2_7;

public class Act2_7Runnable implements Runnable {
    private static int contador = 0; // Variable compartida

    @Override
    public void run() {
        for (int i = 0; i < 5000; i++) {
            synchronized (Act2_7Runnable.class) { // Bloque sincronizado
                contador++;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        // Crear e iniciar cinco hilos independientes sin usar un array
        Thread hilo1 = new Thread(new Act2_7Runnable());
        Thread hilo2 = new Thread(new Act2_7Runnable());
        Thread hilo3 = new Thread(new Act2_7Runnable());
        Thread hilo4 = new Thread(new Act2_7Runnable());
        Thread hilo5 = new Thread(new Act2_7Runnable());

        hilo1.start();
        hilo2.start();
        hilo3.start();
        hilo4.start();
        hilo5.start();

        // Esperar a que todos los hilos terminen
        hilo1.join();
        hilo2.join();
        hilo3.join();
        hilo4.join();
        hilo5.join();

        // Mostrar el resultado final
        System.out.println("Resultado con sincronización: " + contador);
    }
}
