/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Calendario.Calendario;
import Controlador.Aceptar;
import Controlador.Cancelar;
import Controlador.CerrarVentana;
import Controlador.ConsultarListar;
import Controlador.OyenteAceptar;
import Controlador.OyenteCancelar;
import Controlador.OyenteConsultar;
import Controlador.OyenteListar;
import Modelo.Alumno;
import Modelo.Representante;
import Vista.Formatos.Botonera;
import Vista.Formatos.CampoCombo;
import Vista.Formatos.CampoTexto;
import Vista.Tablas.TablaModAdmRepresentantes;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import static Modelo.MensajesDeError.errorSQL;
import java.awt.FlowLayout;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author josediaz
 */
public class VistaActualizarEstudiante extends JFrame implements Aceptar, Cancelar, CerrarVentana, ConsultarListar{
    private final CampoTexto nombres,apellidos,fechanac,cedula,parentesco;
    private final CampoCombo sexo;
    private final JPanel panelTop,panelBusqueda,panelRepresentante,panelCenter,panelTabla;
    private final Botonera boton,botoneraBU,botoneraLI,botoneraDE,botoneraMR;
    private final String[] AC = {"Aceptar","Cancelar"};
    private final String[] opcSexo = {"","Masculino","Femenino"};
    private Alumno alumno = new Alumno();
    private int codigoAlumno;

    private final Representante representanteModelo = new Representante();
    private final TablaModAdmRepresentantes tablaRepresentantes;
    private final String[] BU = {"Buscar"};
    private final String[] LI = {"Listar Todos"};
    private final String[] DE = {"Detallar"};
    private final String[] MR = {"Manejar Representante"};
    private ResultSet resultadoAl,resultadoRep,consultaRep;
    private String mensaje;

