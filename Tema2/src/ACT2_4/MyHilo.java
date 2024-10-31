package ACT2_4;

import java.util.Scanner;

public class MyHilo extends Thread {

    private SolicitaSuspender suspender = new SolicitaSuspender();
    public int contador = 0;
    boolean running = true;


    public void Suspende(){
        suspender.set(true);
    }
    public void reanuda() {
        suspender.set(false);
    }

    public void termina() {
        running = false;
    }

    public int getContador(int contadores) {
        contadores++;
        return contadores;
    }

    public void run() {

        try {
            while (running) {
                suspender.esperandoParaReanudar();
                Thread.sleep(1000);
                System.out.println(contador=getContador(contador));
            }
            System.out.println("Contador: "+contador);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }



    }


}
