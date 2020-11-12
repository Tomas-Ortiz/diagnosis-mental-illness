package Negocio.Entidades;

import java.util.ArrayList;

public abstract class Trastorno {

    protected String nombre;
    protected ArrayList<Sintoma> sintomas;

    public Trastorno() {
    }

    public Trastorno(String nombre, ArrayList<Sintoma> sintomas) {
        this.nombre = nombre;
        this.sintomas = sintomas;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Sintoma> getSintomas() {
        return sintomas;
    }

    public void setSintomas(ArrayList<Sintoma> sintomas) {
        this.sintomas = sintomas;
    }

    public void mostrarSintomas() {
        for (Sintoma sintoma : sintomas) {
            System.out.println(sintoma);
        }
    }

    @Override
    public String toString() {
        return "Trastorno{" + "nombre=" + nombre + ", sintomas=" + sintomas + '}';
    }
}
