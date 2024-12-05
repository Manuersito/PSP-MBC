package Actividad3_5;
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Servidor35 {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        final int N; // Máximo de clientes
        int puerto = 3005;
        ServerSocket serverSocket = new ServerSocket(puerto);

        System.out.println("Escribe maximo de clientes");
        N = sc.nextInt();

        int clienteActual = 1;

        while (clienteActual <= N) {
            Socket socket = serverSocket.accept();
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            DataInputStream in = new DataInputStream(socket.getInputStream());

            // Enviar número de cliente
            out.writeUTF("Eres el cliente número: " + clienteActual);
            // Recibir mensaje del cliente
            String mensaje = in.readUTF();
            System.out.println("Cliente " + clienteActual + " dice: " + mensaje);

            socket.close();
            clienteActual++;
        }

        serverSocket.close();
    }
}


