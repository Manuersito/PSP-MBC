package Ej11;

import java.util.Random;

public class Arbitro {
    private int numeroAAdivinar;
    private int turno;
    private boolean juegoTerminado;
    private int totalJugadores;

    public Arbitro(int totalJugadores) {
        this.totalJugadores = totalJugadores;
        this.numeroAAdivinar = 1 + (int)(10 * Math.random());
        this.turno = 1;
        this.juegoTerminado = false;
        System.out.println("NÚMERO A ADIVINAR: " + numeroAAdivinar);
    }

    public synchronized int getTurno() {
        return turno;
    }

    public synchronized boolean isJuegoTerminado() {
        return juegoTerminado;
    }

    public synchronized void comprobarJugada(int jugadorId, int numero) {
        if (juegoTerminado) {
            return;
        }

        System.out.println("Jugador" + jugadorId + " dice: " + numero);

        if (numero == numeroAAdivinar) {
            System.out.println("Jugador " + jugadorId + " gana");
            juegoTerminado = true;
        } else {
            turno = (turno % totalJugadores) + 1;
            System.out.println("Le toca a Jug" + turno);
        }
    }
}
