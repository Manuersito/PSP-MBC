package Ejercicio3;

import java.net.*;
import java.io.*;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket();
            InetAddress serverAddress = InetAddress.getByName("localhost");
            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.print("Ingrese el ID del alumno a consultar (o 'salir' para terminar): ");
                String idAlumno = scanner.nextLine();

                if (idAlumno.equalsIgnoreCase("salir")) {
                    System.out.println("Cliente finalizado.");
                    break;
                }

                // Enviar solicitud al servidor
                byte[] requestBytes = idAlumno.getBytes();
                DatagramPacket request = new DatagramPacket(requestBytes, requestBytes.length, serverAddress, 9876);
                socket.send(request);

                // Recibir respuesta del servidor
                byte[] buffer = new byte[1024];
                DatagramPacket response = new DatagramPacket(buffer, buffer.length);
                socket.receive(response);
                String respuesta = new String(response.getData(), 0, response.getLength());

                // Mostrar la respuesta
                System.out.println("Respuesta del servidor: " + respuesta);
            }

            socket.close();
            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

