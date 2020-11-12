package Negocio;

import java.util.Iterator;
import jess.Fact;
import jess.Rete;

public class Ayudante {

    private Rete motor;

    public Ayudante(Rete motor) {
        this.motor = motor;
    }

    //Busca y retorna un hecho dado el nombre de un hecho
    public Fact buscarHechoPorNombre(String nombreHecho) {
        Fact fact = null;
        Iterator it = motor.listFacts();
        while (it.hasNext()) {
            Fact f = (Fact) it.next();
            if (f.getName().equals(nombreHecho)) {
                fact = f;
                break;
            }
        }
        return fact;
    }
}
