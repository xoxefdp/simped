/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista.Componentes;

import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.FlowLayout;
import java.awt.Label;
import java.awt.Panel;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

/**
 *
 * @author USUARIO
 */
public class Nacionalidad extends JPanel{
    CampoTexto  nacionalidad;
    Checkbox v,e;
    CheckboxGroup GrupoN;
    JPanel panelnac; 
    public Nacionalidad(){
           
       
       
        crearNacionalidad();
        
        
    } 

    private void crearNacionalidad() {
        setLayout(new FlowLayout());
        nacionalidad =new CampoTexto("",12);
        v=new Checkbox("V");
        e=new Checkbox("E");
        GrupoN =new CheckboxGroup();
        panelnac=new JPanel();
        v.setCheckboxGroup(GrupoN);
        e.setCheckboxGroup(GrupoN);
        panelnac.setBorder(BorderFactory.createTitledBorder("Cedula"));
        panelnac.add(v);
        panelnac.add(e);
        panelnac.add(nacionalidad);
        add(panelnac);
    }
}
