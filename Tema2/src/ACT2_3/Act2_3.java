package ACT2_3;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Act2_3 extends Applet implements ActionListener {

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
    public void stop() {
        // Detenemos ambos hilos antes de asignar null
        if (h1 != null) {
            h1.detener();
        }
        if (h2 != null) {
            h2.detener();
        }

        // Asignamos null para liberar recursos
        h1 = null;
        h2 = null;
    }

    @Override
    public void paint(Graphics g) {
        g.clearRect(0, 0, 400, 400);
        g.drawString("Hilo 1: " + (h1 != null ? h1.cont : "Detenido"), 80, 100);
        g.drawString("Hilo 2: " + (h2 != null ? h2.cont : "Detenido"), 80, 150);
    }

    public class HiloContador extends Thread {
        Thread h;
        Integer cont;
        boolean flag;

        public HiloContador(int cont) {
            this.cont = cont;
            h = this;  // Inicializamos h con el hilo actual
        }

        @Override
        public void run() {
            flag = false;
            Thread hiloactual = Thread.currentThread();
            // Mientras que funcione el hilo y no cambie la flag sigue sumando el cont
            while (h == hiloactual && !flag) {
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
