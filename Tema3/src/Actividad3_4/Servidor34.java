package Actividad3_4;

import java.io.*;
import java.net.*;

public class Servidor34 {
    public static void main(String[] args) throws IOException {
        int puerto = 3004;
        ServerSocket servidor = new ServerSocket(puerto);
        System.out.println("Esperando Cliente");
        Socket socket = servidor.accept();
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        DataInputStream in = new DataInputStream(socket.getInputStream());

        // Recibir número del cliente
        int numero = in.readInt();
        // Enviar el cuadrado del número
        out.writeInt(numero * numero);

        socket.close();
        servidor.close();
    }
}
