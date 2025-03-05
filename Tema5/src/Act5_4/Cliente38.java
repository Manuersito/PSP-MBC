package Act5_4;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.TrustManagerFactory;
import  java.io.*;
import java.net.*;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

public class Cliente38 {
    public static void main(String[] args) throws IOException, KeyStoreException, CertificateException, NoSuchAlgorithmException, KeyManagementException {
        String host = "localhost";
        int port = 3008;

        //fichero con certificado de confianza
        FileInputStream ficCerConf = new FileInputStream("src/Act5_4/miTruststore.jks");
        String ClaveCerConf = "969696";

        //cargar en keystore certificado confianza
        KeyStore almacenConf = KeyStore.getInstance(KeyStore.getDefaultType());
        almacenConf.load(ficCerConf, ClaveCerConf.toCharArray());

        //crear gestor de confianza
        TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        tmf.init(almacenConf);

        //creacion de contexto
        SSLContext sc = SSLContext.getInstance("TLS");
        sc.init(null, tmf.getTrustManagers(), null);
        SSLSocket cliente = (SSLSocket) sc.getSocketFactory().createSocket(host, port);


        try (DatagramSocket socket = new DatagramSocket()) {
            InetAddress address = InetAddress.getByName(host);

            // Crear objeto Persona
            Persona persona = new Persona("Juan", 25);
            System.out.println("Enviando objeto: " + persona);

            // Serializar objeto
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(persona);
            oos.flush();

            byte[] buffer = baos.toByteArray();
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, port);
            socket.send(packet);
            System.out.println("Objeto enviado al servidor.");

            // Recibir objeto modificado
            byte[] respuestaBuffer = new byte[1024];
            DatagramPacket respuestaPacket = new DatagramPacket(respuestaBuffer, respuestaBuffer.length);
            socket.receive(respuestaPacket);

            ByteArrayInputStream bais = new ByteArrayInputStream(respuestaPacket.getData());
            ObjectInputStream ois = new ObjectInputStream(bais);
            Persona personaModificada = (Persona) ois.readObject();

            System.out.println("Objeto recibido del servidor: " + personaModificada);
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error en el cliente: " + e.getMessage());
        }
    }
}
