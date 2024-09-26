package EJ1_4;

import java.io.IOException;
import java.io.InputStream;

public class Ej1_4 {

    public static void main(String[] args) throws IOException {
        ProcessBuilder  pb = new ProcessBuilder("java", "LeerNombre");

        Process p = pb.start();

        try {
            int i = p.waitFor();
            System.out.println(i);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


        try{
            InputStream in = p.getInputStream();
            int c;
            while ((c = in.read()) != -1);
                System.out.print((char) c);
                in.close();
        }catch (IOException e){
            e.printStackTrace();
        }

    }

}
