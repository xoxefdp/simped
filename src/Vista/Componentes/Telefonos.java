/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista.Componentes;

import Vista.Formatos.CampoTexto;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

/**
 *
 * @author yonalix
 */
public class Telefonos extends JPanel{
    CampoTexto celular,local;
    JPanel paneldirec;
    public Telefonos(){
        paneldirec=new JPanel();
        paneldirec.setBorder(BorderFactory.createTitledBorder("Telefonos"));
        celular=new CampoTexto("Numero Movil*",12);
        local=new CampoTexto("Numero Fijo",12);
        paneldirec.add(celular);
        paneldirec.add(local);
        add(paneldirec);
    }

}
