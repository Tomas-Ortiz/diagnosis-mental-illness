package Negocio.Entidades;

public class Paciente {

    private String nombre, apellido, pais, sexo;
    private int edad;

    public Paciente() {
    }

    public Paciente(String nombre, String apellido, String pais, String sexo, int edad) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.pais = pais;
        this.sexo = sexo;
        this.edad = edad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    @Override
    public String toString() {
        return "Paciente{" + "nombre=" + nombre + ", apellido=" + apellido + ", pais=" + pais + ", sexo=" + sexo + ", edad=" + edad + '}';
    }
}
