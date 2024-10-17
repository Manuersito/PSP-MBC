package Ej1;

public class Hilos extends Thread {
    public void run(){
        System.out.println("Hola mundo! " + Thread.currentThread().getName());
    }
}

