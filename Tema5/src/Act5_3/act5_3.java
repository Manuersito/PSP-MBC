package Act5_3;



import java.io.FileOutputStream;
import java.io.IOException;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class act5_3 {
    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {

        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("DSA");

        //Inicializar generador de clave
        SecureRandom numero = SecureRandom.getInstance("SHA1PRNG");
        keyGen.initialize(2048, numero);

        //Se crea el par de claves

        KeyPair par = keyGen.generateKeyPair();
        PrivateKey clavepriv = par.getPrivate();
        PublicKey clavepub = par.getPublic();

        
        //Almacenar clave privada

        PKCS8EncodedKeySpec pk8Spec = new PKCS8EncodedKeySpec(clavepriv.getEncoded());

        //Escribir a fichero binario
        FileOutputStream outpriv = new FileOutputStream("Clave.privada");
        outpriv.write(pk8Spec.getEncoded());
        outpriv.close();

        //almacenar clave publica
        X509EncodedKeySpec pkX509 = new X509EncodedKeySpec(clavepub.getEncoded());

        //Escribir a fichero binario
        FileOutputStream outpub = new FileOutputStream("Clave.publica");
        outpub.write(pkX509.getEncoded());
        outpub.close();


    }


}
