package Negocio.Entidades;

import java.util.ArrayList;

public class TrastornoBipolaridad extends Trastorno {

    private static TrastornoBipolaridad trastornoBipolaridad;

    public static TrastornoBipolaridad getTrastornoBipolaridad() {
        if (trastornoBipolaridad == null) {
            trastornoBipolaridad = new TrastornoBipolaridad();
            trastornoBipolaridad.setSintomas(new ArrayList<>());
        }
        return trastornoBipolaridad;
    }
}
