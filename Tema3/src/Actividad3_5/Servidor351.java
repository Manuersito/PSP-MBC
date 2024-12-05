package Actividad3_5;

import java.io.*;
import java.net.*;

public class Servidor351 {
    public static void main(String[] args) throws IOException {
        int puerto = 3051;
        ServerSocket serverSocket = new ServerSocket(puerto);
        System.out.println("Servidor esperando conexión...");
        Socket socket = serverSocket.accept();
        System.out.println("Cliente conectado.");

        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        DataInputStream in = new DataInputStream(socket.getInputStream());

        while (true) {
            // Recibir mensaje del cliente
            String mensaje = in.readUTF();
            if (mensaje.equals("*")) {
                System.out.println("Cliente finalizó la conexión.");
                break;
            }

            // Calcular número de caracteres y enviar al cliente
            int numCaracteres = mensaje.length();
            out.writeInt(numCaracteres);
            System.out.println("Mensaje recibido: '" + mensaje + "', caracteres: " + numCaracteres);
        }

        socket.close();
        serverSocket.close();
    }
}



