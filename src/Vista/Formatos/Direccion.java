/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista.Formatos;

import java.awt.TextArea;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

/**
 *
 * @author yonalix
 */
public class Direccion extends JPanel{
    TextArea direcc;
    JPanel paneldirec;
    public Direccion(){
        paneldirec=new JPanel();
        paneldirec.setBorder(BorderFactory.createTitledBorder("direccion"));
        direcc= new TextArea();
        direcc.setColumns(30);
        direcc.setRows(10);
        paneldirec.add(direcc);
        add(paneldirec);
    }
    
    
}
