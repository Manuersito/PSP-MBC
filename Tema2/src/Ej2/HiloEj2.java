package Ej2;

public class HiloEj2 implements Runnable{
    private String cad;

    public HiloEj2(String cadena) {
        this.cad = cadena;
    }

    public HiloEj2() {
    }


    public String getCad() {
        return cad;
    }

    @Override
    public void run() {
        System.out.println("Hola mundo!" + getCad() +Thread.currentThread().getId());
    }

}
