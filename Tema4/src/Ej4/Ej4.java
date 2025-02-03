package Ej4;

import org.apache.commons.net.smtp.AuthenticatingSMTPClient;
import org.apache.commons.net.smtp.SMTPReply;
import org.apache.commons.net.smtp.SimpleSMTPHeader;

import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import java.io.IOException;
import java.io.Writer;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;

public class Ej4 {
    public static void main(String[] args) throws IOException, UnrecoverableKeyException, KeyStoreException, NoSuchAlgorithmException {

        AuthenticatingSMTPClient client = new AuthenticatingSMTPClient();
        String server = "smtp.gmail.com";
        String user = "manuel.bernaldezcarrasco@iesvalleinclan.es";
        String password = "password";
        int puerto = 587;
        String remitente = "antonio.pinedatoro@iesvalleinclan.es";

        try {
            int respuesta;

            KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
            kmf.init(null, null);
            KeyManager km = kmf.getKeyManagers()[0];

            client.connect(server, puerto);
            System.out.println("1 - "+ client.getReplyString());

            client.setKeyManager(km);

            respuesta = client.getReplyCode();

            if (!SMTPReply.isPositiveCompletion(respuesta)) {
                client.disconnect();
                System.err.println("CONEXION RECHAZADA");
                System.exit(1);
            }


            client.ehlo(server);
            System.out.println("2 - "+ client.getReplyString());

            if (client.execTLS()){
                System.out.println("3 - "+ client.getReplyString());

                if (client.auth(AuthenticatingSMTPClient.AUTH_METHOD.PLAIN,user,password)){
                    System.out.println("4 - "+ client.getReplyString());
                    String destino1 = "traditionalland@gmail.com";
                    String asunto = "prueba con gmail";
                    String mensaje = "esto es una prueba";


                    SimpleSMTPHeader cabecera = new SimpleSMTPHeader(remitente,destino1,asunto);

                    client.setSender(remitente);
                    client.addRecipient(asunto);
                    System.out.println("5 - "+ client.getReplyString());

                    Writer writer = client.sendMessageData();

                    if (writer != null){
                        System.out.println("FALLO AL ENVIAR");
                        System.exit(1);
                    }

                    writer.write(cabecera.toString());
                    writer.write(mensaje);
                    writer.close();
                    System.out.println("6 - "+ client.getReplyString());

                    boolean exito = client.completePendingCommand();
                    System.out.println("7 - "+ client.getReplyString());

                    if (!exito){
                    System.out.println("FALLO AL FINALIZAR TRANSACCION");
                    System.exit(1);
                    }else
                        System.out.println("MENSAJE ENVIADO CON EXITO.....");

                }else
                    System.out.println("USUARIO NO AUTENTICADO");

            }else
                System.out.println("FALLO AL EJECUTAR STARTTLS.");

        }catch (IOException e){
            System.out.println("could not connect to server");
            e.printStackTrace();
            System.exit(1);
        }
        try {
            client.disconnect();
        }catch (IOException e){
            e.printStackTrace();
        }
        System.out.println("Fin envio");
        System.exit(0);
    }
}
