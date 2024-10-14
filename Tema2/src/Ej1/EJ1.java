package Ej1;

public class EJ1 {

    public static void main(String[] args) {
        Hilos hilo = new Hilos();
        Hilos hilo2 = new Hilos();
        Hilos hilo3 = new Hilos();
        Hilos hilo4 = new Hilos();
        Hilos hilo5 = new Hilos();

        hilo.start();
        hilo2.start();
        hilo3.start();
        hilo4.start();
        hilo5.start();

    }
}
