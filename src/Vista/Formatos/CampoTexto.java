/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista.Formatos;

import java.awt.FlowLayout;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * 
 * @author yonalix
 */
public class CampoTexto extends JPanel{
    public JTextField campo;
    private JPanel panelIn = new JPanel();
       
    /**
     * Crea un panel con título en el borde, con un campo de texto dentro de
     * ancho visible igual al parametro ancho.
     * 
     * @param titulo titulo a colocar en el borde del panel
     * @param ancho ancho visible del campo de texto incluido en el panel
     */
    public CampoTexto(String titulo, int ancho){
        setLayout(new FlowLayout());
        campo = new JTextField(ancho);
        panelIn.setLayout(new FlowLayout());
        panelIn.setBorder(BorderFactory.createTitledBorder(titulo));
        panelIn.add(campo);
        add(panelIn);
    }
    
    /**
     * Crea un panel sin título en el borde, con un campo de texto dentro de
     * ancho visible con valor por defecto de 20.
     */
    public CampoTexto(){
        this(null, 20);
    }
    
    /**
     * Devuelve un valor String con el contenido del campo de texto.
     * @return contenido del campo de texto.
     */
    public String obtenerContenido(){
        return campo.getText();
    }
    
    /**
     * Actualiza el contenido del campo de texto.
     * @param contenido con el que se actualizara el campo de texto.
     */
    public void cambiarContenido(String contenido){
        campo.setText(contenido);
    }
    
    /**
     * Modifica la tipografia del contenido del campo de texto
     * @param tipografia
     * @param estilo
     * @param tamaño
     */
    public void cambiarTipografia(String tipografia, int estilo, int tamaño){
        campo.setFont(new java.awt.Font(tipografia, estilo, tamaño));
    }
    
    /**
     * Devuelve un valor int con la longuitud del contenido del campo de texto.
     * @return longuitud del campo de texto.
     */
    public int longuitudDelContenido(){
        return obtenerContenido().length();
    }
    
    /**
     * Habilita la edición del contenido del campo de texto
     * @param onOff true el campo es editable, false es no editable.
     */
    public void hacerEditable(boolean onOff){
        campo.setEditable(onOff);
    }
    
    /**
     * Ajusta el alineamiento del texto contenido en el campo de texto
     * @param alineamiento
     */
    public void alinearTexto(String alineamiento){
        switch(alineamiento) {
            case "TOP": campo.setHorizontalAlignment((int) TOP_ALIGNMENT); break;
            case "BOTTOM": campo.setHorizontalAlignment((int) BOTTOM_ALIGNMENT); break;
            case "CENTER": campo.setHorizontalAlignment((int) CENTER_ALIGNMENT); break;
            case "RIGHT": campo.setHorizontalAlignment((int) RIGHT_ALIGNMENT); break;
            case "LEFT": campo.setHorizontalAlignment((int) LEFT_ALIGNMENT); break;
            default: /**/; break;
        }
    }
}