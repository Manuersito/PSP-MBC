package Actividad3_7;

import java.io.*;
import java.net.*;

public class Servidor37 {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(3007)) {
            System.out.println("Servidor escuchando en el puerto 3007...");

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Cliente conectado.");

                try (ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
                     ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream())) {

                    while (true) {
                        Numeros numeros = (Numeros) input.readObject();
                        int numero = numeros.getNumero();

                        if (numero <= 0) {
                            System.out.println("Número recibido menor o igual a 0. Cerrando conexión con el cliente...");
                            output.writeObject(null); // Indicar al cliente que se cerrará la conexión.
                            break;
                        }

                        numeros.setCuadrado((long) numero * numero);
                        numeros.setCubo((long) numero * numero * numero);

                        output.writeObject(numeros);
                        System.out.println("Enviado cuadrado y cubo al cliente.");
                    }
                } catch (ClassNotFoundException e) {
                    System.err.println("Error al recibir el objeto: " + e.getMessage());
                }

                System.out.println("Conexión con el cliente cerrada.");
            }
        } catch (IOException e) {
            System.err.println("Error en el servidor: " + e.getMessage());
        }
    }
}
