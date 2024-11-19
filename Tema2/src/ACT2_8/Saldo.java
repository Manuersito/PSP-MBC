package ACT2_8;

import static java.lang.Thread.sleep;

public class Saldo {
    private int saldo;

    public Saldo(int saldoInicial) {
        this.saldo = saldoInicial;
    }

    public int getSaldo() {
        return saldo;
    }

    //se sincronizan los hilos para que no se pueda añadir saldo a la vez o de forma desordenada
    //de esta forma hasta que un hilo no termine no puede iniciar la accion otro
    // para priobar la parte del ejercicio sin sincronizar borra el synchronized
    public synchronized void añadirCantidad(String nombre, int cantidad) {
        int saldoInicial = saldo;
        System.out.println(nombre + " añade: " + cantidad);
        System.out.println("Saldo inicial: " + saldoInicial);
        saldo += cantidad;
        System.out.println("Saldo final: " + saldo);
        try {
            Thread.sleep((int) (Math.random() * 2000 + 1000)); // Sleep aleatorio
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

