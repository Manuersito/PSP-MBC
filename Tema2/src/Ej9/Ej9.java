package Ej9;
import java.applet.Applet;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Ej9 extends Applet implements ActionListener {


    private HiloContador h1, h2;
    private Button b1, b2, b3, b4;

    @Override
    public void init() {
        setLayout(new FlowLayout());

        // Botón para comenzar el proceso
        add( b1 = new Button("Comenzar"));
        b1.addActionListener(this);


        // Botones para interrumpir cada hilo
        add(b2 = new Button("Interrumpir H1"));
        b2.addActionListener(this);

        add(b3 = new Button("Interrumpir H2"));
        b3.addActionListener(this);

        // Botón para finalizar el proceso
        add(b4 = new Button("Finalizar"));
        b4.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) {
            // Inicializamos y empezamos los hilos solo una vez
            h1 = new HiloContador("H1", 500);
            h2 = new HiloContador("H2", 700);
            h1.start();
            h2.start();


        } else if (e.getSource() == b2) {
            // Interrumpimos el hilo 1
            h1.interrumpir();


        } else if (e.getSource() == b3) {
            // Interrumpimos el hilo 2
            h2.interrumpir();


        } else if (e.getSource() == b4) {
            // Detenemos ambos hilos y mostramos el valor final en consola
            if (h1 != null) h1.interrumpir();
            if (h2 != null) h2.interrumpir();

            System.out.println("Valor final de H1: " + (h1 != null ? h1.getContador() : "N/A"));
            System.out.println("Valor final de H2: " + (h2 != null ? h2.getContador() : "N/A"));

        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawString("H1: " + (h1 != null ? (h1.Ejecutando() ? h1.getContador() : "Interrumpido") : "Inactivo"), 20, 100);
        g.drawString("H2: " + (h2 != null ? (h2.Ejecutando() ? h2.getContador() : "Interrumpido") : "Inactivo"), 20, 120);
    }

    // Clase interna para los hilos
    class HiloContador extends Thread {
        private String nombre;
        private int delay;
        private int contador;
        private volatile boolean enEjecucion = true;

        public HiloContador(String nombre, int delay) {
            this.nombre = nombre;
            this.delay = delay;
        }

        @Override
        public void run() {
            while (enEjecucion) {
                try {
                    Thread.sleep(delay);
                    contador++;
                    repaint();
                } catch (InterruptedException e) {
                    enEjecucion = false;
                }
            }
        }

        public void interrumpir() {
            enEjecucion = false;
            interrupt();
        }

        public int getContador() {
            return contador;
        }

        public boolean Ejecutando() {
            return enEjecucion;
        }
    }
}
