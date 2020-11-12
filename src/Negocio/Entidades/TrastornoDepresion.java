package Negocio.Entidades;

import java.util.ArrayList;

public class TrastornoDepresion extends Trastorno {

    private static TrastornoDepresion trastornoDepresion;

    public static TrastornoDepresion getTrastornoDepresion() {
        if (trastornoDepresion == null) {
            trastornoDepresion = new TrastornoDepresion();
            trastornoDepresion.setSintomas(new ArrayList<>());
        }
        return trastornoDepresion;
    }
}
