package Actividad3_3;
import java.io.*;
import java.net.*;

public class Cliente33 {
    public static void main(String[] args) throws IOException {
        String host = "localhost";
        int port = 3003;
        Socket cliente = new Socket(host, port);
        DataOutputStream out = new DataOutputStream(cliente.getOutputStream());
        DataInputStream in = new DataInputStream(cliente.getInputStream());

        // Recibir mensaje del servidor
        String mensaje = in.readUTF();
        System.out.println("Mensaje recibido del servidor: " + mensaje);
        // Enviar mensaje convertido a minúsculas
        out.writeUTF(mensaje.toLowerCase());

        cliente.close();
    }
}
