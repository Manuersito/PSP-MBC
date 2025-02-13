package Act5_2;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CompararDat {

    public static void main(String[] args) throws IOException, ClassNotFoundException, NoSuchAlgorithmException {

        FileInputStream fileout = new FileInputStream("DATOS.DAT");
        ObjectInputStream dataOS = new ObjectInputStream(fileout);
        Object o = dataOS.readObject();

        //Primera lectura para conseguir String
        String datos = (String) o;
        System.out.println("Datos: "+datos);

        //Segunda lectura para conseguir resumen
        o = dataOS.readObject();
        byte resumenOriginal[] = (byte[]) o;

        MessageDigest md = MessageDigest.getInstance("SHA-256");

        //calcular resumen del string leido

    }
}
