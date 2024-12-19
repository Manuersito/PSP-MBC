package Actividad3_10;

import java.io.*;
import java.net.*;
import java.util.Random;

public class Servidor310 {
    public static void main(String[] args) {
        try (DatagramSocket socket = new DatagramSocket(3010)) {
            System.out.println("Servidor UDP escuchando en el puerto 3010...");
            Random random = new Random();
            int numeroAdivinar = random.nextInt(25) + 1;
            System.out.println("Número a adivinar: " + numeroAdivinar);

            byte[] buffer = new byte[1024];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

            while (true) {
                socket.receive(packet);
                String recibido = new String(packet.getData(), 0, packet.getLength());
                int numero = Integer.parseInt(recibido.trim());

                String respuesta;
                if (numero < numeroAdivinar) {
                    respuesta = "Número demasiado pequeño";
                } else if (numero > numeroAdivinar) {
                    respuesta = "Número demasiado grande";
                } else {
                    respuesta = "¡Correcto! Número adivinado.";
                }

                byte[] respuestaBytes = respuesta.getBytes();
                DatagramPacket respuestaPacket = new DatagramPacket(
                        respuestaBytes, respuestaBytes.length, packet.getAddress(), packet.getPort());
                socket.send(respuestaPacket);

                if (respuesta.equals("¡Correcto! Número adivinado.")) {
                    System.out.println("Número adivinado correctamente. Finalizando servidor...");
                    break;
                }
            }
        } catch (IOException e) {
            System.err.println("Error en el servidor: " + e.getMessage());
        }
    }
}
