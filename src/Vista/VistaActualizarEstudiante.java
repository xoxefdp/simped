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
import Modelo.Alumno;
import Vista.Formatos.CampoTexto;
import Vista.Formatos.Botonera;
import Vista.Formatos.CampoCombo;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author yonalix garcia
 */
public class VistaActualizarEstudiante extends JFrame implements Aceptar, Cancelar, CerrarVentana{
    private final CampoTexto nombres, apellidos, fechanac, representante;
    private final CampoCombo sexo;
    private final JPanel panelTop;
    private final Botonera boton;
    private final String[] AC = {"Aceptar","Cancelar"};
    private final String[] opcSexo = {"Masculino","Femenino"};
    
    public VistaActualizarEstudiante(int codigoEstudiante){
        setTitle("Actualizar Datos Alumno");
        setLayout(new BorderLayout());
        setSize(600,400);
        
        nombres = new CampoTexto("Nombres",20);
        apellidos = new CampoTexto("Apellidos",20);
        fechanac = new CampoTexto("Fecha de Nacimiento",20);
        sexo = new CampoCombo("Sexo",opcSexo);
        representante = new CampoTexto("Representante",20);
        
        panelTop = new JPanel();
        panelTop.setLayout(new GridLayout(3,2));
        panelTop.add(nombres);
        panelTop.add(apellidos);
        panelTop.add(fechanac);
        panelTop.add(sexo);
        panelTop.add(representante);
        
        boton=new Botonera(2,AC);

        boton.adherirEscucha(0, new OyenteAceptar(this));
        boton.adherirEscucha(1, new OyenteCancelar(this));

        add(boton);
        add(BorderLayout.NORTH,panelTop);
        add(BorderLayout.SOUTH,boton);

        setVisible(true);
    }

    @Override
    public void aceptar() {
        Alumno alumno = new Alumno();
        /*
        alumno.modificar(int codigo, String nombre, String apellido,
                      String telefono, String direccion, String correo,
                      String parentesco, String fechaNacimiento, String sexo);
        */
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
