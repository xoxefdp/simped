/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista.Componentes;

import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.ImageIcon;

/**
 *
 * @author José Diaz
 */
public class FondoPrincipal extends javax.swing.JPanel{
    
    public FondoPrincipal(){
        
    }
     
    @Override
    public void paint(Graphics g){
        Dimension Tamaño = getSize();
        ImageIcon ImagenFondo = new ImageIcon (getClass().getResource("principal-fondo.jpg"));
        g.drawImage(ImagenFondo.getImage(),0,0, Tamaño.width, Tamaño.height, null);
        setOpaque(false);
        paintComponent(g);
    }    
}