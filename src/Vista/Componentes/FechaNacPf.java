/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista.Componentes;

import java.awt.Label;
import java.awt.TextField;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

/**
 *
 * @author yonalix
 */
public class FechaNacPf extends JPanel{
    Label barra1,barra2;
     TextField dia,mes,año;
    JPanel paneldirec;
    public FechaNacPf(){
        paneldirec=new JPanel();
        paneldirec.setBorder(BorderFactory.createTitledBorder("fecha nacimiento"));
        barra1= new Label("/");
        barra2= new Label("/");
        dia= new TextField(2);
        mes= new TextField(2);
        año= new TextField(4);
        paneldirec.add(dia);
        paneldirec.add(barra1);
        paneldirec.add(mes);
        paneldirec.add(barra2);
        paneldirec.add(año);
        add(paneldirec);
    }
    
    
}
