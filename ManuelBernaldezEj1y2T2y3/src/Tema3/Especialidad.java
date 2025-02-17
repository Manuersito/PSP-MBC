package Tema3;

import java.io.Serializable;
//Se implementa serializable para poder manejar los objetos por el servidor y cliente

public class Especialidad implements Serializable {
    //creamos los atibutos de especialidad creando constructor y getters y setters

    int id;
    String nombreEspecialidad;

    public Especialidad(int id, String nombreEspecialidad) {
        this.id = id;
        this.nombreEspecialidad = nombreEspecialidad;
    }

    public String getNombreEspecialidad() {
        return nombreEspecialidad;
    }

    public void setNombreEspecialidad(String nombreEspecialidad) {
        this.nombreEspecialidad = nombreEspecialidad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Especialidad{" +
                "id=" + id +
                ", nombreEspecialidad='" + nombreEspecialidad + '\'' +
                '}';
    }
}
