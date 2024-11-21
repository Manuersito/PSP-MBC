package ACT2_10;

public class Cola {
    private int numero;
    private boolean disponible = false;

    public int get() {
        if (disponible) {
            disponible = false;
            return numero;
        }
        return -1; //significa que esta vacia
    }

    public void put(int valor){
        numero = valor;
        disponible = true;
    }

}