    public VistaActualizarEstudiante(int codigoEstudiante){
        setTitle("Actualizar Datos de Alumno");
        setResizable(false);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
        /**
         * Elementos del panel superior
         */
        nombres = new CampoTexto("Nombres",20);
        apellidos = new CampoTexto("Apellidos",20);
        fechanac = new CampoTexto("Fecha de Nacimiento",20);
        sexo = new CampoCombo("Sexo",opcSexo);        

        /**
         * Invoca calendario al enfocar
         */
        fechanac.campo.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (fechanac.obtenerContenido().length() == 0) {
                    Calendario calendario = new Calendario(fechanac.campo);
                }
            }
        });
        
        panelTop = new JPanel();
        panelTop.setLayout(new GridLayout(2,3));
        panelTop.add(nombres);
        panelTop.add(apellidos);
        panelTop.add(fechanac);
        panelTop.add(sexo);
        
        /**
         * Elementos del panel central
         */
        cedula = new CampoTexto("",15);
        botoneraBU = new Botonera(BU);
        botoneraBU.adherirEscucha(0, new OyenteConsultar(this));
        panelBusqueda = new JPanel();
        panelBusqueda.add(cedula);
        panelBusqueda.add(botoneraBU);

        botoneraLI = new Botonera(LI);
        botoneraLI.adherirEscucha(0, new OyenteListar(this));
        
        parentesco = new CampoTexto("Parentesco",20);
        
        botoneraDE = new Botonera(DE);
        botoneraDE.adherirEscucha(0, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                detallar();
            }
        });
        
        panelRepresentante = new JPanel();
        panelRepresentante.setLayout(new GridLayout(2,2));
        panelRepresentante.add(panelBusqueda);
        panelRepresentante.add(parentesco);
        panelRepresentante.add(botoneraLI);
        panelRepresentante.add(botoneraDE);
        
        botoneraMR = new Botonera(MR);
        botoneraMR.adherirEscucha(0, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                VistaListaRepresentante vistaListaRepresentante = new VistaListaRepresentante();
            }
        });
        
        resultadoRep = representanteModelo.consultarRepresentantes();
        tablaRepresentantes = new TablaModAdmRepresentantes();
        tablaRepresentantes.cargarTabla(resultadoRep);
        
        panelTabla = new JPanel();
        panelTabla.setLayout(new FlowLayout());
        panelTabla.add(botoneraMR);
        panelTabla.add(tablaRepresentantes);

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
         * Llenado de campos con datos
         */
        codigoAlumno = codigoEstudiante;
        resultadoAl = alumno.consultarAlumnoRepresentante(codigoAlumno);
        try {
            while(resultadoAl.next()){
                nombres.cambiarContenido(resultadoAl.getString(2));
                apellidos.cambiarContenido(resultadoAl.getString(3));
                fechanac.cambiarContenido(resultadoAl.getString(4));
                sexo.seleccionarElemento(resultadoAl.getObject(5));
                parentesco.cambiarContenido(resultadoAl.getString(6));
                cedula.cambiarContenido(resultadoAl.getString(7));
            }
        } catch(SQLException error){
            mensaje = errorSQL(error.getSQLState());
            JOptionPane.showMessageDialog(null, mensaje);
        }
        
        panelCenter = new JPanel();
        panelCenter.setLayout(new GridLayout(2,1));
        panelCenter.setBorder(BorderFactory.createTitledBorder("Modulo de Seleccion de Representante"));
        panelCenter.add(panelRepresentante);
        panelCenter.add(panelTabla);
        panelCenter.getPreferredSize();
        
        /**
         * Elementos inferiores
         */
        boton=new Botonera(AC);
        boton.adherirEscucha(0, new OyenteAceptar(this));
        boton.adherirEscucha(1, new OyenteCancelar(this));

        /**
         * Configuracion de Vista
         */
        add(boton);
        add(BorderLayout.NORTH,panelTop);
        add(BorderLayout.CENTER,panelCenter);
        add(BorderLayout.SOUTH,boton);
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
            
            JOptionPane.showMessageDialog(this,
                    "Datos Representante \n\n"
                +   "Cedula: "+cedulaRepresentante+"\n"
                +   "Nombres: "+stringNomRepresentante+"\n"
                +   "Apellidos: "+stringApeRepresentante+"\n"
                +   "Telefono: "+stringTelRepresentante+"\n"
                +   "Dirección: "+stringDirRepresentante+"\n"
                +   "Correo: "+stringCorRepresentante+"\n"
                +   "Fecha de Nacimiento: "+stringFecRepresentante+"\n"
                +   "Sexo: "+stringSexRepresentante+"\n");
        } else {
            JOptionPane.showMessageDialog(this,"Seleccione antes en la tabla el representante a detallar");
        }
    }

    @Override
    public void aceptar() {
        if (nombres.obtenerContenido().length() != 0 && apellidos.obtenerContenido().length() != 0 &&
        fechanac.obtenerContenido().length() != 0 && sexo.obtenerSeleccion().toString().length() != 0 &&
        cedula.obtenerContenido().length() != 0 && parentesco.obtenerContenido().length() != 0) {
            String nombreAl = nombres.obtenerContenido();
            String apellidoAl = apellidos.obtenerContenido();
            String fechaNacAl = fechanac.obtenerContenido();
            String sexoAl = sexo.obtenerSeleccion().toString();
            int cedulaRep = Integer.parseInt(cedula.obtenerContenido());
            String parentescoRep = parentesco.obtenerContenido();

            // chequear cedula antes de cambiar en registro
            consultaRep = representanteModelo.consultarRepresentante(cedulaRep);
            
            if (alumno.modificar(codigoAlumno, nombreAl, apellidoAl, fechaNacAl, sexoAl, cedulaRep, parentescoRep)) {
                cerrarVentana();
            } else {
                JOptionPane.showMessageDialog(this,"Error al modificar");
            }
        } else {
            JOptionPane.showMessageDialog(this,"Existen campos vacios");
        }
    }

    @Override
    public void cancelar() {
        cerrarVentana();
    }

    @Override
    public void cerrarVentana() {
        this.dispose();
    }

    @Override
    public void listar() { // consulta todos
        ResultSet resultadoListar = representanteModelo.consultarRepresentantes();
        tablaRepresentantes.cargarTabla(resultadoListar);
    }
    
    @Override
    public void consultar() { // consulta uno
        if (cedula.obtenerContenido().length() != 0) {
            String stringCedula = cedula.obtenerContenido(); //falta generalizar
            int cedulaRepresentante=Integer.parseInt(stringCedula);    //   int

            ResultSet resultadoConsulta = representanteModelo.consultarRepresentante(cedulaRepresentante);
            tablaRepresentantes.cargarTabla(resultadoConsulta);
        } else {
            JOptionPane.showMessageDialog(this,"Escriba la cedula a consultar");
        }
    }
}
