package Actividad3_1;
import java.net.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        InetAddress ip = null;
        String ip2 = null;
        System.out.println("MOSTRAR DATOS RED");

        try {

            System.out.println("Ingrese el IP del servidor");
            ip2 = sc.nextLine();
            ip = InetAddress.getByName(String.valueOf(ip2));
        }catch (UnknownHostException e1){e1.printStackTrace();}
        pruebaMetodos(ip);


    }

    private static void pruebaMetodos(InetAddress ip){
        System.out.println("\tMetodo getByName: "+ip);
        InetAddress ip2;
        try {
            ip2 = InetAddress.getLocalHost();
            System.out.println("\tMetodo getLocalHost(): "+ip2);
        }catch (UnknownHostException e1){e1.printStackTrace();}

        System.out.println("\tMetodo getHostName(): "+ip.getHostName());
        System.out.println("\tMetodo getHostAddress(): "+ip.getHostAddress());
        System.out.println("\tMetodo getCanonicalHostName(): "+ip.getCanonicalHostName());
    }
}
