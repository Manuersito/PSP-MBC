package Ej3;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class Ej3 {

    private static final FTPClient clienteFtp = new FTPClient();

    public static void main(String[] args) {
        // Crear ventana principal
        JFrame ventanaPrincipal = new JFrame("Cliente FTP");
        ventanaPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventanaPrincipal.setSize(1000, 850);
        ventanaPrincipal.setLayout(new BorderLayout());

        // Panel para ingresar credenciales
        JPanel panelLogin = new JPanel();
        panelLogin.setLayout(new GridLayout(3, 2));
        JLabel labelUsuario = new JLabel("Usuario:");
        JTextField campoUsuario = new JTextField("cliente", 15);
        JLabel labelContra = new JLabel("Contraseña:");
        JPasswordField campoContrasena = new JPasswordField("cliente", 15);
        JButton botonConectar = new JButton("Conectar");
        panelLogin.add(labelUsuario);
        panelLogin.add(campoUsuario);
        panelLogin.add(labelContra);
        panelLogin.add(campoContrasena);
        panelLogin.add(new JLabel());
        panelLogin.add(botonConectar);

        ventanaPrincipal.add(panelLogin, BorderLayout.NORTH);

        // Lista de archivos y botones
        DefaultListModel<String> modeloListaArchivos = new DefaultListModel<>();
        JList<String> listaArchivos = new JList<>(modeloListaArchivos);
        JScrollPane panelDesplazamiento = new JScrollPane(listaArchivos);

        JButton botonDescargar = new JButton("Descargar");
        JButton botonSalir = new JButton("Salir");

        JPanel panelBotones = new JPanel();
        panelBotones.add(botonDescargar);
        panelBotones.add(botonSalir);

        ventanaPrincipal.add(panelDesplazamiento, BorderLayout.CENTER);
        ventanaPrincipal.add(panelBotones, BorderLayout.SOUTH);

        // Acción para conectarse al servidor FTP
        botonConectar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usuario = campoUsuario.getText();
                String contrasena = new String(campoContrasena.getPassword());
                try {
                    clienteFtp.connect("localhost", 21);
                    boolean inicioSesionExitoso = clienteFtp.login(usuario, contrasena);
                    clienteFtp.changeWorkingDirectory("/home/usuario");

                    if (inicioSesionExitoso) {
                        JOptionPane.showMessageDialog(ventanaPrincipal, "Conexión establecida correctamente.");
                        clienteFtp.enterLocalPassiveMode();

                        // Listar archivos disponibles
                        modeloListaArchivos.clear();
                        for (String archivo : clienteFtp.listNames()) {
                            modeloListaArchivos.addElement(archivo);
                        }
                    } else {
                        JOptionPane.showMessageDialog(ventanaPrincipal, "Credenciales incorrectas.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(ventanaPrincipal, "Error de conexión: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Acción para descargar un archivo
        botonDescargar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String archivoSeleccionado = listaArchivos.getSelectedValue();
                if (archivoSeleccionado == null) {
                    JOptionPane.showMessageDialog(ventanaPrincipal, "Por favor, seleccione un archivo para descargar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                // Seleccionar directorio de destino
                JFileChooser selectorDirectorio = new JFileChooser();
                selectorDirectorio.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int resultado = selectorDirectorio.showSaveDialog(ventanaPrincipal);

                if (resultado == JFileChooser.APPROVE_OPTION) {
                    File directorioDestino = selectorDirectorio.getSelectedFile();
                    File archivoLocal = new File(directorioDestino, archivoSeleccionado);

                    try (FileOutputStream flujoSalida = new FileOutputStream(archivoLocal)) {
                        boolean exito = clienteFtp.retrieveFile(archivoSeleccionado, flujoSalida);
                        if (exito) {
                            JOptionPane.showMessageDialog(ventanaPrincipal, "El archivo ha sido descargado en: " + archivoLocal.getAbsolutePath());
                        } else {
                            JOptionPane.showMessageDialog(ventanaPrincipal, "Error al intentar descargar el archivo.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(ventanaPrincipal, "Error al guardar el archivo: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        // Acción para salir de la aplicación
        botonSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (clienteFtp.isConnected()) {
                        clienteFtp.logout();
                        clienteFtp.disconnect();
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                ventanaPrincipal.dispose();
            }
        });

        ventanaPrincipal.setVisible(true);
    }
}