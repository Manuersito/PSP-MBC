package ACT2_6;

public class HiloPrioridad2 extends Thread{
    HiloPrioridad2 (String nom){
        this.setName(nom);
    }

    public void run(){
        System.out.println("Ejecutando ["+this.getName()+"]");
        for (int i = 1; i < 5; i++) {
            System.out.println("\t("+getName()+": "+i+")");
        }
    }

}
