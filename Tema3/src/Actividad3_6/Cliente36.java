package Actividad3_6;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class Cliente36 {
    public static void main(String[] args) throws IOException {
            DatagramSocket socket = new DatagramSocket();
            InetAddress direccionServidor = InetAddress.getLocalHost(); // Dirección del servidor
            int puertoServidor = 3006;
            Scanner scanner = new Scanner(System.in);

            System.out.println("Cliente UDP iniciado. Introduce texto para enviar al servidor (usa '*' para salir):");

            while (true) {
                // Leer entrada del usuario
                System.out.print("Mensaje: ");
                String mensaje = scanner.nextLine();

                // Enviar mensaje al servidor
                byte[] bufferEnvio = mensaje.getBytes();
                DatagramPacket paqueteEnvio = new DatagramPacket(bufferEnvio, bufferEnvio.length, direccionServidor, puertoServidor);
                socket.send(paqueteEnvio);

                // Finalizar si el mensaje es "*"
                if (mensaje.equals("*")) {
                    System.out.println("Conexión finalizada por el cliente.");
                    break;
                }

                // Recibir respuesta del servidor con timeout
                byte[] bufferRespuesta = new byte[1024];
                DatagramPacket paqueteRespuesta = new DatagramPacket(bufferRespuesta, bufferRespuesta.length);
                socket.setSoTimeout(4000); // 4000 ms = 4 segundos

                try {
                    socket.receive(paqueteRespuesta);
                    String respuesta = new String(paqueteRespuesta.getData(), 0, paqueteRespuesta.getLength());
                    System.out.println("Respuesta del servidor: " + respuesta);
                } catch (java.net.SocketTimeoutException e) {
                    System.out.println("Tiempo de espera agotado. Paquete perdido.");
                }
            }

            socket.close();
    }
}
