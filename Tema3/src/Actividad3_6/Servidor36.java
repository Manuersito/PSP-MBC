package Actividad3_6;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class Servidor36 {
    public static void main(String[] args) throws IOException {
        int puerto = 3006;
            DatagramSocket socket = new DatagramSocket(puerto); // Puerto del servidor
            byte[] buffer = new byte[1024];
            System.out.println("Servidor UDP iniciado...");

            while (true) {
                // Recibir paquete
                DatagramPacket paqueteRecibido = new DatagramPacket(buffer, buffer.length);
                socket.receive(paqueteRecibido);
                String mensaje = new String(paqueteRecibido.getData(), 0, paqueteRecibido.getLength());

                // Finalizar si el mensaje es "*"
                if (mensaje.equals("*")) {
                    System.out.println("El cliente ha terminado la conexión.");
                    break;
                }

                // Mostrar mensaje recibido y enviarlo en mayúsculas
                System.out.println("Mensaje recibido: " + mensaje);
                String respuesta = mensaje.toUpperCase();

                // Enviar respuesta
                InetAddress direccionCliente = paqueteRecibido.getAddress();
                int puertoCliente = paqueteRecibido.getPort();
                byte[] respuestaBytes = respuesta.getBytes();
                DatagramPacket paqueteRespuesta = new DatagramPacket(respuestaBytes, respuestaBytes.length, direccionCliente, puertoCliente);
                socket.send(paqueteRespuesta);
            }

            socket.close();
            System.out.println("Servidor finalizado.");

    }
}