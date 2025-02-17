package Tema3;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    public static void main(String[] args) throws IOException {

        //El ejercicio lo entendi como que tienes que crear varias clases las cuales
        //se inician el el servidor TCP para que sea correcta la comunicacion y no perder
        //informacion, usando los ObjectoutoutStream pasamos la informacion desde el servidor
        //hacia el cliente y alli los leemos, en el cliente lo unico que hace es introducir
        //las id de los profesores hasta enviar un *, se pueden conectar todos los clientes
        //que quieran y para ello se hace con hilos.

        //No funciona ya que no me dio tiempo de terminar el ejercicio


        //creacion asignaturas
        Asignatura asig1 = new Asignatura(1,"PSP");
        Asignatura asig2 = new Asignatura(2,"HLC");
        Asignatura asig3 = new Asignatura(3,"DIU");
        Asignatura asig4 = new Asignatura(4,"SGE");
        Asignatura asig5 = new Asignatura(5,"FCT");

        Asignatura asignaturas1[] = new Asignatura[3];
        asignaturas1[0]=asig1;
        asignaturas1[1]=asig2;

        Asignatura asignaturas2[] = new Asignatura[3];
        asignaturas2[0]=asig3;

        Asignatura asignaturas3[] = new Asignatura[3];
        asignaturas3[0]=asig4;
        asignaturas3[1]=asig5;

        //creacion especialidades
        Especialidad esp1 = new Especialidad(1,"Servidor");
        Especialidad esp2 = new Especialidad(2,"odoo");
        Especialidad esp3 = new Especialidad(3,"React");


        //creacion profesores
        Profesor prof1 = new Profesor(1,"Antonio",asignaturas1,esp1);
        Profesor prof2 = new Profesor(2,"Rafael",asignaturas3,esp2);
        Profesor prof3 = new Profesor(3,"Jacobo",asignaturas2,esp3);
        Profesor prof4 = new Profesor(2,"Julian",asignaturas1,esp3);
        Profesor prof5 = new Profesor(3,"Miguel",asignaturas2,esp2);

        //inicializacion de array profesor
        Profesor profesor[] = new Profesor[5];
        profesor[0]=prof1;
        profesor[1]=prof2;
        profesor[2]=prof3;
        profesor[3]=prof4;
        profesor[4]=prof5;

        int puerto = 1234;
        ServerSocket servidor = new ServerSocket(puerto);
        System.out.println("Servidor conectado esperando cliente.");
        Socket cliente = servidor.accept();


        ObjectOutputStream out = new ObjectOutputStream(cliente.getOutputStream());






    }


}
