/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

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
public class VistaAdmisionEstudiante extends JFrame{
    Nombres nomb;
    Nacionalidad nac;
    Direccion dir;
    JPanel panel1;
    Botonera boton;
    JButton b1;
    Telefonos telef;
    FechaNacPf feP;
    
    public VistaAdmisionEstudiante(){
        setTitle("datos alumnos");
        setLayout(new BorderLayout());
        setSize(600,400);
        nomb=new Nombres();
        nac=new Nacionalidad();
        boton=new Botonera(3);
        panel1=new JPanel();
        b1=new JButton("representante");
        panel1.add(b1);
        add(nomb);
        add(boton);
        add(BorderLayout.NORTH,nomb);
        add(BorderLayout.CENTER,panel1);
        add(BorderLayout.SOUTH,boton);
        setVisible(true);
    }
  
}
