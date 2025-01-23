package Ej2;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Ej2 {

    public static void main(String[] args) {
        // Datos de conexión al servidor FTP
        String servidor = "localhost";  // direccion ip o dominio
        int puerto = 21;                // Puerto servidor
        String usuario = "cliente";    // Nombre de usuario
        String contra = "cliente"; // Contraseña del usuario
        String directorioRemoto = "/";  // Directorio donde se subirán los archivos

        // Crear un diálogo para que el usuario seleccione un archivo
        JFileChooser selectorArchivo = new JFileChooser();
        selectorArchivo.setDialogTitle("Selecciona el archivo a subir");
        int resultado = selectorArchivo.showOpenDialog(null);

        if (resultado == JFileChooser.APPROVE_OPTION) {
            File archivoSeleccionado = selectorArchivo.getSelectedFile();

            // Conectarse al servidor FTP
            FTPClient clienteFTP = new FTPClient();
            try {
                clienteFTP.connect(servidor, puerto);
                if (!clienteFTP.login(usuario, contra)) {
                    JOptionPane.showMessageDialog(null, "Error de autenticación.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Configurar la conexión
                clienteFTP.enterLocalPassiveMode();
                clienteFTP.setFileType(FTP.BINARY_FILE_TYPE);

                // Cambiar al directorio remoto si es necesario
                if (!clienteFTP.changeWorkingDirectory(directorioRemoto)) {
                    JOptionPane.showMessageDialog(null, "No se pudo acceder al directorio remoto.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Subir el archivo al servidor
                String nombreArchivoRemoto = archivoSeleccionado.getName();
                try (FileInputStream flujoEntrada = new FileInputStream(archivoSeleccionado)) {
                    if (clienteFTP.storeFile(nombreArchivoRemoto, flujoEntrada)) {
                        JOptionPane.showMessageDialog(null, "Archivo subido correctamente: " + nombreArchivoRemoto);
                    } else {
                        JOptionPane.showMessageDialog(null, "Error al subir el archivo.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }

                // Listar los archivos en el directorio remoto
                System.out.println("Archivos en el directorio remoto:");
                for (String archivo : clienteFTP.listNames()) {
                    System.out.println(archivo);
                }

            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Error de conexión: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            } finally {
                try {
                    if (clienteFTP.isConnected()) {
                        clienteFTP.disconnect();
                    }
                } catch (IOException e) {
                    // Ignorar errores al cerrar la conexión
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "No se seleccionó ningún archivo.");
        }
    }
}