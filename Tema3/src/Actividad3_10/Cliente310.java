package Actividad3_10;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.*;

public class Cliente310 {

    private int intentos = 0;
    private static final int ID_JUGADOR = 1;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Cliente310().createAndShowGUI());
    }

    public void createAndShowGUI() {
        JFrame frame = new JFrame("JUGADOR - ADIVINA UN NÚMERO");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 250);

        // Panel de la interfaz
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2, 10, 10));

        // Elementos de la interfaz
        JLabel labelID = new JLabel("ID JUGADOR: " + ID_JUGADOR);
        JLabel labelIntentos = new JLabel("INTENTOS: 0");
        JLabel labelResultado = new JLabel(" ");

        JTextField inputNumero = new JTextField();
        inputNumero.setToolTipText("Número a enviar");

        JButton btnEnviar = new JButton("Enviar");
        JButton btnSalir = new JButton("Salir");

        // Añadir elementos al panel
        panel.add(labelID);
        panel.add(labelIntentos);
        panel.add(new JLabel("Adivina un número entre 1 y 25:"));
        panel.add(inputNumero);
        panel.add(btnEnviar);
        panel.add(labelResultado);
        panel.add(btnSalir);

        // Acción al presionar "Enviar"
        btnEnviar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = inputNumero.getText().trim();
                try {
                    int numero = Integer.parseInt(input);
                    String mensaje = enviarNumero(numero);
                    intentos++;
                    labelIntentos.setText("INTENTOS: " + intentos);
                    labelResultado.setText(mensaje);

                    if (mensaje.equals("¡Correcto! Número adivinado.")) {
                        inputNumero.setEnabled(false);
                        btnEnviar.setEnabled(false);
                    }
                } catch (NumberFormatException ex) {
                    labelResultado.setText("Por favor, introduce un número válido.");
                }
            }
        });

        // Acción al presionar "Salir"
        btnSalir.addActionListener(e -> System.exit(0));

        frame.add(panel);
        frame.setVisible(true);
    }

    private String enviarNumero(int numero) {
        try (DatagramSocket socket = new DatagramSocket()) {
            InetAddress address = InetAddress.getByName("localhost");
            byte[] buffer = String.valueOf(numero).getBytes();

            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, 3010);
            socket.send(packet);

            byte[] respuestaBuffer = new byte[1024];
            DatagramPacket respuestaPacket = new DatagramPacket(respuestaBuffer, respuestaBuffer.length);
            socket.receive(respuestaPacket);

            return new String(respuestaPacket.getData(), 0, respuestaPacket.getLength());
        } catch (IOException e) {
            return "Error al comunicarse con el servidor.";
        }
    }
}
