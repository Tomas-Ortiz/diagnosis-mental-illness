package Presentacion;

import Negocio.Entidades.ConsultaPaciente;
import Negocio.NegocioMotor;
import Negocio.Entidades.Paciente;
import Negocio.Entidades.Sintoma;
import Negocio.ManejadorEvento;
import Negocio.NegocioConsultaPaciente;
import Negocio.NegocioPaciente;
import Negocio.NegocioPregunta;
import Negocio.UtilidadGeneral;
import java.awt.Color;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import jess.JessException;

public class FrmPrincipal extends javax.swing.JFrame {

    private String mensaje, titulo;
    private NegocioMotor negocioMotor;
    private ManejadorEvento eventHandler;
    private NegocioPaciente negocioPaciente;
    private Paciente paciente;
    private ConsultaPaciente consultaPaciente;
    private NegocioPregunta negocioPregunta;
    private NegocioConsultaPaciente negocioConsultaPaciente;
    private int indiceTrastorno, indiceSintoma;

    public FrmPrincipal() {
        initComponents();
        configurarJFrame();
        activarPanelDatosPaciente();
        mostrarIcono("src/Iconos/cerebro.png", lblIconoTitulo);
        mostrarListaPaises();
        negocioPaciente = new NegocioPaciente();
    }

