package Negocio;

public class NegocioPaciente {

    public NegocioPaciente() {
    }

    public boolean pacienteValido(String nombre, String apellido) {
        nombre = nombre.replace(" ", "");
        apellido = apellido.replace(" ", "");
        return !(nombre.isEmpty() || apellido.isEmpty()
                || !UtilidadGeneral.soloLetras(nombre) || !UtilidadGeneral.soloLetras(apellido));
    }
}
