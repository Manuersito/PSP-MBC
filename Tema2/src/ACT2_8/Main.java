package ACT2_8;

public class Main {
    public static void main(String[] args) {
        Saldo saldo = new Saldo(100); // Valor inicial del saldo
        System.out.println("Saldo inicial: " + saldo.getSaldo());

        // Crear y lanzar hilos
        Hilo hilo1 = new Hilo("Hilo 1", saldo, 50);
        Hilo hilo2 = new Hilo("Hilo 2", saldo, 30);
        Hilo hilo3 = new Hilo("Hilo 3", saldo, 20);

        hilo1.start();
        hilo2.start();
        hilo3.start();

        try {
            hilo1.join();
            hilo2.join();
            hilo3.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("Saldo final: " + saldo.getSaldo());
    }
}

