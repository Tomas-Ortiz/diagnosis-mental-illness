package Negocio;

import Negocio.Entidades.ConsultaPaciente;
import Negocio.Entidades.Paciente;
import Negocio.Entidades.Sintoma;
import Negocio.Entidades.TrastornoAnsiedad;
import Negocio.Entidades.TrastornoBipolaridad;
import Negocio.Entidades.TrastornoDepresion;
import Negocio.Entidades.TrastornoTOC;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class NegocioConsultaPaciente {

    private ConsultaPaciente consultaPaciente;
    private static FileWriter fw;
    private static BufferedWriter bw;
    private int cont = 0;

    public NegocioConsultaPaciente(ConsultaPaciente consultaPaciente) {
        this.consultaPaciente = consultaPaciente;
        agregarTrastornos();
    }

    public void agregarTrastornos() {
        // Se obtienen los trastornos ya creados y seteados por el motor, con las respuestas en false por defecto
        consultaPaciente.getRespuestasSintomas().add(TrastornoAnsiedad.getTrastornoAnsiedad());
        consultaPaciente.getRespuestasSintomas().add(TrastornoDepresion.getTrastornoDepresion());
        consultaPaciente.getRespuestasSintomas().add(TrastornoTOC.getTrastornoTOC());
        consultaPaciente.getRespuestasSintomas().add(TrastornoBipolaridad.getTrastornoBipolaridad());
    }

    public void setRespuestaSintomaTrastorno(boolean respuesta, int indiceTrastorno, int indiceSintoma) {
        consultaPaciente.getRespuestasSintomas().get(indiceTrastorno).getSintomas().get(indiceSintoma).setRespuesta(respuesta);
    }

    public Sintoma getSintomaTrastorno(int indiceTrastorno, int indiceSintoma) {
        return consultaPaciente.getRespuestasSintomas().get(indiceTrastorno).getSintomas().get(indiceSintoma);
    }

    public void agregarDiagnosticoPaciente(String diagnostico) {
        consultaPaciente.getDiagnostico().add(diagnostico);
    }

    public void eliminarDiagnostico(String diagnostico) {
        consultaPaciente.getDiagnostico().remove(diagnostico);
    }

    public void verificarDiagnosticoSano() {
        if (consultaPaciente.getDiagnostico().contains("Sano") && consultaPaciente.getDiagnostico().size() > 1) {
            eliminarDiagnostico("Sano");
        }
    }

    // Se inserta paciente, sintomas y diagnóstico
    public void guardarConsulta() {
        try {
            fw = new FileWriter("consultas/Consultas de pacientes.txt");
            bw = new BufferedWriter(fw);
            
            bw.write("===========================");
            bw.newLine();
            bw.append("CONSULTAS DE PACIENTES");
            bw.newLine();
            bw.append("===========================");
            bw.newLine();

            bw.append("FECHA: " + UtilidadGeneral.getFechaActual());
            bw.newLine();
            bw.append("HORA: " + UtilidadGeneral.getHoraActual());
            bw.newLine();

            bw.append("===========================");
            bw.newLine();
            bw.append("PACIENTE");
            bw.newLine();
            bw.append("===========================");
            bw.newLine();
            insertarPaciente(consultaPaciente.getPaciente());

            bw.append("===========================");
            bw.newLine();
            bw.append("SINTOMAS");
            bw.newLine();
            bw.append("===========================");
            bw.newLine();

            bw.append("===========================================");
            bw.newLine();
            bw.append(consultaPaciente.getRespuestasSintomas().get(0).getNombre());
            bw.newLine();
            bw.append("===========================================");
            bw.newLine();
            insertarSintomas(consultaPaciente.getRespuestasSintomas().get(0).getSintomas());

            bw.append("===========================================");
            bw.newLine();
            bw.append(consultaPaciente.getRespuestasSintomas().get(1).getNombre());
            bw.newLine();
            bw.append("===========================================");
            bw.newLine();
            insertarSintomas(consultaPaciente.getRespuestasSintomas().get(1).getSintomas());

            bw.append("===========================================");
            bw.newLine();
            bw.append(consultaPaciente.getRespuestasSintomas().get(2).getNombre());
            bw.newLine();
            bw.append("===========================================");
            bw.newLine();
            insertarSintomas(consultaPaciente.getRespuestasSintomas().get(2).getSintomas());

            bw.append("===========================================");
            bw.newLine();
            bw.append(consultaPaciente.getRespuestasSintomas().get(3).getNombre());
            bw.newLine();
            bw.append("===========================================");
            bw.newLine();
            insertarSintomas(consultaPaciente.getRespuestasSintomas().get(3).getSintomas());

            bw.append("===========================");
            bw.newLine();
            bw.append("DIAGNÓSTICO");
            bw.newLine();
            bw.append("===========================");
            bw.newLine();
            insertarDiagnostico(consultaPaciente.getDiagnostico());

            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fw != null) {
                try {
                    fw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void insertarPaciente(Paciente paciente) {
        try {
            bw.append("Paciente: " + paciente.getApellido() + ", " + paciente.getNombre());
            bw.newLine();
            bw.append("País: " + paciente.getPais());
            bw.newLine();
            bw.append("Sexo: " + paciente.getSexo());
            bw.newLine();
            bw.append("Edad: " + paciente.getEdad());
            bw.newLine();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void insertarSintomas(ArrayList<Sintoma> sintomasTrastorno) {
        cont = 0;
        for (Sintoma sint : sintomasTrastorno) {
            try {
                bw.append(++cont + ". " + sint.getPregunta() + ": " + sint.getRespuesta());
                bw.newLine();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    private void insertarDiagnostico(ArrayList<String> diagnosticos) {
        cont = 0;
        for (String diagnostico : diagnosticos) {
            try {
                bw.append(++cont + ". " + diagnostico);
                bw.newLine();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}
