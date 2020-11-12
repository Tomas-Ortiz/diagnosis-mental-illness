package Negocio.Entidades;

import java.util.ArrayList;

public class ConsultaPaciente {

    private Paciente paciente;
    private ArrayList<Trastorno> respuestasSintomas = new ArrayList<>();
    private ArrayList<String> diagnostico = new ArrayList<>();

    public ConsultaPaciente() {
    }

    public ConsultaPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public ArrayList<String> getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(ArrayList<String> diagnostico) {
        this.diagnostico = diagnostico;
    }

    public ArrayList<Trastorno> getRespuestasSintomas() {
        return respuestasSintomas;
    }

    public void setRespuestasSintomas(ArrayList<Trastorno> respuestasSintomas) {
        this.respuestasSintomas = respuestasSintomas;
    }

    @Override
    public String toString() {
        return "ConsultaPaciente{" + "paciente=" + paciente + ", respuestasSintomas=" + respuestasSintomas + ", diagnostico=" + diagnostico + '}';
    }

}
