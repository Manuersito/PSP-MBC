package ACT2_2;

import Ej1.Hilos;

public class Act2_2 {

    public static void main(String[] args) {
    //instanciamos lo hilos con thread ya que no usamos un extends thread
    //en la clase hilo y los iniciamos
    new Thread(new Hilo()).start();
    new Thread(new Hilo()).start();
    new Thread(new Hilo()).start();
    new Thread(new Hilo()).start();
    new Thread(new Hilo()).start();



    }
}
