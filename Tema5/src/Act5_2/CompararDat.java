package Act5_2;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CompararDat {

    public static void main(String[] args) throws IOException, ClassNotFoundException, NoSuchAlgorithmException {

        FileInputStream fileIn = new FileInputStream("DATOS.DAT");
        ObjectInputStream dataOS = new ObjectInputStream(fileIn);
        Object o = dataOS.readObject();

        // Primera lectura para conseguir String
        String datos = (String) o;
        System.out.println("Datos originales: " + datos);

        // Modificar datos
        datos = modificarDatos(datos);
        System.out.println("Datos modificados: " + datos);

        // Segunda lectura para conseguir resumen
        o = dataOS.readObject();
        byte resumenOriginal[] = (byte[]) o;

        MessageDigest md = MessageDigest.getInstance("SHA-256");

        // Calcular resumen del string leído y modificado
        md.update(datos.getBytes()); // Texto a resumir
        byte resumenActual[] = md.digest(); // Calcular resumen

        // Comparar resúmenes
        if (MessageDigest.isEqual(resumenOriginal, resumenActual)) {
            System.out.println("Datos válidos");
        } else {
            System.out.println("Datos inválidos");
        }

        dataOS.close();
        fileIn.close();
    }

    // Método para convertir mayúsculas en minúsculas
    private static String modificarDatos(String datos) {
        return datos.toLowerCase();
    }
}
