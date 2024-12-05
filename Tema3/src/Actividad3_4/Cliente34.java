package Actividad3_4;

import java.io.*;
import java.net.*;
import java.util.Scanner;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Cliente34 {
    public static void main(String[] args) throws IOException {
        int puerto = 3004;
        String host = "localhost";
        Socket cliente = new Socket(host,puerto);
        DataOutputStream out = new DataOutputStream(cliente.getOutputStream());
        DataInputStream in = new DataInputStream(cliente.getInputStream());
        Scanner scanner = new Scanner(System.in);

        System.out.println("Introduce un número entero:");
        int numero = scanner.nextInt();
        // Enviar número al servidor
        out.writeInt(numero);
        // Recibir cuadrado del número
        int respuesta = in.readInt();
        System.out.println("El cuadrado del número es: " + respuesta);

        cliente.close();
    }
}

