package Actividad3_5;

import java.io.*;
import java.net.*;
import java.util.Scanner;


public class Cliente351 {
    public static void main(String[] args) throws IOException {
        String host = "localhost";
        int puerto = 3051;
        Socket socket = new Socket(host, puerto);
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        DataInputStream in = new DataInputStream(socket.getInputStream());
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Introduce un mensaje (o '*' para salir):");
            String mensaje = scanner.nextLine();

            // Enviar mensaje al servidor
            out.writeUTF(mensaje);

            if (mensaje.equals("*")) {
                System.out.println("Conexión finalizada.");
                break;
            }

            // Recibir número de caracteres del servidor
            int numCaracteres = in.readInt();
            System.out.println("El mensaje tiene " + numCaracteres + " caracteres.");
        }

        socket.close();
    }
}
