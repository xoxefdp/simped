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
import Modelo.AlumnoRepresentante;
import static Modelo.MensajesDeError.errorSQL;
import Modelo.Representante;
import Vista.Formatos.CampoTexto;
import Vista.Formatos.Botonera;
import Vista.Tablas.TablaRepresentantes;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * 
 * @author josediaz
 */
public class VistaListaRepresentante extends JFrame implements Incluir, Modificar, Eliminar, ConsultarListar{
    private TablaRepresentantes tablaRepresentantes;
    private Botonera botoneraME,botoneraBU,botoneraDE,botoneraIC,botoneraLI;
    private CampoTexto cedula;
    private JPanel panelBusqueda,panelTop, panelBottom;
    private final String[] IC = {"Incluir"};
    private final String[] ME = {"Modificar","Eliminar"};
    private final String[] BU = {"Buscar"};
    private final String[] LI = {"Listar Todos"};
    private final String[] DE = {"Detallar"};
    private final Representante representante = new Representante();
    private ResultSet resultado;
    
    /**
     * Crea la interface de la clase.
     */
    public VistaListaRepresentante(){
        crearGui();
    }
    
    final void crearGui(){
        setTitle("Lista de Representantes");
        setResizable(false);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                
        /**
         * Elementos del panel superior
         */
        cedula=new CampoTexto("",15);
        cedula.campo.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent ke) {
                int escrito = ke.getKeyChar();
                if((escrito<'0' || escrito>'9')) ke.consume(); 
                if(cedula.longuitudDelContenido() >= 8) ke.consume(); 
            }
        });
        botoneraBU = new Botonera(BU);
        botoneraBU.adherirEscucha(0, new OyenteConsultar(this));
        
        panelBusqueda = new JPanel();
        panelBusqueda.setBorder(BorderFactory.createTitledBorder("Cedula Representante"));
        panelBusqueda.add(cedula);
        panelBusqueda.add(botoneraBU);
        
        botoneraLI = new Botonera(LI);
        botoneraLI.adherirEscucha(0, new OyenteListar(this));
        
        botoneraDE = new Botonera(DE);
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
        resultado = representante.consultarRepresentantes();
        tablaRepresentantes = new TablaRepresentantes(1075,250);
        tablaRepresentantes.cargarTabla(resultado);
   
        /**
         * Ejecuta eventos de selección en tabla
         */
        tablaRepresentantes.tabla.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int row = tablaRepresentantes.tabla.getSelectedRow();
                if (row >= 0) {
                    cedula.cambiarContenido((String)tablaRepresentantes.tabla.getValueAt(row, 0));
                }
            }
        });
        
        /**
         * Elementos del panel inferior
         */
        botoneraIC = new Botonera(IC);
        botoneraIC.adherirEscucha(0,new OyenteIncluir(this));
        
        botoneraME = new Botonera(ME);
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
        add(BorderLayout.CENTER,tablaRepresentantes);
        add(BorderLayout.SOUTH, panelBottom);

        pack();
        setVisible(true);
    }

    public void detallar() {
        if (tablaRepresentantes.tabla.getSelectedRow()>=0){
            
            String stringCedRepresentante=(String)tablaRepresentantes.tablaModelo.getValueAt(tablaRepresentantes.tabla.getSelectedRow(), 0); //string
            int cedulaRepresentante=Integer.parseInt(stringCedRepresentante);    //   int

            String stringNomRepresentante=(String)tablaRepresentantes.tablaModelo.getValueAt(tablaRepresentantes.tabla.getSelectedRow(), 1); //string
            String stringApeRepresentante=(String)tablaRepresentantes.tablaModelo.getValueAt(tablaRepresentantes.tabla.getSelectedRow(), 2); //string
            String stringTelRepresentante=(String)tablaRepresentantes.tablaModelo.getValueAt(tablaRepresentantes.tabla.getSelectedRow(), 3); //string
            String stringDirRepresentante=(String)tablaRepresentantes.tablaModelo.getValueAt(tablaRepresentantes.tabla.getSelectedRow(), 4); //string            
            String stringCorRepresentante=(String)tablaRepresentantes.tablaModelo.getValueAt(tablaRepresentantes.tabla.getSelectedRow(), 5); //string
            String stringFecRepresentante=(String)tablaRepresentantes.tablaModelo.getValueAt(tablaRepresentantes.tabla.getSelectedRow(), 6); //string
            String stringSexRepresentante=(String)tablaRepresentantes.tablaModelo.getValueAt(tablaRepresentantes.tabla.getSelectedRow(), 7); //string
            
            JOptionPane.showMessageDialog(this,"Datos Representante \n\n"
                                            +  "Cedula: "+cedulaRepresentante+"\n"
                                            +  "Nombres: "+stringNomRepresentante+"\n"
                                            +  "Apellidos: "+stringApeRepresentante+"\n"
                                            +  "Telefono: "+stringTelRepresentante+"\n"
                                            +  "Dirección: "+stringDirRepresentante+"\n"
                                            +  "Correo: "+stringCorRepresentante+"\n"
                                            +  "Fecha de Nacimiento: "+stringFecRepresentante+"\n"
                                            +  "Sexo: "+stringSexRepresentante+"\n");
        } else {
            JOptionPane.showMessageDialog(this,"Seleccione antes en la tabla el representante a detallar");
        }
    }
    
    @Override
    public void incluir() {
        VistaAdmisionRepresentante vistaAdmisionRepresentante = new VistaAdmisionRepresentante();
    }

    @Override
    public void modificar() {
        if (tablaRepresentantes.tabla.getSelectedRow()>=0){
            
            String stringRepresentante; // debo convertirlo a int para pasarlo al metodo y a la base de datos
            int codigoRepresentante;            
            
            stringRepresentante=(String)tablaRepresentantes.tablaModelo.getValueAt(tablaRepresentantes.tabla.getSelectedRow(), 0); //string 
            codigoRepresentante=Integer.parseInt(stringRepresentante);    //   int
            VistaActualizarRepresentante vistaActualizarRepresentante = new VistaActualizarRepresentante(codigoRepresentante);
        } else {
            JOptionPane.showMessageDialog(this,"Seleccione antes en la tabla el representante a modificar");
        }
    }
    
    @Override
    public void eliminar() {
        if (tablaRepresentantes.tabla.getSelectedRow()>=0){
            
            String stringRepresentante; // debo convertirlo a int para pasarlo al metodo y a la base de datos
            int cedulaRepresentante;            
            
            stringRepresentante=(String)tablaRepresentantes.tablaModelo.getValueAt(tablaRepresentantes.tabla.getSelectedRow(), 0); //string 
            cedulaRepresentante=Integer.parseInt(stringRepresentante);    //   int
            
            // si confirma elimina de la base de datos
            if (tablaRepresentantes.eliminarFila()) {
                AlumnoRepresentante alumnoRepresentante = new AlumnoRepresentante();
                ResultSet datos = alumnoRepresentante.consultarRepresentanteAlumnos(cedulaRepresentante);
                try{
                    if(!datos.isBeforeFirst()){
                        representante.eliminar(cedulaRepresentante);
                    } else {
                        int respuesta = JOptionPane.showConfirmDialog(this, "Cambie los representantes de los alumnos o se eliminaran tambien");
                        if (respuesta == JOptionPane.OK_OPTION){
                            try {
                                while(datos.next()) {
                                    VistaActualizarEstudiante vistaActualizarEstudiante = new VistaActualizarEstudiante(datos.getInt(1));
                                }
                                JOptionPane.showMessageDialog(this,"Ahora modifique y refresque la tabla para intentar eliminar de nuevo");
                            }catch(SQLException error){
                                String mensaje = errorSQL(error.getSQLState());
                                JOptionPane.showMessageDialog(null,mensaje);
                            }
                        }
                    }
                }catch(SQLException error){
                    String mensaje = errorSQL(error.getSQLState());
                    JOptionPane.showMessageDialog(null,mensaje);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this,"Seleccione antes en la tabla el representante a eliminar");
        }
    }
    
    @Override
    public void listar() { // consulta todos
        ResultSet resultadoListar = representante.consultarRepresentantes();
        tablaRepresentantes.cargarTabla(resultadoListar);
    }
    
    @Override
    public void consultar() { // consulta uno
        if (cedula.longuitudDelContenido() != 0) {
            String stringCedula = cedula.obtenerContenido(); //falta generalizar
            int cedulaRepresentante=Integer.parseInt(stringCedula);    //   int

            ResultSet resultadoConsulta = representante.consultarRepresentante(cedulaRepresentante);
            tablaRepresentantes.cargarTabla(resultadoConsulta);
        } else {
            JOptionPane.showMessageDialog(this,"Escriba la cedula a consultar");   
        }
    }
}