package ACT2_5;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Act2_5 extends Applet implements ActionListener {

    HiloContador h1 = new HiloContador(0);
    HiloContador h2 = new HiloContador(500);
    Button b1, b2;

    @Override
    public void actionPerformed(ActionEvent e) {
        // Indicamos que si pulsamos b1 se detiene h1 y si pulsamos b2 se detiene h2
        if (e.getSource() == b1) {
            h1.detener();
        } else if (e.getSource() == b2) {
            h2.detener();
        }
    }

    @Override
    public void start() {
        // Iniciamos los 2 hilos
        h1.start();
        h2.start();
    }

    @Override
    public void init() {
        // Iniciamos el applet con el fondo amarillo y 2 botones
        setBackground(Color.YELLOW);
        add(b1 = new Button("Parar Hilo 1"));
        b1.addActionListener(this);
        add(b2 = new Button("Parar Hilo 2"));
        b2.addActionListener(this);
    }

    @Override
    public void paint(Graphics g) {
        g.clearRect(0, 0, 400, 400);
        g.drawString("Hilo 1: " + (h1 != null ? h1.cont : "Detenido"), 80, 100);
        g.drawString("Hilo 2: " + (h2 != null ? h2.cont : "Detenido"), 80, 150);
    }

    public class HiloContador extends Thread {
        Integer cont;
        private volatile boolean flag; // Volatile para asegurar la visibilidad del cambio de estado

        public HiloContador(int cont) {
            this.cont = cont;
        }

        @Override
        public void run() {
            flag = false;
            // Mientras el hilo no esté marcado para detenerse, sigue ejecutando
            while (!flag) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                cont++;
                repaint();
            }
        }

        public void detener() {
            flag = true;
        }
    }
}
