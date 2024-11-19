package Ej11;

public class Main {
    public static void main(String[] args) {
        int numeroJugadores = 3; // Puedes cambiar el número de jugadores
        Arbitro arbitro = new Arbitro(numeroJugadores);

        Jugador[] jugadores = new Jugador[numeroJugadores];
        for (int i = 0; i < numeroJugadores; i++) {
            jugadores[i] = new Jugador(i + 1, arbitro);
            jugadores[i].start();
        }

        // Espera a que los hilos terminen
        for (Jugador jugador : jugadores) {
            try {
                jugador.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}

