package ACT2_12;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main extends JFrame {
    private int x = 1; // Posición inicial en x
    private int y = 100; // Posición fija en y
    private JButton boton;
    private Hilo hilo;

    public Main() {
        setTitle("Letra en Movimiento");
        setSize(400, 200); // Tamaño inicial de la ventana
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Cierra la aplicación al cerrar la ventana

        boton = new JButton("Finalizar Hilo");
        boton.setBounds(140, 120, 120, 30);
        add(boton);

        // Evento del botón para pausar o reanudar el hilo
        boton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (hilo != null) {
                    hilo.cambioEstado(); // Cambiar estado del hilo
                    String texto;
                    if (hilo.isMoviendo()) {
                        texto = "Finalizar Hilo";
                    } else {
                        texto = "Reanudar Hilo";
                    }
                    boton.setText(texto);
                }
            }
        });

        // Crea el hilo, lo inicia y comienza a mover la letra
        hilo = new Hilo(this);
        hilo.start();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setFont(new Font("Arial", Font.BOLD, 30));
        g.drawString("A", x, y); // Dibuja la letra en la posición (x, y)
    }

    // Método para actualizar la posición de la letra
    public void setX(int x) {
        this.x = x;
        repaint();
    }

    // Método para obtener el límite derecho
    public int getLimite() {
        return getWidth() - 50;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Main frame = new Main();
                frame.setVisible(true); // Muestra la ventana
            }
        });
    }
}
