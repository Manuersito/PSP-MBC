package Actividad3_7UDP;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Cliente37UDP {
    public static void main(String[] args) {
        try (DatagramSocket socket = new DatagramSocket()) {
            InetAddress address = InetAddress.getByName("localhost");
            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.print("Introduce un número entero mayor que 0 (o 0 para salir): ");
                int numero = scanner.nextInt();

                // Crear objeto Numeros
                Numeros numeros = new Numeros();
                numeros.setNumero(numero);

                // Serializar objeto
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(baos);
                oos.writeObject(numeros);
                oos.flush();

                byte[] buffer = baos.toByteArray();
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, 3017);
                socket.send(packet);

                // Terminar si el número es menor o igual a 0
                if (numero <= 0) {
                    System.out.println("Número menor o igual a 0. Finalizando cliente...");
                    break;
                }

                System.out.println("Objeto enviado al servidor.");

                // Recibir objeto modificado
                byte[] respuestaBuffer = new byte[1024];
                DatagramPacket respuestaPacket = new DatagramPacket(respuestaBuffer, respuestaBuffer.length);
                socket.receive(respuestaPacket);

                ByteArrayInputStream bais = new ByteArrayInputStream(respuestaPacket.getData());
                ObjectInputStream ois = new ObjectInputStream(bais);
                Numeros numerosModificados = (Numeros) ois.readObject();

                System.out.println("Objeto recibido del servidor: " + numerosModificados);
            }
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error en el cliente: " + e.getMessage());
        }
    }
}
