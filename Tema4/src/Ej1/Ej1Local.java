package Ej1;

import org.apache.commons.net.ftp.FTPClient;
import java.io.IOException;

public class Ej1Local {
    public static void main(String[] args) {
        // Datos de conexión al servidor FTP
        String servidor = "localhost"; // dirección IP o nombre de dominio
        int puerto = 21; // Puerto del servidor
        String usuario = "cliente"; // nombre de usuario
        String contra = "cliente"; // contraseña

        FTPClient FTPClient = new FTPClient();

        try {
            // Intentamos conectar al servidor FTP
            FTPClient.connect(servidor, puerto);
            if (!FTPClient.login(usuario, contra)) {
                System.out.println("Error: No se pudo iniciar sesión.");
                return;
            }

            System.out.println("Conexión establecida con éxito.");

            String ruta = "/";

            // Listamos el contenido del directorio raíz
            System.out.println("Contenido del directorio raíz:");
            var contenido = FTPClient.listFiles(ruta);
            if (contenido != null && contenido.length > 0) {
                for (var elemento : contenido) {
                    System.out.println(elemento.getName());
                }
            } else {
                System.out.println("El directorio raíz está vacío o no es accesible.");
            }

            // Cerramos la conexión
            FTPClient.logout();
            System.out.println("Conexión cerrada.");
        } catch (IOException e) {
            System.out.println("Error durante la operación: " + e.getMessage());
        } finally {
            try {
                if (FTPClient.isConnected()) {
                    FTPClient.disconnect();
                    System.out.println("Desconexión realizada.");
                }
            } catch (IOException e) {
                System.out.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
    }
}