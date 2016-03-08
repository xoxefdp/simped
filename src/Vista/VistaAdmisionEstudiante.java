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
import Vista.Tablas.TablaRepresentantes;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.ResultSet;
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
public final class VistaAdmisionEstudiante extends JFrame implements Aceptar, Cancelar, CerrarVentana, ConsultarListar{
    private final CampoTexto nombres,apellidos,fechanac,cedula,parentesco;
    private final CampoCombo sexo;
    private final JPanel panelTop,panelBusqueda,panelRepresentante,panelCenter,panelTabla;
    private final Botonera boton,botoneraBU,botoneraLI,botoneraDE,botoneraMR;
    private final String[] AC = {"Aceptar","Cancelar"};
    private final String[] opcSexo = {"","Masculino","Femenino"};
    private final Alumno alumno = new Alumno();

    private final Representante representanteModelo;
    private final TablaRepresentantes tablaRepresentantes;
    private final String[] BU = {"Buscar"};
    private final String[] LI = {"Listar Todos"};
    private final String[] DE = {"Detallar"};
    private final String[] MR = {"Manejar Representante"};
    private final ResultSet resultado;

    public VistaAdmisionEstudiante(){
        setTitle("Ingreso de Alumno");
        setResizable(false);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
        setSize(750, 550);

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
        panelTop.setLayout(new GridLayout(2,2));
        panelTop.add(nombres);
        panelTop.add(apellidos);
        panelTop.add(fechanac);
        panelTop.add(sexo);
        
        /**
         * Elementos del panel central
         */
        cedula = new CampoTexto("Cedula",15);
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
        
        botoneraMR = new Botonera(MR);
        botoneraMR.adherirEscucha(0, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                VistaListaRepresentante vistaListaRepresentante = new VistaListaRepresentante();
            }
        });
        panelRepresentante = new JPanel();
        panelRepresentante.setLayout(new FlowLayout());
        //panelRepresentante.setBorder(BorderFactory.createTitledBorder("Listado de Representante"));
        panelRepresentante.add(panelBusqueda);
        panelRepresentante.add(parentesco);
        panelRepresentante.add(botoneraMR);

        representanteModelo = new Representante();
        resultado = representanteModelo.consultarRepresentantes();
        tablaRepresentantes = new TablaRepresentantes(375,75);
        tablaRepresentantes.cargarTabla(resultado);
        panelTabla = new JPanel();
        panelTabla.setLayout(new FlowLayout());
        panelTabla.setBorder(BorderFactory.createTitledBorder("Listado de Representante"));
        panelTabla.add(tablaRepresentantes);
        panelTabla.add(botoneraLI);
        panelTabla.add(botoneraDE);
        
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
        //add(boton);
        add(BorderLayout.NORTH,panelTop);
        add(BorderLayout.CENTER,panelCenter);
        add(BorderLayout.SOUTH,boton);
        //pack();
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
        parentesco.obtenerContenido().length() != 0 && tablaRepresentantes.tabla.getSelectedRow() >= 0) {
            String nombreAl = nombres.obtenerContenido();
            String apellidoAl = apellidos.obtenerContenido();
            String fechaNacAl = fechanac.obtenerContenido();
            String sexoAl = sexo.obtenerSeleccion().toString();
            String parentescoRep = parentesco.obtenerContenido();
            
            //String stringRepresentante=(String)tablaRepresentantes.tablaModelo.getValueAt(tablaRepresentantes.tabla.getSelectedRow(), 0);
            
            int cedulaRepresentante = Integer.parseInt(cedula.obtenerContenido());
            // chequear cedula antes de cambiar en registro
            //int cedulaRepresentante=Integer.parseInt(stringRepresentante);
            
            if (alumno.incluir(nombreAl, apellidoAl, fechaNacAl, sexoAl, cedulaRepresentante, parentescoRep)) {
                cerrarVentana();
            } else {
                JOptionPane.showMessageDialog(this,"Error al insertar");
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
