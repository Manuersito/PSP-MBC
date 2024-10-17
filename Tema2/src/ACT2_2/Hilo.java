package ACT2_2;
//implementamos runnable y ponemos que al iniciar el hilo escriba
//hola mundo y el nombre del hilo
public class Hilo implements Runnable{
    @Override
    public void run(){

        System.out.println("Hola mundo! " + Thread.currentThread().getId());
    }
}

