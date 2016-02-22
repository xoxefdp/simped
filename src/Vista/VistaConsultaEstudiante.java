/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Controlador.Aceptar;
import Controlador.ConsultarListar;
import Controlador.OyenteAceptar;
import Controlador.OyenteConsultar;
import Controlador.OyenteListar;
import Modelo.Alumno;
import Modelo.Representante;
import Vista.Formatos.CampoTexto;
import Vista.Formatos.Botonera;
import Vista.Tablas.TablaAlumnos;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * 
 * @author yonalix garcia
 */
public class VistaConsultaEstudiante extends JFrame implements Aceptar,ConsultarListar{
    private TablaAlumnos tablaAlumnos;
    private Botonera botoneraBU,botoneraLI,botoneraDE,botoneraIM;
    private CampoTexto codigo;
    private JPanel panelBusqueda,panelTop, panelBottom;
    private final String[] BU = {"Buscar"};
    private final String[] LI = {"Listar Todos"};
    private final String[] DE = {"Detallar"};
    private final String[] IM = {"Imprimir"};
 
    private final Alumno alumno = new Alumno();
    private ResultSet resultado;
    /**
     * Crea la interface de la clase.
     */
    public VistaConsultaEstudiante(){
        crearGui();
    }
    
    final void crearGui(){
        setTitle("Lista de Estudiantes");
        setResizable(false);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                
        /**
         * Elementos del panel superior
         */
        codigo=new CampoTexto("",15);
        botoneraBU = new Botonera(1,BU);
        botoneraBU.adherirEscucha(0, new OyenteConsultar(this));
        
        panelBusqueda = new JPanel();
        panelBusqueda.setBorder(BorderFactory.createTitledBorder("Codigo Estudiante"));
        panelBusqueda.add(codigo);
        panelBusqueda.add(botoneraBU);
        
        botoneraLI = new Botonera(1,LI);
        botoneraLI.adherirEscucha(0, new OyenteListar(this));
        
        botoneraDE = new Botonera(1,DE);
        botoneraDE.adherirEscucha(0, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                detallar();
            }
        });
        
        panelTop =new JPanel();
        panelTop.setLayout(new GridLayout(1,3)); 
        panelTop.setBorder(BorderFactory.createTitledBorder("Filtrar / Listar / Detallar"));
        panelTop.add(panelBusqueda);
        panelTop.add(botoneraLI);
        panelTop.add(botoneraDE);
        
        /**
         * Se crea la tabla y se pobla con los resultados y el metodo cargarTabla
         */
        resultado = alumno.consultarAlumnos();
        tablaAlumnos = new TablaAlumnos();
        tablaAlumnos.cargarTabla(resultado);
        
        /**
         * Ejecuta eventos de selección en tabla
         */
        tablaAlumnos.tabla.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int row = tablaAlumnos.tabla.getSelectedRow();
                if (row >= 0) {
                    codigo.cambiarContenido((String)tablaAlumnos.tabla.getValueAt(row, 0));
                }
            }
        });
        
        /**
         * Elementos del panel inferior
         */
        botoneraIM = new Botonera(1,IM);
        botoneraIM.adherirEscucha(0, new OyenteAceptar(this));
        
        panelBottom =new JPanel();
        panelBottom.setBorder(BorderFactory.createTitledBorder("Operaciones"));
        panelBottom.add(botoneraIM);

        add(BorderLayout.NORTH,panelTop);
        add(BorderLayout.CENTER,tablaAlumnos);
        add(BorderLayout.SOUTH,panelBottom);
        
        pack();
        setVisible(true);
    }

    public void detallar() {
        if (tablaAlumnos.tabla.getSelectedRow()>=0){
            
            String stringCodEstudiante=(String)tablaAlumnos.tablaModelo.getValueAt(tablaAlumnos.tabla.getSelectedRow(), 0); //string
            int codigoEstudiante=Integer.parseInt(stringCodEstudiante);    //   int

            String stringNomEstudiante=(String)tablaAlumnos.tablaModelo.getValueAt(tablaAlumnos.tabla.getSelectedRow(), 1); //string
            String stringApeEstudiante=(String)tablaAlumnos.tablaModelo.getValueAt(tablaAlumnos.tabla.getSelectedRow(), 2); //string
            String stringFecEstudiante=(String)tablaAlumnos.tablaModelo.getValueAt(tablaAlumnos.tabla.getSelectedRow(), 3); //string
            String stringSexEstudiante=(String)tablaAlumnos.tablaModelo.getValueAt(tablaAlumnos.tabla.getSelectedRow(), 4); //string
            
            String stringRepEstudiante=(String)tablaAlumnos.tablaModelo.getValueAt(tablaAlumnos.tabla.getSelectedRow(), 5); //string
            int cedulaRepEstudiante=Integer.parseInt(stringRepEstudiante);    //   int
            
            Representante representante = new Representante();
            representante.consultarRepresentante(cedulaRepEstudiante);
            
            JOptionPane.showMessageDialog(this,"Datos Estudiante \n\n"
                                            +  "Codigo Estudiante: "+codigoEstudiante+"\n"
                                            +  "Nombres: "+stringNomEstudiante+"\n"
                                            +  "Apellidos: "+stringApeEstudiante+"\n"
                                            +  "Fecha de Nacimiento: "+stringFecEstudiante+"\n"
                                            +  "Sexo: "+stringSexEstudiante+"\n\n"
                                            +  "Representante: "+cedulaRepEstudiante+"\n"
                                            +  "Cedula Representante: "+cedulaRepEstudiante+"\n");
        } else {
            JOptionPane.showMessageDialog(this,"Seleccione antes en la tabla el estudiante a detallar");
        }
    }
   
    @Override
    public void listar() { // consulta todos
        ResultSet resultadoListar = alumno.consultarAlumnos();
        tablaAlumnos.cargarTabla(resultadoListar);
    }
    
    @Override
    public void consultar() { // consulta uno
        if (codigo.obtenerContenido().length() != 0) {
            String stringCodigo = codigo.obtenerContenido(); //falta generalizar
            int codigoAlumno=Integer.parseInt(stringCodigo);    //   int

            ResultSet resultadoConsulta = alumno.consultarAlumno(codigoAlumno);
            tablaAlumnos.cargarTabla(resultadoConsulta);
        } else {
            JOptionPane.showMessageDialog(this,"Escriba el codigo a consultar");
        }
    }

    @Override
    public void aceptar() {
        if (tablaAlumnos.tabla.getSelectedRow()>=0){
            
            String stringCodEstudiante=(String)tablaAlumnos.tablaModelo.getValueAt(tablaAlumnos.tabla.getSelectedRow(), 0); //string
            int codigoEstudiante=Integer.parseInt(stringCodEstudiante);    //   int

            String stringNomEstudiante=(String)tablaAlumnos.tablaModelo.getValueAt(tablaAlumnos.tabla.getSelectedRow(), 1); //string
            String stringApeEstudiante=(String)tablaAlumnos.tablaModelo.getValueAt(tablaAlumnos.tabla.getSelectedRow(), 2); //string
            String stringFecEstudiante=(String)tablaAlumnos.tablaModelo.getValueAt(tablaAlumnos.tabla.getSelectedRow(), 3); //string
            String stringSexEstudiante=(String)tablaAlumnos.tablaModelo.getValueAt(tablaAlumnos.tabla.getSelectedRow(), 4); //string
            
            String stringRepEstudiante=(String)tablaAlumnos.tablaModelo.getValueAt(tablaAlumnos.tabla.getSelectedRow(), 5); //string
            int cedulaRepEstudiante=Integer.parseInt(stringRepEstudiante);    //   int
            
            Representante representante = new Representante();
            representante.consultarRepresentante(cedulaRepEstudiante);
            
            JOptionPane.showMessageDialog(this,"Datos Estudiante \n\n"
                                            +  "Codigo Estudiante: "+codigoEstudiante+"\n"
                                            +  "Nombres: "+stringNomEstudiante+"\n"
                                            +  "Apellidos: "+stringApeEstudiante+"\n"
                                            +  "Fecha de Nacimiento: "+stringFecEstudiante+"\n"
                                            +  "Sexo: "+stringSexEstudiante+"\n\n"
                                            +  "Representante: "+cedulaRepEstudiante+"\n"
                                            +  "Cedula Representante: "+cedulaRepEstudiante+"\n");
        } else {
            JOptionPane.showMessageDialog(this,"Seleccione antes en la tabla el estudiante");
        }
    }
    
}