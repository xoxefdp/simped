/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista.Componentes;

import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author José Diaz
 */
public final class Fondo extends JPanel{
    private String imagen;
    
    public Fondo(String imagenFondo){
        setImagen(imagenFondo);
    }
    
    /**
     * 
     * @param imagenEntrada 
     */
    public void setImagen(String imagenEntrada){
        imagen = imagenEntrada;
    }
    
    /**
     * 
     * @return 
     */
    public String getImagen(){
        return imagen;
    }
    
    @Override
    public void paint(Graphics g){
        Dimension tamaño = getSize();
        ImageIcon imagenFondo = new ImageIcon (getClass().getResource(getImagen()));
        g.drawImage(imagenFondo.getImage(),0,0, tamaño.width, tamaño.height, null);
        setOpaque(false);
        paintComponent(g);
    }
}