/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Vista.Formatos.Direccion;
import Vista.Formatos.Nacionalidad;
import Vista.Formatos.Nombres;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author yonalix
 */
public class pruebaProfesor extends JFrame{
    Nombres nomb;
    Nacionalidad nac;
    Direccion dir;
    JPanel panel1;
    public pruebaProfesor(){
        setTitle("datos profesor");
        setLayout(new BorderLayout());
        setSize(800,800);
        nomb=new Nombres();
        nac=new Nacionalidad();
        dir=new Direccion();
        panel1=new JPanel();
        add(nomb);
        //panel1.add(nac);
        panel1.add(dir);
        add(BorderLayout.NORTH,nomb);
        add(BorderLayout.CENTER,panel1);
        //add(BorderLayout.SOUTH,);
        setVisible(true);
    }
    public static void main(String[] args) {
        new pruebaProfesor();
    }
    
}
