package Act5_1;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.util.Scanner;

public class act5_1 {

    public static void main(String[] args) throws NoSuchAlgorithmException {
        MessageDigest md;
        String algoritmo;
        String mensaje;
        String clave;

        Scanner sc = new Scanner(System.in);

        //para la primera parte del ejercicio poner en el algoritmo
        //cuando lo pida MD5 sin poner clave,
        //para la segunda parte usar SHA-256 y poniendo una clave


        System.out.println("Ingrese el algoritmo: ");
        algoritmo = sc.nextLine();
        System.out.println("Ingrese el mensaje: ");
        mensaje = sc.nextLine();
        System.out.println("Ingrese la clave: ");
        clave = sc.nextLine();

        md = MessageDigest.getInstance(algoritmo);

        byte databyte[] = mensaje.getBytes();
        md.update(databyte);
        byte[] resumen = md.digest(clave.getBytes());

        System.out.println("Mensaje original: " + mensaje);
        System.out.println("Numero de bytes: " + md.getDigestLength());
        System.out.println("Algoritmo: " + md.getAlgorithm());
        System.out.println("Mensaje resumen: "+ new String(resumen));
        System.out.println("Mensaje en exadecimal: "+ Hexadecimal(resumen));
        Provider proveedor = md.getProvider();
        System.out.println("Proveedores: "+proveedor.toString());
    }

    static String Hexadecimal(byte[] resumen){
        String hex="";
        for(int i=0;i<resumen.length;i++){
            String h = Integer.toHexString(0xff & resumen[i]);
            if(hex.length()==1) hex+="0";
            hex+=h;
        }
        return hex.toUpperCase();
    }

}
