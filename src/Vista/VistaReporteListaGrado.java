/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Modelo.AlumnoGrado;
import Modelo.Grado;
import Modelo.GradoProfesor;
import static Modelo.MensajesDeError.errorSQL;
import Vista.Componentes.ReporteListaGrado;
import Vista.Formatos.Botonera;
import Vista.Tablas.TablaGradoFechaAlumnos;
import Vista.Tablas.TablaGradoFechaProfesores;
import Vista.Tablas.TablaGrados;
import Vista.Tablas.TablaGradosFechas;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * 
 * @author josediaz
 */
public class VistaReporteListaGrado extends JFrame {
    private TablaGrados tablaGrados;
    private TablaGradosFechas tablaGradosFechas;
    private TablaGradoFechaAlumnos tablaGradoFechaAlumnos;
    private TablaGradoFechaProfesores tablaGradoFechaProfesores;
    
    private Botonera botoneraICG,botoneraMEG,botoneraNF,botoneraICP,botoneraMEP,botoneraICA,botoneraMEA;
    private JPanel panelLeft, panelRight;
    private final String[] IC = {"Incluir"};
    private final String[] ME = {"Modificar","Eliminar"};
    private final String[] NF = {"Nuevo"};
    private final ArrayList<String> fechas = new ArrayList<>();
    private final Grado grado = new Grado();
    private final GradoProfesor gradoProfesor = new GradoProfesor();
    private final AlumnoGrado alumnoGrado = new AlumnoGrado();
    private String mensaje,codigoGrado,fecha;
    private ResultSet resultado;
    
    /**
     * Crea la interface de la clase.
     */
    public VistaReporteListaGrado(){
        crearGui();
    }
    
    private void setCodigoGrado(String codigoEntrada){
        codigoGrado = codigoEntrada;
    }
    private String getCodigoGrado(){
        return codigoGrado;
    }
    
    private void setFecha(String fechaEntrada){
        fecha = fechaEntrada;
    }
    private String getFecha(){
        return fecha;
    }
    
