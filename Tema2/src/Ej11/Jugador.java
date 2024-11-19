package Ej11;

import java.util.Random;

public class Jugador extends Thread {
    private int id;
    private Arbitro arbitro;
    private Random random;

    public Jugador(int id, Arbitro arbitro) {
        this.id = id;
        this.arbitro = arbitro;
        this.random = new Random();
    }

    @Override
    public void run() {
        while (!arbitro.isJuegoTerminado()) {
            synchronized (arbitro) {
                if (arbitro.getTurno() == id && !arbitro.isJuegoTerminado()) {
                    int numero = 1 + random.nextInt(10);
                    arbitro.comprobarJugada(id, numero);
                }
            }

            try {
                Thread.sleep(100); // Pausa breve para simular el tiempo entre turnos
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
