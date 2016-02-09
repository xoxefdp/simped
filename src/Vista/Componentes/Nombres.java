/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista.Componentes;

import Vista.Formatos.CampoTexto;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

/**
 *
 * @author yonalix
 */
public class Nombres extends JPanel{
    CampoTexto nombre,nombre2,apellido,apellido2;
    JPanel panelnomb;
    GridLayout ordenar;
    Nacionalidad ced;
    public Nombres(){
        
        ced=new Nacionalidad();
        nombre=new CampoTexto("Primer Nombre",20);
        nombre2=new CampoTexto("Segundo Nombre",20);
        apellido=new CampoTexto("Primer Apellido",20);
        apellido2=new CampoTexto("Segundo Apellido",20);
        panelnomb =new JPanel();
        panelnomb.setBorder(BorderFactory.createTitledBorder("Nombres y apellidos"));
        //panelnomb.add(nombre);
        //panelnomb.add(nombre2);
        //panelnomb.add(apellido);
        //panelnomb.add(apellido2);
        //panelnomb.add();
        //add(panelnomb);
        add(nombre);
        add(nombre2);
        add(apellido);
        add(apellido2);
        add(ced);
        ordenar=new GridLayout();
        ordenar.setColumns(1);
        ordenar.setRows(3);
        setLayout(new GridLayout());
        setLayout (ordenar);
        
    }
    
}
