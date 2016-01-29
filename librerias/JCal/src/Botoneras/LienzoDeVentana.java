package Botoneras;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import javax.swing.JPanel;

public class LienzoDeVentana extends JPanel{
    
    Image imagen;
    String nombreImagen;
    
    public LienzoDeVentana(String nombreDeImagen){
        
        nombreImagen = nombreDeImagen;
        if (loadImages());
    }
    
    @Override
    public void paint(Graphics g){
        g.drawImage(imagen,0,0,getWidth(), getHeight(),this);
        setOpaque(false);
        super.paint(g);
    }
    
    private boolean loadImages(){

	MediaTracker tracker = new MediaTracker(this);
        imagen = Toolkit.getDefaultToolkit().getImage(nombreImagen);

        tracker.addImage(imagen, 1);
        try{
            tracker.waitForID(1);
        }
        catch (InterruptedException e){
        }
        if (tracker.isErrorID(1)){
            return false;
        }
        return true;
    }
}