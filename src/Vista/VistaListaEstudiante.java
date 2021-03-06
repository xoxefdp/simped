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
import Modelo.Alumno;
import Modelo.AlumnoGrado;
import Modelo.AlumnoRepresentante;
import static Modelo.MensajesDeError.errorSQL;
import Modelo.Representante;
import Vista.Formatos.CampoTexto;
import Vista.Formatos.Botonera;
import Vista.Tablas.TablaAlumnos;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * 
 * @author josediaz
 */
public class VistaListaEstudiante extends JFrame implements Incluir, Modificar, Eliminar, ConsultarListar{
    private TablaAlumnos tablaAlumnos;
    private Botonera botoneraME,botoneraBU,botoneraDE,botoneraIC,botoneraLI;
    private CampoTexto codigo;
    private JPanel panelBusqueda,panelTop, panelBottom;
    private String nombreRep, apellidoRep;
    private final String[] IC = {"Incluir"};
    private final String[] ME = {"Modificar","Eliminar"};
    private final String[] BU = {"Buscar"};
    private final String[] LI = {"Listar Todos"};
    private final String[] DE = {"Detallar"};
    private final Alumno alumno = new Alumno();
    private ResultSet resultado;
    /**
     * Crea la interface de la clase.
     */
    public VistaListaEstudiante(){
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
        codigo.campo.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent ke) {
                int escrito = ke.getKeyChar();
      
                if((escrito<'0' || escrito>'9')) ke.consume(); 
                if(codigo.longuitudDelContenido() >= 5) ke.consume(); 
            }
        });
        
        botoneraBU = new Botonera(BU);
        botoneraBU.adherirEscucha(0, new OyenteConsultar(this));
        
        panelBusqueda = new JPanel();
        panelBusqueda.setBorder(BorderFactory.createTitledBorder("Codigo Estudiante"));
        panelBusqueda.add(codigo);
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
        resultado = alumno.consultarAlumnosRepresentantes();
        tablaAlumnos = new TablaAlumnos(825,250);
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
        
        botoneraIC = new Botonera(IC);
        botoneraIC.adherirEscucha(0,new OyenteIncluir(this));
        
        botoneraME = new Botonera(ME);
        botoneraME.adherirEscucha(0,new OyenteModificar(this));
        botoneraME.adherirEscucha(1,new OyenteEliminar(this));
        
        panelBottom =new JPanel();
        panelBottom.setBorder(BorderFactory.createTitledBorder("Operaciones"));
        panelBottom.add(botoneraIC);
        panelBottom.add(botoneraME);        
        
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
            String stringParRepresenta=(String)tablaAlumnos.tablaModelo.getValueAt(tablaAlumnos.tabla.getSelectedRow(), 5); //string
            String stringRepEstudiante=(String)tablaAlumnos.tablaModelo.getValueAt(tablaAlumnos.tabla.getSelectedRow(), 6); //string
            int cedulaRepEstudiante=Integer.parseInt(stringRepEstudiante);    //   int
            
            Representante representante = new Representante();
            ResultSet rep = representante.consultarRepresentante(cedulaRepEstudiante);
            
            try{
                while(rep.next()){
                    nombreRep = rep.getString(2);
                    apellidoRep = rep.getString(3);
                }
            }catch(SQLException error){
                String mensaje = errorSQL(error.getSQLState());
                JOptionPane.showMessageDialog(null,mensaje);
            }
            
            JOptionPane.showMessageDialog(this,"Datos Estudiante \n\n"
                                            +  "Codigo Estudiante: "+codigoEstudiante+"\n"
                                            +  "Nombres: "+stringNomEstudiante+"\n"
                                            +  "Apellidos: "+stringApeEstudiante+"\n"
                                            +  "Fecha de Nacimiento: "+stringFecEstudiante+"\n"
                                            +  "Sexo: "+stringSexEstudiante+"\n\n"
                                            +  "Representante: "+nombreRep+" "+apellidoRep+"\n"
                                            +  "Cedula Representante: "+cedulaRepEstudiante+"\n"
                                            +  "Parentesco: "+stringParRepresenta+"\n");
        } else {
            JOptionPane.showMessageDialog(this,"Seleccione antes en la tabla el estudiante a detallar");
        }
    }

    @Override
    public void incluir() {
        VistaAdmisionEstudiante vistaAdmisionEstudiante = new VistaAdmisionEstudiante();
    }

    @Override
    public void modificar() {
        if (tablaAlumnos.tabla.getSelectedRow()>=0){
            
            String stringEstudiante; // debo convertirlo a int para pasarlo al metodo y a la base de datos
            int codigoEstudiante;            
            
            stringEstudiante=(String)tablaAlumnos.tablaModelo.getValueAt(tablaAlumnos.tabla.getSelectedRow(), 0); //string 
            codigoEstudiante=Integer.parseInt(stringEstudiante);    //   int
            VistaActualizarEstudiante vistaActualizarEstudiante = new VistaActualizarEstudiante(codigoEstudiante);
        } else {
            JOptionPane.showMessageDialog(this,"Seleccione antes en la tabla el estudiante a modificar");
        }
    }
    
    @Override
    public void eliminar() {
        if (tablaAlumnos.tabla.getSelectedRow() >= 0){
            
            String stringEstudiante; // debo convertirlo a int para pasarlo al metodo y a la base de datos
            int codigoEstudiante;            
            
            stringEstudiante=(String)tablaAlumnos.tablaModelo.getValueAt(tablaAlumnos.tabla.getSelectedRow(), 0); //string 
            codigoEstudiante=Integer.parseInt(stringEstudiante);    //   int
            
            // si confirma elimina de la base de datos
            if (tablaAlumnos.eliminarFila()) {
                AlumnoGrado alumnoGrado = new AlumnoGrado();
                ResultSet datos = alumnoGrado.consultarAlumnoGradosFechas(codigoEstudiante);
                try{
                    if(!datos.isBeforeFirst()){
                        alumno.eliminar(codigoEstudiante);
                    } else {
                        JOptionPane.showMessageDialog(this,"No se puede eliminar a un estudiante regular inscrito en un grado, refresque lista");
                        VistaListaGrado vistaListagrado = new VistaListaGrado();
                    }
                }catch(SQLException error){
                    String mensaje = errorSQL(error.getSQLState());
                    JOptionPane.showMessageDialog(null,mensaje);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this,"Seleccione antes en la tabla el estudiante a eliminar");
        }
    }
    
    @Override
    public void listar() { // consulta todos
        ResultSet resultadoListar = alumno.consultarAlumnosRepresentantes();
        tablaAlumnos.cargarTabla(resultadoListar);
    }
    
    @Override
    public void consultar() { // consulta uno
        if (codigo.longuitudDelContenido() != 0) {
            String stringCodigo = codigo.obtenerContenido(); //falta generalizar
            int codigoAlumno=Integer.parseInt(stringCodigo);    //   int

            ResultSet resultadoConsulta = alumno.consultarAlumnoRepresentante(codigoAlumno);
            tablaAlumnos.cargarTabla(resultadoConsulta);
        } else {
            JOptionPane.showMessageDialog(this,"Escriba el codigo a consultar");
        }
    }
}