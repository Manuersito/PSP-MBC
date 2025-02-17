package Tema2;

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


    public synchronized void añadirCantidad(String nombre, int cantidad) {
        int saldoInicial = saldo;
        //se pone el if para controlar que no se pase de 700 ya que es la cantidad maxima
        if (saldo + cantidad <= 700 ) {
            //se puestra por pantalla cual es la cantidad y saldo que tienes y la operacion
            System.out.println(nombre + " añade: " + cantidad);
            System.out.println("Saldo inicial: " + saldoInicial);
            saldo += cantidad;
            System.out.println("Saldo final: " + saldo);

        }else{
            System.out.println("No se puede realizar la operacion");
        }

    }


    public synchronized void restarCantidad(String nombre, int cantidad) {

        int saldoInicial = saldo;

        //ponemos el if para mirar que al hacer el reintegro no pase de 0 ya que no se
        //puede numeros negativos
        if (saldo - cantidad >= 0 ) {
            //se puestra por pantalla cual es la cantidad y saldo que tienes y la operacion

            System.out.println(nombre + " retira: " + cantidad);
            System.out.println("Saldo inicial: " + saldoInicial);
            saldo -= cantidad;
            System.out.println("Saldo final: " + saldo);

        }else{
            System.out.println("No se puede realizar la operacion");
        }

    }



}
