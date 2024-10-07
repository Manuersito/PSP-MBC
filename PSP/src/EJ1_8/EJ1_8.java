package EJ1_8;

import java.io.*;

public class EJ1_8 {
    public static void main(String[] args) throws IOException {
        // Ruta de los archivos windows
//        File inputFile = new File("D:\\Manuersin\\Documents\\PSP-MBC\\PSP\\src\\EJ1_7\\entrada.txt");  // Archivo de entrada
//        File outputFile = new File("D:\\Manuersin\\Documents\\PSP-MBC\\PSP\\src\\EJ1_7\\salida.txt");  // Archivo de salida estándar


        //Ruta archivo linux
        File inputFile = new File("/home/usuario/Documentos/PSP-MBC/PSP/src/EJ1_8/entrada.txt");  // Archivo de entrada
        File outputFile = new File("/home/usuario/Documentos/PSP-MBC/PSP/src/EJ1_8/salida.txt");  // Archivo de salida estándar


        //Directorio carpeta class
        File directorio = new File("/home/usuario/Documentos/PSP-MBC/PSP/out/production/PSP");


        // Crear ProcessBuilder para ejecutar este mismo programa
        ProcessBuilder pb = new ProcessBuilder("java", "EJ1_8.Ejemplo5"); // Ejecuta Ejemplo5 de nuevo

        pb.directory(directorio);

        pb.inheritIO();

// Iniciar el proceso
        Process p = pb.start();

        //Mostrar por consola
        try (InputStream in = p.getInputStream()) {
            int c;
            while ((c = in.read()) != -1) {
                System.out.print((char) c);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        pb.redirectInput(ProcessBuilder.Redirect.from(inputFile));
        pb.redirectOutput(ProcessBuilder.Redirect.to(outputFile));
        pb.start();

pb.start();



        // Esperar que el proceso termine
        try {
            int exitCode = p.waitFor();
            System.out.println("El proceso terminó con el código: " + exitCode);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

