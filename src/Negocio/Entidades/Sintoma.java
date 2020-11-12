package Negocio.Entidades;

public class Sintoma {

    private String nombre, enfermedad, pregunta;
    private boolean respuesta;

    public Sintoma() {
    }

    public Sintoma(String enfermedad, String nombre, String pregunta, boolean respuesta) {
        this.enfermedad = enfermedad;
        this.nombre = nombre;
        this.pregunta = pregunta;
        this.respuesta = respuesta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public boolean getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(boolean respuesta) {
        this.respuesta = respuesta;
    }

    public String getEnfermedad() {
        return enfermedad;
    }

    public void setEnfermedad(String enfermedad) {
        this.enfermedad = enfermedad;
    }

    @Override
    public String toString() {
        return "Sintoma{" + "enfermedad=" + enfermedad + ", nombre=" + nombre + ", pregunta=" + pregunta + ", respuesta=" + respuesta + '}';
    }
}
