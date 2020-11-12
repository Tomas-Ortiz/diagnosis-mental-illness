package Negocio;

import Presentacion.FrmPrincipal;
import jess.Fact;
import jess.JessEvent;
import jess.JessException;
import jess.JessListener;
import jess.Rete;

public class ManejadorEvento implements JessListener {

    private FrmPrincipal vista;
    private Ayudante ayudante;

    public ManejadorEvento(FrmPrincipal vista) {
        this.vista = vista;
    }

    @Override
    public void eventHappened(JessEvent je) throws JessException {
        // A partir de un evento Jess, se obtiene el tipo de evento y la fuente
        int tipo = je.getType();
        Rete rete = (Rete) je.getSource();
        ayudante = new Ayudante(rete);
        String nombreHecho;
        // Cuando se ejecuta un run (después de agregar un sintoma) se verifica 
        // si hay hechos equivalentes a los diagnósticos de algún trastorno
        if (tipo == JessEvent.RUN) {
            nombreHecho = "MAIN::indicios_ansiedad";
            buscarHechoPorNombre(nombreHecho);

            nombreHecho = "MAIN::trastorno_ansiedad";
            buscarHechoPorNombre(nombreHecho);

            nombreHecho = "MAIN::indicios_depresion";
            buscarHechoPorNombre(nombreHecho);

            nombreHecho = "MAIN::trastorno_depresion";
            buscarHechoPorNombre(nombreHecho);

            nombreHecho = "MAIN::trastorno_obsesivo";
            buscarHechoPorNombre(nombreHecho);

            nombreHecho = "MAIN::trastorno_compulsivo";
            buscarHechoPorNombre(nombreHecho);

            nombreHecho = "MAIN::trastorno_obsesivo_compulsivo";
            buscarHechoPorNombre(nombreHecho);

            nombreHecho = "MAIN::mania_hipomania";
            buscarHechoPorNombre(nombreHecho);

            nombreHecho = "MAIN::depresion_mayor";
            buscarHechoPorNombre(nombreHecho);

            nombreHecho = "MAIN::trastorno_bipolaridad";
            buscarHechoPorNombre(nombreHecho);

            nombreHecho = "MAIN::sano";
            buscarHechoPorNombre(nombreHecho);
        }
    }

    private void buscarHechoPorNombre(String nombreHecho) {
        String respuesta;
        Fact respuestaTrastorno = ayudante.buscarHechoPorNombre(nombreHecho);
        if (respuestaTrastorno != null) {
            try {
                respuesta = respuestaTrastorno.get(0).toString();
                enviarRespuestaVista(respuesta);
            } catch (JessException ex) {
                System.out.println("Error al obtener la respuesta " + ex.getMessage());
            }
        }
    }

    private void enviarRespuestaVista(String respuesta) {
        vista.agregarRespuesta(respuesta);
    }
}
