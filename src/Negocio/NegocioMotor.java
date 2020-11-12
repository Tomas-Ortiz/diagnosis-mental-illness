package Negocio;

import Negocio.Entidades.Sintoma;
import Negocio.Entidades.Trastorno;
import Negocio.Entidades.TrastornoAnsiedad;
import Negocio.Entidades.TrastornoBipolaridad;
import Negocio.Entidades.TrastornoDepresion;
import Negocio.Entidades.TrastornoTOC;
import java.util.Iterator;
import jess.Activation;
import jess.Deffacts;
import jess.Defrule;
import jess.Fact;
import jess.JessEvent;
import jess.JessException;
import jess.Rete;

public class NegocioMotor {

    private final Rete motor;

    public NegocioMotor() throws JessException {
        motor = new Rete();
        motor.batch("../Clips/rules.clp");
        motor.reset();
        System.out.println("============================================");
        System.out.println("Motor de inferencia iniciado correctamente...");
        System.out.println("============================================");
        System.out.println("============== Deffacts ==============");
        listarDeffacts();
        System.out.println("============== Facts ==============");
        listarHechos();
        System.out.println("============== Rules ==============");
        listarReglas();
        System.out.println("===================================");
    }

    public void agregarSintoma(Sintoma sintoma) throws JessException {
        motor.add(sintoma);
        //listarHechos();
        //listarActivacionesReglas();
        // Con un run se ejecuta la regla
        // Con el otro, se obtiene el hecho (JessEvent)
        motor.run();
        motor.run();
    }

    public void ejecutar() throws JessException {
        System.out.println("============================================");
        System.out.println("Ejecutando...");
        System.out.println("============================================");
        motor.run();
    }

    public void agregarEscuchador(ManejadorEvento eventHandler) {
        motor.addJessListener(eventHandler);
        motor.setEventMask(JessEvent.RUN);
    }

    public void listarDeffacts() {
        Iterator it = motor.listDeffacts();
        while (it.hasNext()) {
            Deffacts deffact = (Deffacts) it.next();
            System.out.println(deffact.getName());
        }
    }

    public void listarActivacionesReglas() throws JessException {
        Iterator it = motor.listActivations();
        while (it.hasNext()) {
            Activation activation = (Activation) it.next();
            System.out.println(activation.getRule());
        }
    }

    public void listarHechos() throws JessException {
        Iterator it = motor.listFacts();
        while (it.hasNext()) {
            Fact fact = (Fact) it.next();
            switch (fact.getName()) {
                case "MAIN::Sintoma":
                    System.out.println(fact.getSlotValue("enfermedad").toString() + ", " + fact.getSlotValue("nombre").toString()
                            + "," + fact.getSlotValue("respuesta").toString());
                    break;
                default:
                    System.out.println(fact.getName());
                    break;
            }
        }
    }

    public void listarReglas() {
        Iterator it = motor.listDefrules();
        while (it.hasNext()) {
            Defrule defrule = (Defrule) it.next();
            System.out.println(defrule.getName());
        }
    }

    public void cargarTrastornosFromClips() {
        Trastorno trastornoAnsiedad = TrastornoAnsiedad.getTrastornoAnsiedad();
        trastornoAnsiedad.setNombre("Trastorno de ansiedad");
        Trastorno trastornoDepresion = TrastornoDepresion.getTrastornoDepresion();
        trastornoDepresion.setNombre("Trastorno de depresión");
        Trastorno trastornoTOC = TrastornoTOC.getTrastornoTOC();
        trastornoTOC.setNombre("Trastorno TOC");
        Trastorno trastornoBipolaridad = TrastornoBipolaridad.getTrastornoBipolaridad();
        trastornoBipolaridad.setNombre("Trastorno de bipolaridad");
        trastornoAnsiedad.getSintomas().clear();
        trastornoDepresion.getSintomas().clear();
        trastornoTOC.getSintomas().clear();
        trastornoBipolaridad.getSintomas().clear();

        Sintoma sintoma = null;
        String enfermedad = "";

        Iterator it = motor.listFacts();

        while (it.hasNext()) {
            Fact fact = (Fact) it.next();
            if (fact.getName().equals("MAIN::Sintoma")) {
                try {
                    // respuesta por defecto en falso
                    enfermedad = fact.getSlotValue("enfermedad").toString();
                    sintoma = new Sintoma(enfermedad, fact.getSlotValue("nombre").toString(), fact.getSlotValue("pregunta").toString(), false);
                } catch (JessException ex) {
                    System.out.println(ex.getMessage());
                }
                // Se quitan las comillas del string
                enfermedad = enfermedad.substring(1, enfermedad.length() - 1);
                switch (enfermedad) {
                    case "trastorno_ansiedad":
                        trastornoAnsiedad.getSintomas().add(sintoma);
                        break;
                    case "trastorno_depresion":
                        trastornoDepresion.getSintomas().add(sintoma);
                        break;
                    case "trastorno_TOC":
                        trastornoTOC.getSintomas().add(sintoma);
                        break;
                    case "trastorno_bipolar":
                        trastornoBipolaridad.getSintomas().add(sintoma);
                        break;
                }
            }
        }
    }
}
