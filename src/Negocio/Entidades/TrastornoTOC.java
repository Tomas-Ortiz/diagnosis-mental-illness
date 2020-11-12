package Negocio.Entidades;

import java.util.ArrayList;

public class TrastornoTOC extends Trastorno {

    private static TrastornoTOC trastornoTOC;

    public static TrastornoTOC getTrastornoTOC() {
        if (trastornoTOC == null) {
            trastornoTOC = new TrastornoTOC();
            trastornoTOC.setSintomas(new ArrayList<>());
        }
        return trastornoTOC;
    }
}
