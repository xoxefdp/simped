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
import Vista.Componentes.CampoTexto;
import Vista.Formatos.Botonera;
import Vista.Tablas.TablaProfesores;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.sql.ResultSet;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
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
        
        setSize(400,400);
        pack();
        setVisible(true);
    }

    @Override
    public void incluir() {
        VistaAdmisionProfesor vistaAdmisionProfesor = new VistaAdmisionProfesor();
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modificar() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        }
    }
    
    @Override
    public void listar() { // consulta todos
        ResultSet resultadoListar = profesor.consultarProfesores();
        tablaProfesores.cargarTabla(resultadoListar);
    }
    
    @Override
    public void consultar() { // consulta uno
        
        String stringCedula = cedula.obtenerContenido(); //falta generalizar
        int cedulaProfesor=Integer.parseInt(stringCedula);    //   int
        
        ResultSet resultadoConsulta = profesor.consultarProfesor(cedulaProfesor);
        tablaProfesores.cargarTabla(resultadoConsulta);
    }
}