package Tema2;

public class Persona extends Thread {
//inicializamos para que el saldo inicial sea 40
    private Saldo saldo = new Saldo(40);


    //Recibe por parametros el nombre para saber quien es el que hace la operacion

    public Persona(String nombre) {
    super(nombre);
    }

    int cantidad = (int) (Math.random()*500+1);

//ponemos las operaciones que va a hacer la persona con un sleep para que se vea claro
    @Override
    public void run() {
        try {

            for (int i = 0; i < 2; i++) {
                cantidad = (int) (Math.random()*500+1);
                saldo.añadirCantidad(getName(),cantidad);

                Thread.sleep(1000);

                cantidad = (int) (Math.random()*500+1);
                saldo.restarCantidad(getName(),cantidad);
                Thread.sleep(1000);

            }


        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
