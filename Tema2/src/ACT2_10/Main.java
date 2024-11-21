package ACT2_10;

public class Main {
    public static void main(String[] args) {
        Cola cola = new Cola();
        Productor p = new Productor(cola,3);
        Consumidor c = new Consumidor(cola,0);
        p.start();
        c.start();

    }
}
