package Actividad3_7;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Cliente37 {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 3007);
             ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream input = new ObjectInputStream(socket.getInputStream())) {

            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.print("Introduce un número entero mayor que 0 (o 0 para salir): ");
                int numero = scanner.nextInt();

                if (numero <= 0) {
                    System.out.println("Saliendo...");
                    output.writeObject(new Numeros(numero)); // Enviar el número para que el servidor cierre.
                    break;
                }

                Numeros numeros = new Numeros(numero);
                output.writeObject(numeros);

                Numeros respuesta = (Numeros) input.readObject();

                if (respuesta == null) {
                    System.out.println("El servidor ha cerrado la conexión.");
                    break;
                }

                System.out.println("Número: " + respuesta.getNumero());
                System.out.println("Cuadrado: " + respuesta.getCuadrado());
                System.out.println("Cubo: " + respuesta.getCubo());
            }
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error en el cliente: " + e.getMessage());
        }
    }
}
