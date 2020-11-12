package Negocio.Entidades;

import java.util.ArrayList;

public class TrastornoAnsiedad extends Trastorno {

    private static TrastornoAnsiedad trastornoAnsiedad;

    public static TrastornoAnsiedad getTrastornoAnsiedad() {
        if (trastornoAnsiedad == null) {
            trastornoAnsiedad = new TrastornoAnsiedad();
            trastornoAnsiedad.setSintomas(new ArrayList<>());
        }
        return trastornoAnsiedad;
    }
}
