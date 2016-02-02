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
import Vista.Formatos.Botonera;
import Vista.Componentes.Direccion;
import Vista.Componentes.FechaNacPf;
import Vista.Componentes.Nacionalidad;
import Vista.Componentes.Nombres;
import Vista.Componentes.Telefonos;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author yonalix
 */
public class VistaAdmisionEstudiante extends JFrame implements Aceptar, Cancelar, CerrarVentana{
    Nombres nomb;
    Nacionalidad nac;
    Direccion dir;
    JPanel panel1;
    Botonera boton;
    JButton b1;
    Telefonos telef;
    FechaNacPf feP;
    String[] AC = {"Aceptar","Cancelar"};
    
    public VistaAdmisionEstudiante(){
        setTitle("Datos Alumno");
        setLayout(new BorderLayout());
        setSize(600,400);
        nomb=new Nombres();
        nac=new Nacionalidad();
        boton=new Botonera(2,AC);
        
        boton.adherirEscucha(0, new OyenteAceptar(this));
        boton.adherirEscucha(1, new OyenteCancelar(this));
        
        add(nomb);
        add(boton);
        add(BorderLayout.NORTH,nomb);
        add(BorderLayout.SOUTH,boton);
        setVisible(true);
        
        /*
        botoneraProd.adherirEscucha(0, new OyenteIncluir (this));
        botoneraProd.adherirEscucha(1, new OyenteActualizar (this));
        botoneraProd.adherirEscucha(2, new OyenteEliminar (this));
        */
    }

    @Override
    public void aceptar() {
        Alumno alumno = new Alumno();
        /*
        alumno.incluir(int cedula, String nombre, String apellido,
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
