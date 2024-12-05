package Actividad3_2;

import java.io.IOException;
import java.net.Socket;

public class Cliente32 {
    public static void main(String[] args) throws IOException {
        String Host = "localhost";
        int Puerto = 3002;

        Socket Cliente = new Socket(Host, Puerto);
        System.out.println("Conectando al servidor por el puerto " + Puerto);

        System.out.println("\tPuerto local: " + Cliente.getLocalPort());
        System.out.println("\tPuerto remoto: " + Cliente.getPort());
        System.out.println("\tIP Máquina Remota: " + Cliente.getInetAddress().getHostAddress());

        Cliente.close();
    }
}