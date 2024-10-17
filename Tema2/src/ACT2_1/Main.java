package ACT2_1;
//Si se muestra de forma ordenada por que al usar sleep paramos durante el tiempo indicado
//el hilo y se ejecuta el otro hilo pero hay veces que se le da tiempo a repetirse el hilo
public class Main {
    public static void main(String[] args) {
        HiloTIC h1 = new HiloTIC();
        HiloTAC h2 = new HiloTAC();
        h1.start();
        h2.start();
    }
}