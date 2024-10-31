package Ej8;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Ej8 extends Applet implements ActionListener {

    MyHilo h1, h2;
    Button b1, b2, b3, b4, b5, b6;

    @Override
    public void actionPerformed(ActionEvent e) {
        // Manejo de los botones para controlar los hilos
        if (e.getSource() == b1) {
            h1.Suspende();
        } else if (e.getSource() == b2) {
            h2.Suspende();
        }

        if (e.getSource() == b4) {
            h1.reanuda();
        } else if (e.getSource() == b5) {
            h2.reanuda();
        }

        if (e.getSource() == b3) {
            // Inicia los hilos solo una vez
            if (h1 == null || !h1.isAlive()) {
                h1 = new MyHilo();
                h1.start();
            }
            if (h2 == null || !h2.isAlive()) {
                h2 = new MyHilo();
                h2.start();
            }
        } else if (e.getSource() == b6) {
            h1.termina();
            h2.termina();
            h1 = null;
            h2 = null;
            repaint();
        }
    }

    @Override
    public void init() {
        // Configuración de los botones
        setBackground(Color.YELLOW);
        add(b3 = new Button("Comenzar Procesos"));
        b3.addActionListener(this);
        add(b1 = new Button("Parar Hilo 1"));
        b1.addActionListener(this);
        add(b2 = new Button("Parar Hilo 2"));
        b2.addActionListener(this);
        add(b4 = new Button("Reanudar Hilo 1"));
        b4.addActionListener(this);
        add(b5 = new Button("Reanudar Hilo 2"));
        b5.addActionListener(this);
        add(b6 = new Button("Parar Procesos"));
        b6.addActionListener(this);
    }

    @Override
    public void paint(Graphics g) {
        // Dibuja los contadores de los hilos
        g.clearRect(0, 0, 400, 400);
        g.drawString("Hilo 1: " + (h1 != null ? h1.contador : "Detenido"), 80, 100);
        g.drawString("Hilo 2: " + (h2 != null ? h2.contador : "Detenido"), 80, 150);
    }

    public class MyHilo extends Thread {
        private SolicitaSuspender suspender = new SolicitaSuspender();
        public int contador = 0;
        boolean running = true;

        public void Suspende() {
            suspender.set(true);
        }

        public void reanuda() {
            suspender.set(false);
        }

        public void termina() {
            running = false;
        }

        public int getContador() {
            return ++contador;
        }

        @Override
        public void run() {
            try {
                while (running) {
                    suspender.esperandoParaReanudar();
                    Thread.sleep(1000);
                    contador = getContador();
                    repaint();
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public class SolicitaSuspender {
        private boolean suspender;

        public synchronized void set(boolean b) {
            suspender = b;
            notifyAll();
        }

        public synchronized void esperandoParaReanudar() throws InterruptedException {
            while (suspender) {
                wait();
            }
        }
    }
}
