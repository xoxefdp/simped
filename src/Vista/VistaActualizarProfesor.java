/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Controlador.Aceptar;
import Controlador.Cancelar;
import Controlador.CerrarVentana;
import Controlador.ConsultarListar;
import Controlador.OyenteAceptar;
import Controlador.OyenteCancelar;
import Controlador.OyenteConsultar;
import Controlador.OyenteListar;
import Modelo.Alumno;
import Modelo.Profesor;
import Modelo.Representante;
import Vista.Formatos.Botonera;
import Vista.Formatos.CampoCombo;
import Vista.Formatos.CampoTexto;
import Vista.Tablas.TablaModAdmRepresentantes;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

/**
 *
 * @author josediaz
 */
public final class VistaActualizarProfesor extends JFrame implements Aceptar, Cancelar, CerrarVentana{
    private final CampoTexto cedula,nombres,apellidos,fechanac,direccion,correo,telefono,titulo;
    private final CampoCombo sexo;
    private final JPanel panelTop;
    private final Botonera boton;
    private final String[] AC = {"Aceptar","Cancelar"};
    private final String[] opcSexo = {"","Masculino","Femenino"};
    private final Profesor profesor;
    private final ResultSet resultadoPr;

    public VistaActualizarProfesor(int cedulaEntrada){
        setTitle("Actualizar Datos de Profesor");
        setResizable(false);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
        /**
         * Elementos del panel superior
         */
        cedula = new CampoTexto("Cedula", 20);
        nombres = new CampoTexto("Nombres",20);
        apellidos = new CampoTexto("Apellidos",20);
        fechanac = new CampoTexto("Fecha de Nacimiento",20);
        direccion  = new CampoTexto("Dirección", 20);
        correo  = new CampoTexto("Correo", 20);
        telefono  = new CampoTexto("Telefono", 20);
        sexo = new CampoCombo("Sexo",opcSexo);
        titulo  = new CampoTexto("Titulo o cargo", 20);

        /**
         * Llenado de campos
         */
        profesor = new Profesor();
        resultadoPr = profesor.consultarProfesor(cedulaEntrada);
        try{
            cedula.cambiarContenido(resultadoPr.getString(0));
            nombres.cambiarContenido(resultadoPr.getString(1));
            apellidos.cambiarContenido(resultadoPr.getString(2));
            fechanac.cambiarContenido(resultadoPr.getString(3));
            direccion.cambiarContenido(resultadoPr.getString(4));
            correo.cambiarContenido(resultadoPr.getString(5));
            telefono.cambiarContenido(resultadoPr.getString(6));
            sexo.seleccionarElemento(resultadoPr.getObject(7));
            titulo.cambiarContenido(resultadoPr.getString(8));   
        } catch (SQLException ex) {
            Logger.getLogger(VistaActualizarEstudiante.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        panelTop = new JPanel();
        panelTop.setLayout(new GridLayout(3,3));
        panelTop.add(cedula);
        panelTop.add(nombres);
        panelTop.add(apellidos);        
        panelTop.add(fechanac);
        panelTop.add(direccion);
        panelTop.add(correo);
        panelTop.add(telefono);
        panelTop.add(sexo);
        panelTop.add(titulo);
        
        /**
         * Elementos inferiores
         */
        boton=new Botonera(2,AC);
        boton.adherirEscucha(0, new OyenteAceptar(this));
        boton.adherirEscucha(1, new OyenteCancelar(this));

        /**
         * Configuracion de Vista
         */
        add(boton);
        add(BorderLayout.NORTH,panelTop);
        add(BorderLayout.SOUTH,boton);
        pack();
        setVisible(true);
    }

    @Override
    public void aceptar() {
        if (cedula.obtenerContenido().length() != 0 && nombres.obtenerContenido().length() != 0 && 
        apellidos.obtenerContenido().length() != 0 && fechanac.obtenerContenido().length() != 0 && 
        direccion.obtenerContenido().length() != 0 && correo.obtenerContenido().length() != 0 && 
        telefono.obtenerContenido().length() != 0 && sexo.obtenerSeleccion().toString().length() != 0 && 
        titulo.obtenerContenido().length() != 0) {
            
            String cedulaPr = cedula.obtenerContenido();
            int cedulaProfesor = Integer.parseInt(cedulaPr);
            
            String nombrePr = nombres.obtenerContenido();
            String apellidoPr = apellidos.obtenerContenido();
            String fechaNacPr = fechanac.obtenerContenido();
            String direccionPr = direccion.obtenerContenido();
            String correoPr = correo.obtenerContenido();
            String telefonoPr = telefono.obtenerContenido();
            String sexoPr = sexo.obtenerSeleccion().toString();
            String tituloPr  = titulo.obtenerContenido();
            
            if (profesor.modificar(cedulaProfesor, nombrePr, apellidoPr, fechaNacPr, direccionPr, telefonoPr, correoPr, tituloPr, sexoPr)) {
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
}
