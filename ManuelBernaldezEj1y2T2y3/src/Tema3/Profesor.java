package Tema3;

import java.io.Serializable;
import java.util.Arrays;
//Se implementa serializable para poder manejar los objetos por el servidor y cliente
public class Profesor implements Serializable {
    //creamos los atibutos de profesor creando constructor y getters y setters

    int idProfesor;
    String nombre;
    Asignatura asignatura[] = new Asignatura[3];
    Especialidad especialidad;

    public Profesor(int idProfesor, String nombre, Asignatura[] asignatura, Especialidad especialidad) {
        this.idProfesor = idProfesor;
        this.nombre = nombre;
        this.asignatura = asignatura;
        this.especialidad = especialidad;
    }

    public Profesor() {
    }

    public int getIdProfesor() {
        return idProfesor;
    }

    public void setIdProfesor(int idProfesor) {
        this.idProfesor = idProfesor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Asignatura[] getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(Asignatura asignatura) {
        this.asignatura = new Asignatura[]{asignatura};
    }

    public Especialidad getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(Especialidad especialidad) {
        this.especialidad = especialidad;
    }

    @Override
    public String toString() {
        return "Profesor{" +
                "idProfesor=" + idProfesor +
                ", nombre='" + nombre + '\'' +
                ", asignatura=" + Arrays.toString(asignatura) +
                ", especialidad=" + especialidad +
                '}';
    }
}
