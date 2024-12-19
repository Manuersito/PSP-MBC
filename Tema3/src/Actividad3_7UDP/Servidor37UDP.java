package Actividad3_7UDP;

import java.io.*;
import java.net.*;

public class Servidor37UDP {
    public static void main(String[] args) {
        try (DatagramSocket socket = new DatagramSocket(3017)) {
            System.out.println("Servidor UDP escuchando en el puerto 3017...");

            byte[] buffer = new byte[1024];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

            while (true) {
                // Recibir datagrama
                socket.receive(packet);
                System.out.println("Datagrama recibido del cliente.");

                // Deserializar objeto
                ByteArrayInputStream bais = new ByteArrayInputStream(packet.getData());
                ObjectInputStream ois = new ObjectInputStream(bais);
                Numeros numeros = (Numeros) ois.readObject();

                // Verificar si el número es menor o igual a 0
                if (numeros.getNumero() <= 0) {
                    System.out.println("Número recibido menor o igual a 0. Finalizando servidor...");
                    break;
                }

                System.out.println("Objeto recibido: " + numeros);

                // Calcular cuadrado y cubo
                int numero = numeros.getNumero();
                numeros.setCuadrado((long) numero * numero);
                numeros.setCubo((long) numero * numero * numero);

                System.out.println("Objeto modificado: " + numeros);

                // Serializar objeto y enviarlo de vuelta
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(baos);
                oos.writeObject(numeros);
                oos.flush();

                byte[] respuesta = baos.toByteArray();
                DatagramPacket respuestaPacket = new DatagramPacket(
                        respuesta, respuesta.length, packet.getAddress(), packet.getPort()
                );
                socket.send(respuestaPacket);
                System.out.println("Objeto modificado enviado al cliente.");
            }
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error en el servidor: " + e.getMessage());
        }
    }
}
