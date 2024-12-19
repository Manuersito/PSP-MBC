package Actividad3_8;

import java.io.*;
import java.net.*;

public class Cliente38 {
    public static void main(String[] args) {
        try (DatagramSocket socket = new DatagramSocket()) {
            InetAddress address = InetAddress.getByName("localhost");

            // Crear objeto Persona
            Persona persona = new Persona("Juan", 25);
            System.out.println("Enviando objeto: " + persona);

            // Serializar objeto
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(persona);
            oos.flush();

            byte[] buffer = baos.toByteArray();
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, 3008);
            socket.send(packet);
            System.out.println("Objeto enviado al servidor.");

            // Recibir objeto modificado
            byte[] respuestaBuffer = new byte[1024];
            DatagramPacket respuestaPacket = new DatagramPacket(respuestaBuffer, respuestaBuffer.length);
            socket.receive(respuestaPacket);

            ByteArrayInputStream bais = new ByteArrayInputStream(respuestaPacket.getData());
            ObjectInputStream ois = new ObjectInputStream(bais);
            Persona personaModificada = (Persona) ois.readObject();

            System.out.println("Objeto recibido del servidor: " + personaModificada);
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error en el cliente: " + e.getMessage());
        }
    }
}
