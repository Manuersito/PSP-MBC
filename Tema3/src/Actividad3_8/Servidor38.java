package Actividad3_8;

import java.io.*;
import java.net.*;

public class Servidor38 {
    public static void main(String[] args) {
        try (DatagramSocket socket = new DatagramSocket(3008)) {
            System.out.println("Servidor UDP escuchando en el puerto 3008...");

            byte[] buffer = new byte[1024];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

            // Recibir datagrama
            socket.receive(packet);
            System.out.println("Datagrama recibido del cliente.");

            // Deserializar objeto
            ByteArrayInputStream bais = new ByteArrayInputStream(packet.getData());
            ObjectInputStream ois = new ObjectInputStream(bais);
            Persona persona = (Persona) ois.readObject();
            System.out.println("Objeto recibido: " + persona);

            // Modificar objeto
            persona.setEdad(persona.getEdad() + 10);
            persona.setNombre(persona.getNombre() + " (Modificado)");

            System.out.println("Objeto modificado: " + persona);

            // Serializar objeto y enviarlo de vuelta
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(persona);
            oos.flush();

            byte[] respuesta = baos.toByteArray();
            DatagramPacket respuestaPacket = new DatagramPacket(
                    respuesta, respuesta.length, packet.getAddress(), packet.getPort()
            );
            socket.send(respuestaPacket);
            System.out.println("Objeto modificado enviado al cliente.");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error en el servidor: " + e.getMessage());
        }
    }
}
