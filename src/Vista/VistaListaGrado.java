/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Controlador.ConsultarListar;
import Controlador.Eliminar;
import Controlador.Incluir;
import Controlador.Modificar;
import Controlador.OyenteConsultar;
import Controlador.OyenteEliminar;
import Controlador.OyenteIncluir;
import Controlador.OyenteListar;
import Controlador.OyenteModificar;
import Modelo.AlumnoGrado;
import Modelo.Grado;
import Modelo.GradoProfesor;
import static Modelo.MensajesDeError.errorSQL;
import Modelo.Representante;
import Vista.Formatos.CampoTexto;
import Vista.Formatos.Botonera;
import Vista.Tablas.TablaGradoFechaAlumnos;
import Vista.Tablas.TablaGradoFechaProfesores;
import Vista.Tablas.TablaGrados;
import Vista.Tablas.TablaGradosFechas;
import Vista.Tablas.TablaRepresentantes;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import static javax.management.Query.and;
import javax.swing.BorderFactory;
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
public class VistaListaGrado extends JFrame {
    private TablaGrados tablaGrados;
    private TablaGradosFechas tablaGradosFechas;
    private TablaGradoFechaProfesores tablaGradoFechaProfesores;
    private TablaGradoFechaAlumnos tablaGradoFechaAlumnos;
    
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
    public VistaListaGrado(){
        crearGui();
    }
    
    public void setCodigoGrado(String codigoEntrada){
        codigoGrado = codigoEntrada;
    }
    public String getCodigoGrado(){
        return codigoGrado;
    }
    
    public void setFecha(String fechaEntrada){
        fecha = fechaEntrada;
    }
    public String getFecha(){
        return fecha;
    }
    
    
    final void crearGui(){
        setTitle("Lista de Grados");
        setResizable(false);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                
        /**
         * Elementos del panel izquierdo
         */
        resultado = grado.consultarGrados();
        tablaGrados = new TablaGrados(300,100);
        tablaGrados.cargarTabla(resultado);
    
        botoneraICG = new Botonera(IC);
        botoneraICG.adherirEscucha(0, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
        //        VistaAdmisionGrado vistaAdmisionGrado = new VistaAdmisionGrado();
            }
        });
        botoneraMEG = new Botonera(ME);
        botoneraMEG.adherirEscucha(0, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
        //        VistaActualizarGrado vistaActualizarGrado = new VistaActualizarGrado();
            }
        });
        botoneraMEG.adherirEscucha(1, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (tablaGrados.tabla.getSelectedRow()>=0){

                    String stringGrado=(String)tablaGrados.tablaModelo.getValueAt(tablaGrados.tabla.getSelectedRow(), 0); //string 
                    int codigoGrado=Integer.parseInt(stringGrado);    //   int

                    // si confirma elimina de la base de datos
                    if (tablaGrados.eliminarFila()) {
                        grado.eliminar(codigoGrado);
                    }
                } else {
        //            JOptionPane.showMessageDialog(this,"Seleccione antes en la tabla el profesor a eliminar");
                }
            }
        });
    
        JPanel panelBotonesGrado = new JPanel();
        panelBotonesGrado.setLayout(new FlowLayout());
        panelBotonesGrado.add(botoneraICG);
        panelBotonesGrado.add(botoneraMEG);
        
        JPanel panelGrado = new JPanel();
        panelGrado.setLayout(new GridLayout(2,1));
        panelGrado.setBorder(BorderFactory.createTitledBorder("Manejo de Grados"));
        panelGrado.add(tablaGrados);
        panelGrado.add(panelBotonesGrado);
        
        
        ResultSet resultadoGA = alumnoGrado.consultarFechas();
        try{
            while(resultadoGA.next()){
                fechas.add(resultadoGA.getString(1));
            }
        }catch(SQLException error){
            mensaje = errorSQL(error.getSQLState());
            JOptionPane.showMessageDialog(null,mensaje);
        }
        
        ResultSet resultadoGP = gradoProfesor.consultarFechas();
        try{
            while(resultadoGP.next()){
                fechas.add(resultadoGP.getString(1));
            }
        }catch(SQLException error){
            mensaje = errorSQL(error.getSQLState());
            JOptionPane.showMessageDialog(null,mensaje);
        }
        
        tablaGradosFechas = new TablaGradosFechas(55,100);
        tablaGradosFechas.cargarTabla(fechas);
        
        botoneraNF = new Botonera(NF);
        botoneraNF.adherirEscucha(0, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        
        JPanel panelFecha = new JPanel();
        panelFecha.setLayout(new GridLayout(2,1));
        panelFecha.setBorder(BorderFactory.createTitledBorder("Fechas"));
        panelFecha.add(tablaGradosFechas);
        panelFecha.add(botoneraNF);
        
        JPanel panelTopLeft = new JPanel();
        panelTopLeft.setLayout(new FlowLayout());
        panelTopLeft.add(panelGrado);
        panelTopLeft.add(panelFecha);
        
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
        
        panelLeft = new JPanel();
        panelLeft.setLayout(new GridLayout(2,1));
        //panelLeft.setBorder(BorderFactory.createTitledBorder("Grados"));
        panelLeft.add(panelTopLeft);
        panelLeft.add(panelProfesores);
                
        
        //resultado = alumnnoGrado.consultarAlumnoGradoFecha(mensaje, ICONIFIED, NORMAL);
        tablaGradoFechaAlumnos = new TablaGradoFechaAlumnos(375,200);
        //tablaGradoFechaAlumnos.cargarTabla(resultado);
        
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
        
        panelRight = new JPanel();
        panelRight.setLayout(new FlowLayout());
        //panelRight.setBorder(BorderFactory.createTitledBorder("Estudiantes Asignados(as)"));
        panelRight.add(panelAlumnos);
        
        /**
         * Configuracion de Vista
         */
        add(BorderLayout.WEST, panelLeft);
        add(BorderLayout.EAST, panelRight);
        
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

}