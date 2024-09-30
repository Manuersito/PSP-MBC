package EJ1_5;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class Ej1_5 {

    public static void main(String[] args) throws IOException {
        // Establecemos el directorio donde está el archivo LeerNombre.class
        File directorio = new File("/home/usuario/Documentos/PSP-MBC/PSP/out/production/PSP");

        // Valor 1
        ProcessBuilder pb = new ProcessBuilder("java", "EJ1_5.hola");

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

        try (InputStream err = p.getErrorStream()) {
            int c;
            System.out.println("\nErrores del proceso:");
            while ((c = err.read()) != -1) {
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
