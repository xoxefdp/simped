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
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author yonalix
 */
public class VistaPRUEBARepresentante extends JFrame{
    Nombres nomb;
    Nacionalidad nac;
    Direccion dir;
    JPanel panel1;
    Botonera boton;
    Telefonos telef;
    FechaNacPf feP;
    String[] AC = {"Aceptar","Cancelar"};
    
    public VistaPRUEBARepresentante(){
        setTitle("Datos Representante");
        setLayout(new BorderLayout());
        setSize(500,700);
        nomb=new Nombres();
        nac=new Nacionalidad();
        dir=new Direccion();
        panel1=new JPanel();
        boton=new Botonera(2,AC);
        telef=new Telefonos();
       
        add(nomb);
        add(boton);
        add(telef);
        //panel1.add(nac);
        panel1.add(dir);
        panel1.add(telef);
        add(BorderLayout.NORTH,nomb);
        add(BorderLayout.CENTER,panel1);
        add(BorderLayout.SOUTH,boton);
        setVisible(true);
    }
    
}
