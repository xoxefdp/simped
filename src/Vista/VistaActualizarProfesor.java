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
import Controlador.OyenteAceptar;
import Controlador.OyenteCancelar;
import static Modelo.MensajesDeError.errorSQL;
import Modelo.Profesor;
import Vista.Formatos.Botonera;
import Vista.Formatos.CampoAreaTexto;
import Vista.Formatos.CampoCombo;
import Vista.Formatos.CampoTexto;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

/**
 *
 * @author josediaz
 */
public class VistaActualizarProfesor extends JFrame implements Aceptar, Cancelar, CerrarVentana{
    private final CampoTexto cedula,nombres,apellidos,fechanac,correo,telefono,titulo;
    private final CampoAreaTexto direccion;
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
        cedula = new CampoTexto("Cedula",20);
        nombres = new CampoTexto("Nombres",20);
        apellidos = new CampoTexto("Apellidos",20);
        fechanac = new CampoTexto("Fecha de Nacimiento",20);
        direccion = new CampoAreaTexto("Dirección",20,2);
        correo = new CampoTexto("Correo",20);
        telefono = new CampoTexto("Telefono",20);
        sexo = new CampoCombo("Sexo",opcSexo);
        titulo = new CampoTexto("Titulo",20);        
        
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
        
        /**
         * Llenado de campos
         */
        profesor = new Profesor();
        resultadoPr = profesor.consultarProfesor(cedulaEntrada);
        try{
            while(resultadoPr.next()){
                cedula.cambiarContenido(resultadoPr.getString(1));
                nombres.cambiarContenido(resultadoPr.getString(2));
                apellidos.cambiarContenido(resultadoPr.getString(3));
                fechanac.cambiarContenido(resultadoPr.getString(4));
                direccion.cambiarContenido(resultadoPr.getString(5));
                correo.cambiarContenido(resultadoPr.getString(6));
                telefono.cambiarContenido(resultadoPr.getString(7));
                sexo.seleccionarElemento(resultadoPr.getObject(8));
                titulo.cambiarContenido(resultadoPr.getString(9));
            }
        } catch(SQLException error){
            String mensaje = errorSQL(error.getSQLState());
            JOptionPane.showMessageDialog(null, mensaje);
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
        boton=new Botonera(AC);
        boton.adherirEscucha(0, new OyenteAceptar(this));
        boton.adherirEscucha(1, new OyenteCancelar(this));

        /**
         * Configuracion de Vista
         */
        add(BorderLayout.NORTH,panelTop);
        add(BorderLayout.SOUTH,boton);
        pack();
        setVisible(true);
    }

    @Override
    public void aceptar() {
        if (cedula.obtenerContenido().length() != 0 && nombres.obtenerContenido().length() != 0 &&
        apellidos.obtenerContenido().length() != 0 && telefono.obtenerContenido().length() != 0 &&
        direccion.obtenerContenido().length() != 0 && correo.obtenerContenido().length() != 0 &&
        fechanac.obtenerContenido().length() != 0 && sexo.obtenerSeleccion().toString().length() != 0 &&
        titulo.obtenerContenido().length() != 0) {
            
            String strCedulaProf = cedula.obtenerContenido();
            int cedulaProf = Integer.parseInt(strCedulaProf);
            
            String nombreProf = nombres.obtenerContenido();
            String apellidoProf = apellidos.obtenerContenido();
            String fechaNacProf = fechanac.obtenerContenido();
            String direccionProf = direccion.obtenerContenido();
            String correoProf = correo.obtenerContenido();
            String telefonoProf = telefono.obtenerContenido();
            String sexoProf = (String) sexo.obtenerSeleccion();
            String tituloProf = titulo.obtenerContenido();
            
            if (profesor.modificar(cedulaProf, nombreProf, apellidoProf, fechaNacProf, direccionProf, telefonoProf, correoProf, tituloProf, sexoProf)){
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
}
