package ACT2_8;

public class Hilo extends Thread {
    private Saldo saldo;
    private int cantidad;

    public Hilo(String nombre, Saldo saldo, int cantidad) {
        super(nombre);
        this.saldo = saldo;
        this.cantidad = cantidad;
    }

    @Override
    public void run() {
        saldo.añadirCantidad(getName(), cantidad);
    }
}

