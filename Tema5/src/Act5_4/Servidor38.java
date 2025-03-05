package Act5_4;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import java.io.*;
import java.net.*;
import java.security.*;
import java.security.cert.CertificateException;

public class Servidor38 {
    public static void main(String[] args) throws IOException, KeyStoreException, CertificateException, NoSuchAlgorithmException, UnrecoverableKeyException, KeyManagementException {
       int puerto = 3008;

        //Fichero almacen con certificado
        FileInputStream ficAlmacen = new FileInputStream("src/Act5_4/miAlmacen.jks");
        String claveAlmacen = "696969";

        //cargar keyStore
        KeyStore almacen = KeyStore.getInstance(KeyStore.getDefaultType());
        almacen.load(ficAlmacen,claveAlmacen.toCharArray());

        //gestor de claves
        KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
        kmf.init(almacen,claveAlmacen.toCharArray());

        //contexto con TSL
        SSLContext sc = SSLContext.getInstance("TLS");
        sc.init(kmf.getKeyManagers(),null,null);

        //creacion socket ssl
        SSLServerSocketFactory sfact = sc.getServerSocketFactory();
        SSLServerSocket ss = (SSLServerSocket) sfact.createServerSocket(puerto);


        try (DatagramSocket socket = new DatagramSocket(puerto)) {
            System.out.println("Servidor UDP escuchando en el puerto 3008...");

            byte[] buffer = new byte[1024];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

            // Recibir datagrama
            socket.receive(packet);
            System.out.println("Datagrama recibido del cliente.");

            // Deserializar objeto
            ByteArrayInputStream bais = new ByteArrayInputStream(packet.getData());
            ObjectInputStream ois = new ObjectInputStream(bais);
            Persona persona = (Persona) ois.readObject();
            System.out.println("Objeto recibido: " + persona);

            // Modificar objeto
            persona.setEdad(persona.getEdad() + 10);
            persona.setNombre(persona.getNombre() + " (Modificado)");

            System.out.println("Objeto modificado: " + persona);

            // Serializar objeto y enviarlo de vuelta
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(persona);
            oos.flush();

            byte[] respuesta = baos.toByteArray();
            DatagramPacket respuestaPacket = new DatagramPacket(
                    respuesta, respuesta.length, packet.getAddress(), packet.getPort()
            );
            socket.send(respuestaPacket);
            System.out.println("Objeto modificado enviado al cliente.");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error en el servidor: " + e.getMessage());
        }
    }
}
