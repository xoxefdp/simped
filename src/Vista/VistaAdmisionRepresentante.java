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
import Vista.Componentes.CampoAreaTexto;
import Vista.Componentes.CampoTexto;
import Vista.Formatos.Botonera;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author yonalix
 */
public class VistaAdmisionRepresentante extends JFrame implements Aceptar, Cancelar, CerrarVentana{
    CampoTexto cedula,nombres,apellidos,telefono,fechanac,correo;
    CampoAreaTexto direccion;
    JPanel panel1,panel2,panel3;
    Botonera boton;
    String[] AC = {"Aceptar","Cancelar"};
    
    public VistaAdmisionRepresentante(){
        setTitle("Datos Representante");
        setLayout(new BorderLayout());
        setSize(500,700);
        cedula=new CampoTexto("Cedula",15);
        nombres=new CampoTexto("Nombres",15);
        apellidos=new CampoTexto("Apellidos",15);
        panel1 = new JPanel();
        panel1.setLayout(new FlowLayout());
        panel1.add(cedula);
        panel1.add(nombres);
        panel1.add(apellidos);
        
        telefono=new CampoTexto("Telefono",15);
        fechanac=new CampoTexto("Fecha de nacimiento",15);
        correo=new CampoTexto("Correo Electronico",15);
        panel2 = new JPanel();
        panel2.setLayout(new FlowLayout());
        panel2.add(telefono);
        panel2.add(fechanac);
        panel2.add(correo);
        
        direccion=new CampoAreaTexto("Dirección",15);
        boton=new Botonera(2,AC);
        boton.adherirEscucha(0, new OyenteAceptar(this));
        boton.adherirEscucha(1, new OyenteCancelar(this));
        panel3 = new JPanel();
        panel3.setLayout(new FlowLayout());
        panel3.add(direccion);
        panel3.add(boton);
               
        add(BorderLayout.NORTH,panel1);
        add(BorderLayout.CENTER,panel2);
        add(BorderLayout.SOUTH,panel3);
        setVisible(true);
    }

    @Override
    public void aceptar() {
        Representante representante = new Representante();
        /*
        representante.incluir(int cedula, String nombre, String apellido,
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
