/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista.Componentes;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author José Diaz
 */

public final class Fondo extends JPanel{
    private BufferedImage image;
    private JFrame windowFrame;

    public Fondo(JFrame window,String imagePath) throws IOException {
        // This is just an example, I'd prefer to use setters/getters
        // and would also need to provide alignment options ;)
        BufferedImage bImage = ImageIO.read(new File(imagePath));
        image = bImage;
        windowFrame = window;
    }

    @Override
    public Dimension getPreferredSize() {
        return image == null ? new Dimension(0, 0) : new Dimension(image.getWidth(this), image.getHeight(this));            
    }
    
    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        if (image != null) {
            int w = windowFrame.getWidth();
            int h = windowFrame.getHeight();
            int iw = image.getWidth();
            int ih = image.getHeight();
            double xScale = (double)w/iw;
            double yScale = (double)h/ih;
            //double scale = Math.min(xScale, yScale);    // scale to fit
            double scale = Math.max(xScale, yScale);  // scale to fill
            int width = (int)(scale*iw);
            int height = (int)(scale*ih);
            int x = (w - width)/2;
            int y = (h - height)/2;
            g.drawImage(image, x, y, width, height, null);
        }
    }
}

/*
public final class Fondo extends JPanel{
    private String imagen;
    
    public Fondo(String imagenFondo){
        setImagen(imagenFondo);
    }

    public void setImagen(String imagenEntrada){
        imagen = imagenEntrada;
    }

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
*/
/*
public final class Fondo extends ImageIcon{
    
    private final BufferedImage image;
    private final JFrame window;
 
    public Fondo(JFrame windowFrame,String imagePath) throws IOException {
        BufferedImage bImage = ImageIO.read(new File(imagePath));
        
        image = bImage;
        window = windowFrame;
        System.out.println(image);
        Graphics2D g2 =(Graphics2D)image.getGraphics();
        System.out.println(g2);
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                            RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        int w = window.getWidth();
        int h = window.getHeight();
        int iw = image.getWidth();
        int ih = image.getHeight();
        double xScale = (double)w/iw;
        double yScale = (double)h/ih;
        //double scale = Math.min(xScale, yScale);    // scale to fit
        double scale = Math.min(xScale, yScale);  // scale to fill
        int width = (int)(scale*iw);
        int height = (int)(scale*ih);
        int x = (w - width)/2;
        int y = (h - height)/2;
        g2.drawImage(image, x, y, width, height, null);
    }
    
    protected void paintComponents(Graphics g) {
        window.paintComponents(g);
        System.out.println("image");
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                            RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        int w = window.getWidth();
        int h = window.getHeight();
        int iw = image.getWidth();
        int ih = image.getHeight();
        double xScale = (double)w/iw;
        double yScale = (double)h/ih;
        //double scale = Math.min(xScale, yScale);    // scale to fit
        double scale = Math.min(xScale, yScale);  // scale to fill
        int width = (int)(scale*iw);
        int height = (int)(scale*ih);
        int x = (w - width)/2;
        int y = (h - height)/2;
        g2.drawImage(image, x, y, width, height, null);
    }
}
*/