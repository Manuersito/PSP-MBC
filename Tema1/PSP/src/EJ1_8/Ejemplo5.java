package EJ1_8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Ejemplo5 {

    public static void main(String[] args) {
        InputStreamReader in = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(in);
        String texto;
        try {
            System.out.println("Introduzca el texto: ");
            texto= br.readLine();
            System.out.println("CadenaEscrita: "+texto);
            in.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
