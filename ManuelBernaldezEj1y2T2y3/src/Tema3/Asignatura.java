package Tema3;

import java.io.Serializable;
//Se implementa serializable para poder manejar los objetos por el servidor y cliente

public class Asignatura implements Serializable {
    //creamos los atibutos de asignatura creando constructor y getters y setters
    int id;
    String nombreAsignatura;

    public Asignatura(int id, String nombreAsignatura) {
        this.id = id;
        this.nombreAsignatura = nombreAsignatura;
    }

    public String getNombreAsignatura() {
        return nombreAsignatura;
    }

    public void setNombreAsignatura(String nombreAsignatura) {
        this.nombreAsignatura = nombreAsignatura;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Asignatura{" +
                "id=" + id +
                ", nombreAsignatura='" + nombreAsignatura + '\'' +
                '}';
    }
}
