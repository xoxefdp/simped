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
import Modelo.Profesor;
import Vista.Formatos.CampoTexto;
import Vista.Formatos.Botonera;
import Vista.Tablas.TablaProfesores;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * 
 * @author josediaz
 */
public class VistaListaProfesor extends JFrame implements Incluir, Modificar, Eliminar, ConsultarListar{
    private TablaProfesores tablaProfesores;
    private Botonera botoneraME,botoneraBU,botoneraDE,botoneraIC,botoneraLI;
    private CampoTexto cedula;
    private JPanel panelBusqueda,panelTop, panelBottom;
    private final String[] IC = {"Incluir"};
    private final String[] ME = {"Modificar","Eliminar"};
    private final String[] BU = {"Buscar"};
    private final String[] LI = {"Listar Todos"};
    private final String[] DE = {"Detallar"};
    private final Profesor profesor = new Profesor();
    private ResultSet resultado;
    /**
     * Crea la interface de la clase.
     */
    public VistaListaProfesor(){
        
        crearGui();
    }
    
    final void crearGui(){
        setTitle("Lista de Profesores");
        setResizable(false);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                
        /**
         * Elementos del panel superior
         */
        cedula=new CampoTexto("",15);
        botoneraBU = new Botonera(1,BU);
        botoneraBU.adherirEscucha(0, new OyenteConsultar(this));
        
        panelBusqueda = new JPanel();
        panelBusqueda.setBorder(BorderFactory.createTitledBorder("Cedula Profesor"));
        panelBusqueda.add(cedula);
        panelBusqueda.add(botoneraBU);
        
        botoneraLI = new Botonera(1,LI);
        botoneraLI.adherirEscucha(0, new OyenteListar(this));
        
        botoneraDE = new Botonera(1,DE);
        /*
        botoneraDE.adherirEscucha(0, (ActionEvent e) -> {
            detallar();
        });
        */
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
        resultado = profesor.consultarProfesores();
        tablaProfesores = new TablaProfesores();
        tablaProfesores.cargarTabla(resultado);
   
        /**
         * Elementos del panel inferior
         */
        botoneraIC = new Botonera(1,IC);
        botoneraIC.adherirEscucha(0,new OyenteIncluir(this));
        
        botoneraME = new Botonera(2,ME);
        botoneraME.adherirEscucha(0,new OyenteModificar(this));
        botoneraME.adherirEscucha(1,new OyenteEliminar(this));
        
        panelBottom =new JPanel();
        panelBottom.setBorder(BorderFactory.createTitledBorder("Operaciones"));
        panelBottom.add(botoneraIC);
        panelBottom.add(botoneraME);
        
        /**
         * Configuracion de Vista
         */
        add(BorderLayout.NORTH, panelTop);
        add(BorderLayout.CENTER,tablaProfesores);
        add(BorderLayout.SOUTH, panelBottom);

        pack();
        setVisible(true);
    }

    public void detallar() {
        if (tablaProfesores.tabla.getSelectedRow()>=0){
            
            String stringCedProfesor=(String)tablaProfesores.tablaModelo.getValueAt(tablaProfesores.tabla.getSelectedRow(), 0); //string
            int cedulaProfesor=Integer.parseInt(stringCedProfesor);    //   int

            String stringNomProfesor=(String)tablaProfesores.tablaModelo.getValueAt(tablaProfesores.tabla.getSelectedRow(), 1); //string
            String stringApeProfesor=(String)tablaProfesores.tablaModelo.getValueAt(tablaProfesores.tabla.getSelectedRow(), 2); //string
            String stringFecProfesor=(String)tablaProfesores.tablaModelo.getValueAt(tablaProfesores.tabla.getSelectedRow(), 3); //string
            String stringDirProfesor=(String)tablaProfesores.tablaModelo.getValueAt(tablaProfesores.tabla.getSelectedRow(), 4); //string            
            String stringCorProfesor=(String)tablaProfesores.tablaModelo.getValueAt(tablaProfesores.tabla.getSelectedRow(), 5); //string
            String stringTelProfesor=(String)tablaProfesores.tablaModelo.getValueAt(tablaProfesores.tabla.getSelectedRow(), 6); //string
            String stringSexProfesor=(String)tablaProfesores.tablaModelo.getValueAt(tablaProfesores.tabla.getSelectedRow(), 7); //string
            String stringTitProfesor=(String)tablaProfesores.tablaModelo.getValueAt(tablaProfesores.tabla.getSelectedRow(), 8); //string
            
            JOptionPane.showMessageDialog(this,"Datos Profesor \n\n"
                                            +  "Cedula: "+cedulaProfesor+"\n"
                                            +  "Nombres: "+stringNomProfesor+"\n"
                                            +  "Apellidos: "+stringApeProfesor+"\n"
                                            +  "Fecha de Nacimiento: "+stringFecProfesor+"\n"
                                            +  "Dirección: "+stringDirProfesor+"\n"
                                            +  "Correo: "+stringCorProfesor+"\n"
                                            +  "Telefono: "+stringTelProfesor+"\n"
                                            +  "Sexo: "+stringSexProfesor+"\n"
                                            +  "Titulo: "+stringTitProfesor+"\n");
        } else {
            JOptionPane.showMessageDialog(this,"Seleccione antes en la tabla el profesor a detallar");
        }
    }
    
    @Override
    public void incluir() {
        VistaAdmisionProfesor vistaAdmisionProfesor = new VistaAdmisionProfesor();
    }

    @Override
    public void modificar() {
        if (tablaProfesores.tabla.getSelectedRow()>=0){
            
            String stringProfesor; // debo convertirlo a int para pasarlo al metodo y a la base de datos
            int cedulaProfesor;
            
            stringProfesor=(String)tablaProfesores.tablaModelo.getValueAt(tablaProfesores.tabla.getSelectedRow(), 0); //string 
            cedulaProfesor=Integer.parseInt(stringProfesor);    //   int
            VistaActualizarProfesor vistaActualizarProfesor = new VistaActualizarProfesor(cedulaProfesor);
        }
    }
    
    @Override
    public void eliminar() {
        if (tablaProfesores.tabla.getSelectedRow()>=0){
            
            String stringCedula; // debo convertirlo a int para pasarlo al metodo y a la base de datos
            int cedulaProfesor;            
            
            stringCedula=(String)tablaProfesores.tablaModelo.getValueAt(tablaProfesores.tabla.getSelectedRow(), 0); //string 
            cedulaProfesor=Integer.parseInt(stringCedula);    //   int
            
            // si confirma elimina de la base de datos
            if (tablaProfesores.eliminarFila()) {
                profesor.eliminar(cedulaProfesor);
            }
        } else {
            JOptionPane.showMessageDialog(this,"Seleccione antes en la tabla el profesor a eliminar");
        }
    }
    
    @Override
    public void listar() { // consulta todos
        ResultSet resultadoListar = profesor.consultarProfesores();
        tablaProfesores.cargarTabla(resultadoListar);
    }
    
    @Override
    public void consultar() { // consulta uno
        if (cedula.obtenerContenido().length() != 0) {
            String stringCedula = cedula.obtenerContenido(); //falta generalizar
            int cedulaProfesor=Integer.parseInt(stringCedula);    //   int

            ResultSet resultadoConsulta = profesor.consultarProfesor(cedulaProfesor);
            tablaProfesores.cargarTabla(resultadoConsulta);
        } else {
            JOptionPane.showMessageDialog(this,"Escriba la cedula a consultar");   
        }
    }
}