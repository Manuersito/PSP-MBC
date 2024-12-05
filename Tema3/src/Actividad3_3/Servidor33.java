package Actividad3_3;

import java.io.*;
import java.net.*;

public class Servidor33 {
    public static void main(String[] args) throws IOException {
        int puerto = 3003;
        ServerSocket servidor = new ServerSocket(puerto);
        Socket socket = null;
        System.out.println("Servidor esperando cliente");
        socket = servidor.accept();
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        DataInputStream in = new DataInputStream(socket.getInputStream());

        // Enviar mensaje al cliente
        out.writeUTF("ESTE ES UN MENSAJE EN MAYÚSCULAS.");
        // Recibir mensaje en minúsculas
        String respuesta = in.readUTF();
        System.out.println("Mensaje del cliente: " + respuesta);

        socket.close();
        servidor.close();
    }
}

