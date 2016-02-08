/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista.Formatos;

import java.awt.FlowLayout;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 * 
 * @author josediaz
 */
public class CampoAreaTexto extends JPanel{
    private JTextArea campo;
    private JPanel panelIn;
       
    /**
     * Crea un panel con título en el borde, con un campo de texto dentro de
     * ancho visible igual al parametro ancho.
     * 
     * @param nombre titulo a colocar en el borde del panel
     * @param ancho ancho visible del campo de texto incluido en el panel
     */
    public CampoAreaTexto(String nombre, int ancho){
        setLayout(new FlowLayout());
        campo = new JTextArea();
        campo.getScrollableTracksViewportHeight();
        campo.setColumns(ancho);
        panelIn  = new JPanel();
        panelIn.setLayout(new FlowLayout());
        panelIn.setBorder(BorderFactory.createTitledBorder(nombre));
        panelIn.add(campo);
        add(panelIn);
    }
    
    /**
     * crea un campo de texto de area sin titulo y con 20 de longitud
     */
    public CampoAreaTexto(){
        this(null, 20);
    }
    
    /**
     * devuelve el contenido del campo de texto de area
     * @return 
     */
    public String obtenerContenido(){
        return campo.getText();
    }
    
    /**
     * modifica el contenido del campo de texto de area
     * @param contenido 
     */
    public void cambiarContenido(String contenido){
        campo.setText(contenido);
    }
    
    /**
     * devuelve la longitud del campo de texto de area
     * @return 
     */
    public int longuitudDelContenido(){
        return obtenerContenido().length();
    }
    
    /**
     * habilita o deshabilita la propiedad de edición del campo de texto de area
     * @param onOff 
     */
    public void hacerEditable(boolean onOff){
        campo.setEditable(onOff);
    }
}