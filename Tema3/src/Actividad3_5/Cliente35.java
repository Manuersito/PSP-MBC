package Actividad3_5;

import java.io.*;
import java.net.*;

public class Cliente35 {
    public static void main(String[] args) throws IOException {
        int puerto = 3005;
        String host = "localhost";

        Socket socket = new Socket(host, puerto);
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        DataInputStream in = new DataInputStream(socket.getInputStream());

        // Recibir mensaje del servidor
        String mensaje = in.readUTF();
        System.out.println("Mensaje recibido del servidor: " + mensaje);
        // Enviar respuesta al servidor
        out.writeUTF("¡Mensaje recibido correctamente!");

        socket.close();
    }
}

