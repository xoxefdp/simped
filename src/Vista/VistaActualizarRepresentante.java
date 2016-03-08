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
import Modelo.Representante;
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
public final class VistaActualizarRepresentante extends JFrame implements Aceptar, Cancelar, CerrarVentana{
    private final CampoTexto cedula,nombres,apellidos,telefono,correo,fechanac;
    private final CampoAreaTexto direccion;
    private final CampoCombo sexo;
    private final JPanel panelTop;
    private final Botonera boton;
    private final String[] AC = {"Aceptar","Cancelar"};
    private final String[] opcSexo = {"","Masculino","Femenino"};
    private Representante representante = new Representante();
    private ResultSet resultado;

    public VistaActualizarRepresentante(int cedulaRepresentante){
        setTitle("Actualizar Datos de Representante");
        setResizable(false);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        /**
         * Elementos del panel superior
         */
        cedula = new CampoTexto("Cedula",20); cedula.hacerEditable(false);
        nombres = new CampoTexto("Nombres",20);
        apellidos = new CampoTexto("Apellidos",20);
        telefono = new CampoTexto("Telefono",20);
        direccion = new CampoAreaTexto("Dirección",20,2);
        correo = new CampoTexto("Correo",20);
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
        
        /**
         * Llenado de campos
         */
        resultado = representante.consultarRepresentante(cedulaRepresentante);
        try{
            while(resultado.next()){
                cedula.cambiarContenido(resultado.getString(1));
                nombres.cambiarContenido(resultado.getString(2));
                apellidos.cambiarContenido(resultado.getString(3));
                telefono.cambiarContenido(resultado.getString(4));
                direccion.cambiarContenido(resultado.getString(5));
                correo.cambiarContenido(resultado.getString(6));
                fechanac.cambiarContenido(resultado.getString(7));
                sexo.seleccionarElemento(resultado.getObject(8));
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
        panelTop.add(telefono);
        panelTop.add(direccion);
        panelTop.add(correo);
        panelTop.add(fechanac);
        panelTop.add(sexo);
        
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
        fechanac.obtenerContenido().length() != 0 && sexo.obtenerSeleccion().toString().length() != 0) {
            
            String strCedulaRep = cedula.obtenerContenido();
            
            int cedulaRep = Integer.parseInt(strCedulaRep);
            String nombreRep = nombres.obtenerContenido();
            String apellidoRep = apellidos.obtenerContenido();
            String telefonoRep = telefono.obtenerContenido();
            String direccionRep = direccion.obtenerContenido();
            String correoRep = correo.obtenerContenido();
            String fechaNacRep = fechanac.obtenerContenido();
            String sexoRep = sexo.obtenerSeleccion().toString();
            
            if (representante.modificar(cedulaRep, nombreRep, apellidoRep, telefonoRep, direccionRep, correoRep, fechaNacRep, sexoRep)) {
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
