package Ejercicio3;

import java.net.*;
import java.io.*;
import java.util.*;


public class Servidor {
    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket(9876);
            System.out.println("Servidor UDP en funcionamiento...");

            // Inicializar datos de ejemplo
            Curso curso1 = new Curso("C1", "Matemáticas");
            Curso curso2 = new Curso("C2", "Historia");

            Alumno[] alumnos = {
                    new Alumno("A1", "Juan Perez", curso1, 85),
                    new Alumno("A2", "Maria Lopez", curso2, 90),
                    new Alumno("A3", "Carlos Ruiz", curso1, 78),
                    new Alumno("A4", "Ana Torres", curso2, 92),
                    new Alumno("A5", "Luis Gomez", curso1, 88)
            };

            while (true) {
                // Recibir solicitud del cliente
                byte[] buffer = new byte[1024];
                DatagramPacket request = new DatagramPacket(buffer, buffer.length);
                socket.receive(request);
                String idAlumno = new String(request.getData(), 0, request.getLength());
                System.out.println("Solicitud recibida para ID: " + idAlumno);

                // Buscar al alumno correspondiente
                Alumno alumnoEncontrado = null;
                for (Alumno alumno : alumnos) {
                    if (alumno.getIdAlumno().equals(idAlumno)) {
                        alumnoEncontrado = alumno;
                        break;
                    }
                }

                // Preparar respuesta
                String respuesta;
                if (alumnoEncontrado != null) {
                    respuesta = alumnoEncontrado.toString();
                } else {
                    respuesta = "Alumno no encontrado.";
                }

                // Enviar respuesta al cliente
                byte[] respuestaBytes = respuesta.getBytes();
                InetAddress clientAddress = request.getAddress();
                int clientPort = request.getPort();
                DatagramPacket response = new DatagramPacket(respuestaBytes, respuestaBytes.length, clientAddress, clientPort);
                socket.send(response);
                System.out.println("Respuesta enviada: " + respuesta);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