    final void crearGui(){
        setTitle("Emision de Listas de Asistencia");
        setResizable(false);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                
        /**
         * Elementos del panel izquierdo
         */
        resultado = grado.consultarGrados();
        tablaGrados = new TablaGrados(300,100);
        tablaGrados.cargarTabla(resultado);
        
        /*
        JPanel panelGrado = new JPanel();
        panelGrado.setLayout(new FlowLayout());
        panelGrado.setBorder(BorderFactory.createTitledBorder("Lista de Grados"));
        panelGrado.add(tablaGrados);
        */
        
        ResultSet resultadoGA = alumnoGrado.consultarFechas();
        try{
            while(resultadoGA.next()){
                if(!fechas.contains(resultadoGA.getString(1))){
                    fechas.add(resultadoGA.getString(1));
                }
            }
        }catch(SQLException error){
            mensaje = errorSQL(error.getSQLState());
            JOptionPane.showMessageDialog(null,mensaje);
            //JOptionPane.showMessageDialog(null,error);
        }
        
        ResultSet resultadoGP = gradoProfesor.consultarFechas();
        try{
            while(resultadoGP.next()){
                if(!fechas.contains(resultadoGP.getString(1))){
                    fechas.add(resultadoGP.getString(1));
                }
            }
        }catch(SQLException error){
            mensaje = errorSQL(error.getSQLState());
            JOptionPane.showMessageDialog(null,mensaje);
            //JOptionPane.showMessageDialog(null,error);
        }
        
        tablaGradosFechas = new TablaGradosFechas(55,100);
        tablaGradosFechas.cargarTabla(fechas);
    /*    
        botoneraNF = new Botonera(NF);
        botoneraNF.adherirEscucha(0, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
    */    
    /*
        JPanel panelFecha = new JPanel();
        panelFecha.setLayout(new GridLayout(2,1));
    //  panelFecha.setLayout(new FlowLayout());
        panelFecha.setBorder(BorderFactory.createTitledBorder("Fechas"));
        panelFecha.add(tablaGradosFechas);
      panelFecha.add(botoneraNF);
    
        
        JPanel panelTopLeft = new JPanel();
        panelTopLeft.setLayout(new FlowLayout());
        //panelTopLeft.add(panelGrado);
        //panelTopLeft.add(panelFecha);
        panelTopLeft.add(tablaGrados);
        panelTopLeft.add(tablaGradosFechas);
        */
        /**
         * Ejecuta eventos de selección en tabla
         */
    /*
        tablaGrados.tabla.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int row = tablaGrados.tabla.getSelectedRow();
                if (row >= 0) {
                    cedula.cambiarContenido((String)tablaGrados.tabla.getValueAt(row, 0));
                }
            }
        });
    */
    
        //resultado = gradoProfesor.consultarGradoProfesorFecha(,);        
        tablaGradoFechaProfesores = new TablaGradoFechaProfesores(375,100);
        //tablaGradoFechaProfesores.cargarTabla(resultado);
    /*
        botoneraICP = new Botonera(IC);
        //botoneraICP.adherirEscucha(0, new OyenteIncluir(this));        
        botoneraMEP = new Botonera(ME);
        //botoneraMEP.adherirEscucha(0, new OyenteModificar(this));
        //botoneraMEP.adherirEscucha(1, new OyenteEliminar(this));
        
        JPanel panelBotonesProfesor = new JPanel();
        panelBotonesProfesor.setLayout(new FlowLayout());
        panelBotonesProfesor.add(botoneraICP);
        panelBotonesProfesor.add(botoneraMEP);
        
        JPanel panelProfesores = new JPanel();
        panelProfesores.setLayout(new GridLayout(2,1));
        panelProfesores.setBorder(BorderFactory.createTitledBorder("Profesor(es) Asignado(s)"));
        panelProfesores.add(tablaGradoFechaProfesores);
        panelProfesores.add(panelBotonesProfesor);
        */
        panelLeft = new JPanel();
        //panelLeft.setLayout(new GridLayout(2,1));
        panelLeft.setLayout(new FlowLayout());
        //panelLeft.setBorder(BorderFactory.createTitledBorder("Grados"));
        //panelLeft.add(panelTopLeft);
        //panelLeft.add(panelProfesores);
        panelLeft.add(tablaGrados);
        panelLeft.add(tablaGradosFechas);                
        panelLeft.add(tablaGradoFechaProfesores);
        
        //resultado = alumnnoGrado.consultarAlumnoGradoFecha(mensaje, ICONIFIED, NORMAL);
        tablaGradoFechaAlumnos = new TablaGradoFechaAlumnos(375,200);
        //tablaGradoFechaAlumnos.cargarTabla(resultado);
    /*
        botoneraICA = new Botonera(IC);
        //botoneraICA.adherirEscucha(0, new OyenteIncluir(this));        
        botoneraMEA = new Botonera(ME);
        //botoneraMEA.adherirEscucha(0, new OyenteModificar(this));
        //botoneraMEA.adherirEscucha(1, new OyenteEliminar(this));
        
        JPanel panelBotonesAlumno = new JPanel();
        panelBotonesAlumno.setLayout(new FlowLayout());
        panelBotonesAlumno.add(botoneraICA);
        panelBotonesAlumno.add(botoneraMEA);
        
        JPanel panelAlumnos = new JPanel();
        panelAlumnos.setLayout(new GridLayout(2,1));
        panelAlumnos.setBorder(BorderFactory.createTitledBorder("Estudiantes Asignados(as)"));
        panelAlumnos.add(tablaGradoFechaAlumnos);
        panelAlumnos.add(panelBotonesAlumno);
    */    
        panelRight = new JPanel();
        panelRight.setLayout(new FlowLayout());
        //panelRight.setBorder(BorderFactory.createTitledBorder("Estudiantes Asignados(as)"));
        //panelRight.add(panelAlumnos);
        //panelRight.add(tablaGradoFechaProfesores);
        panelRight.add(tablaGradoFechaAlumnos);
        
        String[] imprime = {"Imprimir"};
        Botonera botoneraImprimir = new Botonera(imprime);
        botoneraImprimir.adherirEscucha(0, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                imprimir();
            }
        });
        
        /**
         * Configuracion de Vista
         */
        add(BorderLayout.WEST, panelLeft);
        add(BorderLayout.EAST, panelRight);
        add(BorderLayout.SOUTH, botoneraImprimir);
        
        tablaGrados.tabla.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int row = tablaGrados.tabla.getSelectedRow();
                if (row >= 0) {
                    setCodigoGrado((String)tablaGrados.tabla.getValueAt(row, 0));
                    ResultSet resultadoAG = alumnoGrado.consultarListaAlumnosGradoFecha(getFecha(), getCodigoGrado());
                    ResultSet resultadoGP = gradoProfesor.consultarGradoFechaProfesor(getFecha(), getCodigoGrado());
                    tablaGradoFechaProfesores.cargarTabla(resultadoGP);
                    tablaGradoFechaAlumnos.cargarTabla(resultadoAG);
                }
            }
        });
        
        tablaGradosFechas.tabla.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int row = tablaGradosFechas.tabla.getSelectedRow();
                if (row >= 0) {
                    setFecha((String)tablaGradosFechas.tabla.getValueAt(row, 0));
                    ResultSet resultadoAG = alumnoGrado.consultarListaAlumnosGradoFecha(getFecha(), getCodigoGrado());
                    ResultSet resultadoGP = gradoProfesor.consultarGradoFechaProfesor(getFecha(), getCodigoGrado());
                    tablaGradoFechaProfesores.cargarTabla(resultadoGP);
                    tablaGradoFechaAlumnos.cargarTabla(resultadoAG);
                }
            }
        });
        
        pack();
        setVisible(true);
    }
    
    public void imprimir() {
        if (tablaGrados.tabla.getSelectedRow()>=0 && tablaGradosFechas.tabla.getSelectedRow()>=0){
            
            ResultSet resultadoConsultaEstudiante = alumnoGrado.consultarListaAlumnosGradoFecha(fecha, getCodigoGrado());
            ResultSet resultadoConsultaProfesor = gradoProfesor.consultarGradoFechaProfesor(fecha, getCodigoGrado());
                       
            ReporteListaGrado reporteConstanciaEstudiante = new ReporteListaGrado(resultadoConsultaEstudiante, resultadoConsultaProfesor);
            
        } else {
            JOptionPane.showMessageDialog(this,"Seleccione antes en la tabla de grados y años");
        }
    }

}