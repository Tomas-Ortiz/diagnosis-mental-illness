package Negocio;

import Negocio.Entidades.TrastornoAnsiedad;
import Negocio.Entidades.TrastornoBipolaridad;
import Negocio.Entidades.TrastornoDepresion;
import Negocio.Entidades.TrastornoTOC;
import Presentacion.FrmPrincipal;

public class NegocioPregunta {

    // Se utilizan los tamaños de los arrays y contadores
    private int tamañoSintomasAnsiedad, tamañoSintomasDepresion, tamañoSintomasTOC, tamañoSintomasBipo;
    private int contPregSintomaAnsiedad = 0, contPregSintomaDepresion = -1, contPregSintomaTOC = -1, contPregSintomaBipo = -1;
    private FrmPrincipal vista;
    private static NegocioPregunta negocioPregunta;

    private NegocioPregunta(FrmPrincipal vista) {
        this.vista = vista;
        setTamañosArraysSintomas();
    }

    public static NegocioPregunta getNegocioPregunta(FrmPrincipal vista) {
        if (negocioPregunta == null) {
            negocioPregunta = new NegocioPregunta(vista);
        }
        return negocioPregunta;
    }

    private void setTamañosArraysSintomas() {
        tamañoSintomasAnsiedad = TrastornoAnsiedad.getTrastornoAnsiedad().getSintomas().size();
        tamañoSintomasDepresion = TrastornoDepresion.getTrastornoDepresion().getSintomas().size();
        tamañoSintomasTOC = TrastornoTOC.getTrastornoTOC().getSintomas().size();
        tamañoSintomasBipo = TrastornoBipolaridad.getTrastornoBipolaridad().getSintomas().size();
    }

    // Para el manejo de las preguntas y trastornos se utilizan contadores y tamaños de arrays
    // Por cada pregunta mostrada de cada trastorno se incrementa su contador
    // Si el contador iguala el tamaño del array entonces se recorrieron todas las preguntas de ese trastorno
    // Y se pasa al siguiente trastorno
    // El -1 indica que los sintomas de ese trastorno no se deben mostrar
    // Por defecto, comienza con los sintomas de ansiedad
    public void determinarSiguientePreguntaOrTrastorno() {

        if (contPregSintomaAnsiedad == tamañoSintomasAnsiedad) {
            contPregSintomaAnsiedad = -1;
            contPregSintomaDepresion = 0;
        } else if (contPregSintomaAnsiedad > -1) {
            vista.mostrarTrastorno(TrastornoAnsiedad.getTrastornoAnsiedad().getNombre());
            enviarIndicesVista(0, contPregSintomaAnsiedad);
            vista.mostrarPregunta(TrastornoAnsiedad.getTrastornoAnsiedad().getSintomas().get(contPregSintomaAnsiedad++).getPregunta());
        }

        if (contPregSintomaDepresion == tamañoSintomasDepresion) {
            contPregSintomaDepresion = -1;
            contPregSintomaTOC = 0;
        } else if (contPregSintomaDepresion > -1) {
            vista.mostrarTrastorno(TrastornoDepresion.getTrastornoDepresion().getNombre());
            enviarIndicesVista(1, contPregSintomaDepresion);
            vista.mostrarPregunta(TrastornoDepresion.getTrastornoDepresion().getSintomas().get(contPregSintomaDepresion++).getPregunta());
        }
        if (contPregSintomaTOC == tamañoSintomasTOC) {
            contPregSintomaTOC = -1;
            contPregSintomaBipo = 0;
        } else if (contPregSintomaTOC > -1) {
            vista.mostrarTrastorno(TrastornoTOC.getTrastornoTOC().getNombre());
            enviarIndicesVista(2, contPregSintomaTOC);
            vista.mostrarPregunta(TrastornoTOC.getTrastornoTOC().getSintomas().get(contPregSintomaTOC++).getPregunta());
        }
        if (contPregSintomaBipo == tamañoSintomasBipo) {
            contPregSintomaBipo = -1;
        } else if (contPregSintomaBipo > -1) {
            vista.mostrarTrastorno(TrastornoBipolaridad.getTrastornoBipolaridad().getNombre());
            enviarIndicesVista(3, contPregSintomaBipo);
            vista.mostrarPregunta(TrastornoBipolaridad.getTrastornoBipolaridad().getSintomas().get(contPregSintomaBipo++).getPregunta());
        }
        // Si los sintomas de todos los trastornos fueron mostrados
        if (contPregSintomaAnsiedad == -1 && contPregSintomaDepresion == -1 && contPregSintomaTOC == -1 && contPregSintomaBipo == -1) {
            vista.finalizarDiagnostico();
        }
    }

    // Comienza por defecto a preguntar los sintomas de ansiedad
    public void reiniciarPreguntas() {
        contPregSintomaAnsiedad = 0;
        determinarSiguientePreguntaOrTrastorno();
    }

    // La vista requiere saber el sintoma y trastorno actual
    // para establecer la respuesta al sintoma correcto
    public void enviarIndicesVista(int indiceTrastorno, int indiceSintoma) {
        vista.setIndiceTrastorno(indiceTrastorno);
        vista.setIndiceSintoma(indiceSintoma);
    }

    // Si se responde que no, se salta al siguiente árbol o rama
    public void pasarSiguienteRamaOrArbol() {
        if (contPregSintomaAnsiedad > -1 && contPregSintomaAnsiedad < tamañoSintomasAnsiedad) {
            contPregSintomaAnsiedad = -1;
            contPregSintomaDepresion = 0;
        } else if (contPregSintomaDepresion > -1 && contPregSintomaDepresion < tamañoSintomasDepresion) {
            contPregSintomaDepresion = -1;
            contPregSintomaTOC = 0;
        } else if (contPregSintomaTOC > -1 && contPregSintomaTOC < tamañoSintomasTOC) {
            if (contPregSintomaTOC < 4) {
                contPregSintomaTOC = 4;
            } else {
                contPregSintomaTOC = -1;
                contPregSintomaBipo = 0;
            }
        } else if (contPregSintomaBipo > -1 && contPregSintomaBipo < tamañoSintomasBipo) {
            if (contPregSintomaBipo < 6) {
                contPregSintomaBipo = 6;
            } else {
                contPregSintomaBipo = -1;
            }
        }
    }
}
