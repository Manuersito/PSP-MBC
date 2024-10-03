package EJ1_8;

import java.io.*;

public class EJ1_8 {
    public static void main(String[] args) throws IOException {
        // Ruta de los archivos
        File inputFile = new File("D:\\Manuersin\\Documents\\PSP-MBC\\PSP\\src\\EJ1_7\\entrada.txt");  // Archivo de entrada
        File outputFile = new File("D:\\Manuersin\\Documents\\PSP-MBC\\PSP\\src\\EJ1_7\\salida.txt");  // Archivo de salida estándar
        File errorFile = new File("D:\\Manuersin\\Documents\\PSP-MBC\\PSP\\src\\EJ1_7\\error.txt");    // Archivo de salida de error

        // Crear ProcessBuilder para ejecutar este mismo programa
        ProcessBuilder pb = new ProcessBuilder("java", "Ejemplo5"); // Ejecuta Ejemplo5 de nuevo

        // Redirigir la entrada desde el archivo entrada.txt
        pb.redirectInput(inputFile);

        // Redirigir la salida estándar a salida.txt
        pb.redirectOutput(outputFile);

        // Redirigir la salida de error a error.txt
        pb.redirectError(errorFile);

        // Iniciar el proceso
        Process p = pb.start();

        // Esperar que el proceso termine
        try {
            int exitCode = p.waitFor();
            System.out.println("El proceso terminó con el código: " + exitCode);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