    private void configurarJFrame() {
        this.setTitle("Diagnóstico de enfermedades mentales");
        this.setSize(680, 680);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.getContentPane().setBackground(Color.WHITE);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void iniciarMotor() {
        try {
            negocioMotor = new NegocioMotor();
        } catch (JessException ex) {
            System.out.println(ex.getMessage());
        }
        eventHandler = new ManejadorEvento(this);
        negocioMotor.agregarEscuchador(eventHandler);
    }

    private void limpiarDatosPaciente() {
        this.jtfNombre.setText("");
        this.jtfApellido.setText("");
        this.jcbPais.setSelectedIndex(0);
        this.jcbSexo.setSelectedIndex(0);
        this.jsEdad.setValue(1);
    }

    private void activarPanelPreguntasRespuestas() {
        jpDatosPaciente.setVisible(false);
        jpPreguntasRespuestas.setVisible(true);
        jtaResultados.setEditable(false);
        mostrarPaciente();
        configurarBotonComenzar();
        iniciarDiagnostico();
    }

    private void mostrarPaciente() {
        lblPaciente.setText("<html><b>Paciente: </b>" + paciente.getApellido() + ", " + paciente.getNombre() + "</html>");
    }

    private void configurarBotonComenzar() {
        jbtnComenzar.setText("Comenzar otro diagnóstico");
        jbtnComenzar.setBackground(new Color(147, 145, 50));
    }

    private void iniciarDiagnostico() {
        botonesRespuestaActivos(true, true);
        bgRespuestas.clearSelection();
        jtaResultados.setText("");
        iniciarMotor();
        negocioMotor.cargarTrastornosFromClips();
        negocioPregunta = NegocioPregunta.getNegocioPregunta(this);
        reiniciarConsultaPaciente();
        negocioPregunta.reiniciarPreguntas();
        jbtnSiguientePregunta.setText("Siguiente pregunta");
    }

    public void reiniciarConsultaPaciente() {
        consultaPaciente = new ConsultaPaciente(paciente);
        negocioConsultaPaciente = new NegocioConsultaPaciente(consultaPaciente);
    }

    public void finalizarDiagnostico() {
        botonesRespuestaActivos(false, false);
        lblPregunta.setText("<html><b>¡Diagnóstico finalizado!</b></html>");
        lblTrastorno.setText("");
        jbtnSiguientePregunta.setText("Volver a intentar");
        negocioConsultaPaciente.guardarConsulta();
    }

    private void activarPanelDatosPaciente() {
        jpPreguntasRespuestas.setVisible(false);
        jpDatosPaciente.setVisible(true);
        jbtnComenzar.setText("Comenzar diagnóstico");
        jbtnComenzar.setBackground(new Color(0, 102, 0));
    }

    private void mostrarIcono(String rutaIcono, JLabel label) {
        Image icono = new ImageIcon(rutaIcono).getImage();
        ImageIcon iconoImg = new ImageIcon(icono.getScaledInstance(56, 56, Image.SCALE_SMOOTH));
        label.setIcon(iconoImg);
    }

    private void mostrarListaPaises() {
        for (String pais : UtilidadGeneral.getPaises()) {
            jcbPais.addItem(pais);
        }
    }

    public void mostrarTrastorno(String trastorno) {
        lblTrastorno.setText("<html><b>Trastorno a evaluar: </b> " + trastorno + "<html>");
    }

    public void setIndiceTrastorno(int indiceTrastorno) {
        this.indiceTrastorno = indiceTrastorno;
    }

    public void setIndiceSintoma(int indiceSintoma) {
        this.indiceSintoma = indiceSintoma;
    }

    public void mostrarPregunta(String pregunta) {
        lblPregunta.setText("<html>" + pregunta + "</html>");
    }

    public void agregarRespuesta(String respuesta) {
        // Si la respuesta no se encuentra ya en el JtextArea
        if (!jtaResultados.getText().contains(respuesta)) {
            negocioConsultaPaciente.agregarDiagnosticoPaciente(respuesta);
            // Al principio, el resultado es sano
            // Si se agrega un trastorno, se elimina el diagnóstico sano del paciente
            negocioConsultaPaciente.verificarDiagnosticoSano();
            jtaResultados.setText("");
            // Se agregan los diagnosticos del paciente a los resultados
            for (String diagnostico : consultaPaciente.getDiagnostico()) {
                jtaResultados.append(diagnostico + "\n");
            }
        }
    }

    private void botonesRespuestaActivos(boolean botonTrue, boolean botonFalse) {
        jrbTrue.setEnabled(botonTrue);
        jrbFalse.setEnabled(botonFalse);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bgRespuestas = new javax.swing.ButtonGroup();
        jpTitulo = new javax.swing.JPanel();
        lblTitulo1 = new javax.swing.JLabel();
        lblIconoTitulo = new javax.swing.JLabel();
        jpDatosPaciente = new javax.swing.JPanel();
        lblTitulo2 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jtfNombre = new javax.swing.JTextField();
        jtfApellido = new javax.swing.JTextField();
        lblPais = new javax.swing.JLabel();
        jcbPais = new javax.swing.JComboBox();
        jcbSexo = new javax.swing.JComboBox();
        jsEdad = new javax.swing.JSpinner();
        lblApellido = new javax.swing.JLabel();
        lblNombre = new javax.swing.JLabel();
        lblSexo = new javax.swing.JLabel();
        lblEdad = new javax.swing.JLabel();
        jbtnSalir = new javax.swing.JButton();
        jbtnComenzar = new javax.swing.JButton();
        jSeparator7 = new javax.swing.JSeparator();
        jpPreguntasRespuestas = new javax.swing.JPanel();
        lblTitulo3 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        lblPregunta = new javax.swing.JLabel();
        jrbFalse = new javax.swing.JRadioButton();
        jrbTrue = new javax.swing.JRadioButton();
        lblRespuesta = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtaResultados = new javax.swing.JTextArea();
        jSeparator3 = new javax.swing.JSeparator();
        lblResultados = new javax.swing.JLabel();
        jbtnSiguientePregunta = new javax.swing.JButton();
        lblTrastorno = new javax.swing.JLabel();
        lblPaciente = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jpTitulo.setBackground(new java.awt.Color(0, 102, 153));
        jpTitulo.setForeground(new java.awt.Color(255, 255, 255));

        lblTitulo1.setFont(new java.awt.Font("Bernard MT Condensed", 1, 22)); // NOI18N
        lblTitulo1.setForeground(new java.awt.Color(255, 255, 255));
        lblTitulo1.setText("SBC de diagnóstico de enfermedades mentales");

        lblIconoTitulo.setBackground(new java.awt.Color(0, 102, 153));
        lblIconoTitulo.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblIconoTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblIconoTitulo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/cerebro.png"))); // NOI18N
        lblIconoTitulo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblIconoTitulo.setIconTextGap(0);
        lblIconoTitulo.setMaximumSize(new java.awt.Dimension(57, 128));
        lblIconoTitulo.setPreferredSize(new java.awt.Dimension(53, 128));

        javax.swing.GroupLayout jpTituloLayout = new javax.swing.GroupLayout(jpTitulo);
        jpTitulo.setLayout(jpTituloLayout);
        jpTituloLayout.setHorizontalGroup(
            jpTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpTituloLayout.createSequentialGroup()
                .addContainerGap(122, Short.MAX_VALUE)
                .addComponent(lblTitulo1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblIconoTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35))
        );
        jpTituloLayout.setVerticalGroup(
            jpTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpTituloLayout.createSequentialGroup()
                .addGroup(jpTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpTituloLayout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(lblTitulo1))
                    .addGroup(jpTituloLayout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(lblIconoTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(30, 30, 30))
        );

        getContentPane().add(jpTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 680, 110));

        jpDatosPaciente.setBackground(new java.awt.Color(255, 255, 255));

        lblTitulo2.setFont(new java.awt.Font("Bernard MT Condensed", 3, 24)); // NOI18N
        lblTitulo2.setText("Datos del paciente");

        jSeparator1.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));

        lblPais.setFont(new java.awt.Font("Maiandra GD", 1, 14)); // NOI18N
        lblPais.setText("País");

        jcbPais.setFont(new java.awt.Font("Maiandra GD", 0, 14)); // NOI18N

        jcbSexo.setFont(new java.awt.Font("Maiandra GD", 0, 14)); // NOI18N
        jcbSexo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Masculino", "Femenino", "No especificar" }));

        jsEdad.setFont(new java.awt.Font("Maiandra GD", 0, 14)); // NOI18N
        jsEdad.setModel(new javax.swing.SpinnerNumberModel(1, 1, 170, 1));

        lblApellido.setFont(new java.awt.Font("Maiandra GD", 1, 14)); // NOI18N
        lblApellido.setText("Apellido");

        lblNombre.setFont(new java.awt.Font("Maiandra GD", 1, 14)); // NOI18N
        lblNombre.setText("Nombre");

        lblSexo.setFont(new java.awt.Font("Maiandra GD", 1, 14)); // NOI18N
        lblSexo.setText("Sexo");

        lblEdad.setFont(new java.awt.Font("Maiandra GD", 1, 14)); // NOI18N
        lblEdad.setText("Edad");

        javax.swing.GroupLayout jpDatosPacienteLayout = new javax.swing.GroupLayout(jpDatosPaciente);
        jpDatosPaciente.setLayout(jpDatosPacienteLayout);
        jpDatosPacienteLayout.setHorizontalGroup(
            jpDatosPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpDatosPacienteLayout.createSequentialGroup()
                .addGroup(jpDatosPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpDatosPacienteLayout.createSequentialGroup()
                        .addGap(197, 197, 197)
                        .addGroup(jpDatosPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpDatosPacienteLayout.createSequentialGroup()
                                .addGroup(jpDatosPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblPais, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblSexo, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblEdad, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(38, 38, 38)
                                .addGroup(jpDatosPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jcbSexo, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jcbPais, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jsEdad, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jpDatosPacienteLayout.createSequentialGroup()
                                .addComponent(lblNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jtfNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jpDatosPacienteLayout.createSequentialGroup()
                                .addComponent(lblApellido)
                                .addGap(18, 18, 18)
                                .addComponent(jtfApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jpDatosPacienteLayout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 590, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpDatosPacienteLayout.createSequentialGroup()
                        .addGap(253, 253, 253)
                        .addComponent(lblTitulo2, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(42, 42, 42))
        );
        jpDatosPacienteLayout.setVerticalGroup(
            jpDatosPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpDatosPacienteLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(lblTitulo2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47)
                .addGroup(jpDatosPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jtfNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNombre))
                .addGap(30, 30, 30)
                .addGroup(jpDatosPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jtfApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblApellido))
                .addGap(30, 30, 30)
                .addGroup(jpDatosPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPais)
                    .addComponent(jcbPais, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jpDatosPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSexo)
                    .addComponent(jcbSexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(jpDatosPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEdad)
                    .addComponent(jsEdad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(91, Short.MAX_VALUE))
        );

        getContentPane().add(jpDatosPaciente, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 660, 470));

        jbtnSalir.setBackground(new java.awt.Color(204, 0, 0));
        jbtnSalir.setFont(new java.awt.Font("Maiandra GD", 1, 16)); // NOI18N
        jbtnSalir.setForeground(new java.awt.Color(255, 255, 255));
        jbtnSalir.setText("Salir");
        jbtnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnSalirActionPerformed(evt);
            }
        });
        getContentPane().add(jbtnSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 590, 100, 50));

        jbtnComenzar.setBackground(new java.awt.Color(0, 102, 0));
        jbtnComenzar.setFont(new java.awt.Font("Maiandra GD", 1, 16)); // NOI18N
        jbtnComenzar.setForeground(new java.awt.Color(255, 255, 255));
        jbtnComenzar.setText("Comenzar diagnóstico");
        jbtnComenzar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnComenzarActionPerformed(evt);
            }
        });
        getContentPane().add(jbtnComenzar, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 590, 250, 50));

        jSeparator7.setForeground(new java.awt.Color(0, 0, 0));
        getContentPane().add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 580, 640, -1));

        jpPreguntasRespuestas.setBackground(new java.awt.Color(255, 255, 255));

        lblTitulo3.setFont(new java.awt.Font("Bernard MT Condensed", 3, 24)); // NOI18N
        lblTitulo3.setText("Diagnóstico");

        jSeparator2.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator2.setForeground(new java.awt.Color(0, 0, 0));

        lblPregunta.setFont(new java.awt.Font("Maiandra GD", 0, 15)); // NOI18N
        lblPregunta.setText("<html> Percibir situaciones o acontecimientos como amenazantes, incluso cuando no lo son. </html>");

        bgRespuestas.add(jrbFalse);
        jrbFalse.setFont(new java.awt.Font("Maiandra GD", 0, 18)); // NOI18N
        jrbFalse.setText("No");

        bgRespuestas.add(jrbTrue);
        jrbTrue.setFont(new java.awt.Font("Maiandra GD", 0, 18)); // NOI18N
        jrbTrue.setText("Si");

        lblRespuesta.setFont(new java.awt.Font("Maiandra GD", 1, 16)); // NOI18N
        lblRespuesta.setText("Respuesta");

        jtaResultados.setColumns(20);
        jtaResultados.setRows(5);
        jScrollPane1.setViewportView(jtaResultados);

        jSeparator3.setBackground(new java.awt.Color(0, 0, 0));

        lblResultados.setFont(new java.awt.Font("Maiandra GD", 1, 16)); // NOI18N
        lblResultados.setText("Resultados");

        jbtnSiguientePregunta.setBackground(new java.awt.Color(0, 102, 153));
        jbtnSiguientePregunta.setFont(new java.awt.Font("Maiandra GD", 0, 18)); // NOI18N
        jbtnSiguientePregunta.setForeground(new java.awt.Color(255, 255, 255));
        jbtnSiguientePregunta.setText("Siguiente pregunta");
        jbtnSiguientePregunta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnSiguientePreguntaActionPerformed(evt);
            }
        });

        lblTrastorno.setFont(new java.awt.Font("Maiandra GD", 0, 14)); // NOI18N
        lblTrastorno.setText("Trastorno en evaluación:");

        lblPaciente.setFont(new java.awt.Font("Maiandra GD", 0, 14)); // NOI18N
        lblPaciente.setText("Paciente:");

        javax.swing.GroupLayout jpPreguntasRespuestasLayout = new javax.swing.GroupLayout(jpPreguntasRespuestas);
        jpPreguntasRespuestas.setLayout(jpPreguntasRespuestasLayout);
        jpPreguntasRespuestasLayout.setHorizontalGroup(
            jpPreguntasRespuestasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpPreguntasRespuestasLayout.createSequentialGroup()
                .addGroup(jpPreguntasRespuestasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpPreguntasRespuestasLayout.createSequentialGroup()
                        .addGap(259, 259, 259)
                        .addComponent(lblTitulo3, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpPreguntasRespuestasLayout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(jpPreguntasRespuestasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jSeparator2)
                            .addComponent(lblResultados)
                            .addGroup(jpPreguntasRespuestasLayout.createSequentialGroup()
                                .addComponent(lblRespuesta)
                                .addGap(147, 147, 147)
                                .addComponent(jrbTrue)
                                .addGap(44, 44, 44)
                                .addComponent(jrbFalse)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jbtnSiguientePregunta))
                            .addComponent(lblPregunta, javax.swing.GroupLayout.DEFAULT_SIZE, 590, Short.MAX_VALUE)
                            .addComponent(jSeparator3)
                            .addComponent(jScrollPane1)
                            .addGroup(jpPreguntasRespuestasLayout.createSequentialGroup()
                                .addComponent(lblTrastorno)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblPaciente)
                                .addGap(21, 21, 21)))))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        jpPreguntasRespuestasLayout.setVerticalGroup(
            jpPreguntasRespuestasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpPreguntasRespuestasLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(lblTitulo3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpPreguntasRespuestasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTrastorno)
                    .addComponent(lblPaciente))
                .addGap(25, 25, 25)
                .addComponent(lblPregunta, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jpPreguntasRespuestasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jrbFalse)
                    .addComponent(lblRespuesta)
                    .addComponent(jrbTrue)
                    .addComponent(jbtnSiguientePregunta))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addComponent(lblResultados)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );

        getContentPane().add(jpPreguntasRespuestas, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 660, 470));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbtnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnSalirActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jbtnSalirActionPerformed

    private void jbtnComenzarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnComenzarActionPerformed
        String nombre, apellido, pais, sexo;
        int edad;
        boolean pacienteValido;
        if (jpDatosPaciente.isVisible()) {
            pacienteValido = negocioPaciente.pacienteValido(jtfNombre.getText(), jtfApellido.getText());
            if (pacienteValido) {
                nombre = jtfNombre.getText();
                apellido = jtfApellido.getText();
                pais = (String) jcbPais.getSelectedItem();
                sexo = (String) jcbSexo.getSelectedItem();
                edad = (int) jsEdad.getValue();
                paciente = new Paciente(nombre, apellido, pais, sexo, edad);
                activarPanelPreguntasRespuestas();
            } else {
                mensaje = "Error, verifique el formato en los campos Nombre y Apellido. \n \n "
                        + "No se admite: \n "
                        + "- Campo vacío \n "
                        + "- Números \n "
                        + "- Símbolos";
                titulo = "Error";
                JOptionPane.showMessageDialog(null, mensaje, titulo, JOptionPane.ERROR_MESSAGE);
            }
        } else {
            limpiarDatosPaciente();
            activarPanelDatosPaciente();
        }
    }//GEN-LAST:event_jbtnComenzarActionPerformed

    private void jbtnSiguientePreguntaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnSiguientePreguntaActionPerformed
        Sintoma sintoma;

        if (!jrbTrue.isSelected() && !jrbFalse.isSelected()) {
            mensaje = "Error, para ir a la siguiente pregunta debe seleccionar una respuesta.";
            titulo = "Error";
            JOptionPane.showMessageDialog(null, mensaje, titulo, JOptionPane.ERROR_MESSAGE);
        } else {
            if (jbtnSiguientePregunta.getText().equals("Siguiente pregunta")) {
                negocioConsultaPaciente.setRespuestaSintomaTrastorno(jrbTrue.isSelected(), indiceTrastorno, indiceSintoma);
                sintoma = negocioConsultaPaciente.getSintomaTrastorno(indiceTrastorno, indiceSintoma);
                try {
                    negocioMotor.agregarSintoma(sintoma);
                } catch (JessException ex) {
                    System.out.println("Error al agregar el sintoma. " + ex.getMessage());
                }
                if (jrbFalse.isSelected()) {
                    negocioPregunta.pasarSiguienteRamaOrArbol();
                }
                negocioPregunta.determinarSiguientePreguntaOrTrastorno();
            } else {
                // Si se hace click en el botón con el texto Volver a intentar
                iniciarDiagnostico();
            }
        }
    }//GEN-LAST:event_jbtnSiguientePreguntaActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup bgRespuestas;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JButton jbtnComenzar;
    private javax.swing.JButton jbtnSalir;
    private javax.swing.JButton jbtnSiguientePregunta;
    private javax.swing.JComboBox jcbPais;
    private javax.swing.JComboBox jcbSexo;
    private javax.swing.JPanel jpDatosPaciente;
    private javax.swing.JPanel jpPreguntasRespuestas;
    private javax.swing.JPanel jpTitulo;
    private javax.swing.JRadioButton jrbFalse;
    private javax.swing.JRadioButton jrbTrue;
    private javax.swing.JSpinner jsEdad;
    private javax.swing.JTextArea jtaResultados;
    private javax.swing.JTextField jtfApellido;
    private javax.swing.JTextField jtfNombre;
    private javax.swing.JLabel lblApellido;
    private javax.swing.JLabel lblEdad;
    private javax.swing.JLabel lblIconoTitulo;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblPaciente;
    private javax.swing.JLabel lblPais;
    private javax.swing.JLabel lblPregunta;
    private javax.swing.JLabel lblRespuesta;
    private javax.swing.JLabel lblResultados;
    private javax.swing.JLabel lblSexo;
    private javax.swing.JLabel lblTitulo1;
    private javax.swing.JLabel lblTitulo2;
    private javax.swing.JLabel lblTitulo3;
    private javax.swing.JLabel lblTrastorno;
    // End of variables declaration//GEN-END:variables
}
