package Ejercicio3;

public class Alumno {
    private String idAlumno;
    private String nombre;
    private Curso curso;
    private int nota;

    public Alumno(String idAlumno, String nombre, Curso curso, int nota) {
        this.idAlumno = idAlumno;
        this.nombre = nombre;
        this.curso = curso;
        this.nota = nota;
    }

    public String getIdAlumno() {
        return idAlumno;
    }

    public String getNombre() {
        return nombre;
    }

    public Curso getCurso() {
        return curso;
    }

    public int getNota() {
        return nota;
    }

    @Override
    public String toString() {
        return "Alumno [ID=" + idAlumno + ", Nombre=" + nombre + ", " + curso + ", Nota=" + nota + "]";
    }
}
