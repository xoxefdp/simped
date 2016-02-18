/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Controlador.Aceptar;
import Controlador.Cancelar;
import Controlador.CerrarVentana;
import Controlador.OyenteAceptar;
import Controlador.OyenteCancelar;
import Modelo.Representante;
import Vista.Formatos.Botonera;
import Vista.Formatos.CampoAreaTexto;
import Vista.Formatos.CampoCombo;
import Vista.Formatos.CampoTexto;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

/**
 *
 * @author josediaz
 */
public final class VistaAdmisionRepresentante extends JFrame implements Aceptar, Cancelar, CerrarVentana{
    private final CampoTexto cedula,nombres,apellidos,telefono,correo;
    CampoTexto fechanac;
    private final CampoAreaTexto direccion;
    private final CampoCombo sexo;
    private final JPanel panelTop;
    private final Botonera boton;
    private final String[] AC = {"Aceptar","Cancelar"};
    private final String[] opcSexo = {"","Masculino","Femenino"};

    public VistaAdmisionRepresentante(){
        setTitle("Ingreso de Representante");
        setResizable(false);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        /**
         * Elementos del panel superior
         */
        cedula = new CampoTexto("Cedula",20);
        nombres = new CampoTexto("Nombres",20);
        apellidos = new CampoTexto("Apellidos",20);
        telefono = new CampoTexto("Telefono",20);
        direccion = new CampoAreaTexto("Dirección",20,2);
        correo = new CampoTexto("Correo",20);
        fechanac = new CampoTexto("Fecha de Nacimiento",20);
        sexo = new CampoCombo("Sexo",opcSexo);

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
        boton=new Botonera(2,AC);
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
        Representante representante = new Representante();
        
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
            
            if (representante.incluir(cedulaRep, nombreRep, apellidoRep, telefonoRep, direccionRep, correoRep, fechaNacRep, sexoRep)) {
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
