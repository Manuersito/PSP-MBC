package ACT2_4;

import java.util.Scanner;

public class Act2_4 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s;
        MyHilo myHilo = new MyHilo();
        myHilo.start();


        while (true){
            System.out.println("Pulse * Para finalizar, S para Suspender y R para reanudar");
            s = in.nextLine();
            if (s.equals("*")){
                myHilo.termina();
            }else if (s.equals("R")){
                myHilo.reanuda();
            }else if (s.equals("S")){
                myHilo.Suspende();
            }
        }

    }

}
