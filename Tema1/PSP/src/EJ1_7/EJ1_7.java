package EJ1_7;

import java.io.*;

public class EJ1_7 {
    public static void main(String[] args) {
        try {
//            // Especificar la ruta completa de los archivos
//            File input = new File("D:\\Manuersin\\Documents\\PSP-MBC\\PSP\\src\\EJ1_7\\entrada.txt");  // Archivo de entrada
//            File output = new File("D:\\Manuersin\\Documents\\PSP-MBC\\PSP\\src\\EJ1_7\\salida.txt");  // Archivo de salida estándar
//            File error = new File("D:\\Manuersin\\Documents\\PSP-MBC\\PSP\\src\\EJ1_7\\error.txt");    // Archivo de salida de error


            //ruta linux
            File input = new File("/home/usuario/Documentos/PSP-MBC/PSP/src/EJ1_7/entrada.txt");  // Archivo de entrada
            File output = new File("/home/usuario/Documentos/PSP-MBC/PSP/src/EJ1_7/salida.txt");  // Archivo de salida estándar
            File error = new File("/home/usuario/Documentos/PSP-MBC/PSP/src/EJ1_7/error.txt");    // Archivo de salida de error



            // Redirigir la salida estándar y la salida de error a los archivos
            PrintStream salida = new PrintStream(new FileOutputStream(output));
            PrintStream errorSalida = new PrintStream(new FileOutputStream(error));
            System.setOut(salida);
            System.setErr(errorSalida);

            // Leer del archivo de entrada y procesar la entrada
            BufferedReader br = new BufferedReader(new FileReader(input));
            String texto;
            while ((texto = br.readLine()) != null) {
                System.out.println("Cadena escrita: " + texto);  // Escribe en salida.txt
            }

            // Cerrar los flujos
            br.close();
            salida.close();
            errorSalida.close();

        } catch (FileNotFoundException e) {
            System.err.println("Archivo no encontrado: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error de entrada/salida: " + e.getMessage());
        }
    }
}
