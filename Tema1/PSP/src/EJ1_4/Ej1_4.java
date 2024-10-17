package EJ1_4;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class Ej1_4 {

    public static void main(String[] args) throws IOException {
        // Establecemos el directorio donde está el archivo LeerNombre.class
        File directorio = new File("/home/usuario/Documentos/PSP-MBC/PSP/out/production/PSP");

        // Valor 1
        ProcessBuilder pb = new ProcessBuilder("java", "EJ1_4.LeerNombre", "Mi nombre es Manuel");


        // Valor -1
        //ProcessBuilder pb = new ProcessBuilder("java", "EJ1_4.LeerNombre");

        // Establecemos el directorio donde buscar el archivo .class
        pb.directory(directorio);

        // Iniciamos el proceso
        Process p = pb.start();

        // Leemos y mostramos la salida del proceso
        try (InputStream in = p.getInputStream()) {
            int c;
            while ((c = in.read()) != -1) {
                System.out.print((char) c);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Mostramos el valor de salida del proceso
        try {
            int exitVal = p.waitFor();
            System.out.println("Valor de salida: " + exitVal);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
