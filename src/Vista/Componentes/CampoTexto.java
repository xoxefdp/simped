/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista.Componentes;

import java.awt.FlowLayout;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Panel con borde que contiene un campo de texto y que puede incluir o no un
 * titulo.
 * @author Hector Alvarez
 */
public class CampoTexto extends JPanel{
    private JTextField campo;
    private JPanel panelIn = new JPanel();
       
    /**
     * Crea un panel con título en el borde, con un campo de texto dentro de
     * ancho visible igual al parametro ancho.
     * 
     * @param nombre titulo a colocar en el borde del panel
     * @param ancho ancho visible del campo de texto incluido en el panel
     */
    public CampoTexto(String nombre, int ancho){
        setLayout(new FlowLayout());
        campo = new JTextField(ancho);
        panelIn.setLayout(new FlowLayout());
        panelIn.setBorder(BorderFactory.createTitledBorder(nombre));
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
     * Devuelve un valor int con la longuitud del contenido del campo de texto.
     * @return longuitud del campo de texto.
     */
    public int longuitudDelContenido(){
        return obtenerContenido().length();
    }
    
    /**
     * Devuelve un valor int con la longuitud del contenido del campo de texto.
     * @param onOff true el campo es editable, false es no editable.
     */
    public void hacerEditable(boolean onOff){
        campo.setEditable(onOff);
    }
}