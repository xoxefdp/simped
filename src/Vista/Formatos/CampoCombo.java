/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista.Formatos;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author josediaz
 */
public class CampoCombo extends JPanel{
    
    private final JLabel etiqueta;
    private final JComboBox campocombo;
    
    /**
     * Crea un panel con título en el borde, con un campo de texto dentro de
     * @param nombre nombre a colocar en el borde del campo
     * @param elementos elemntos que se agregaran como opciones al campo combo
     */
    public CampoCombo(String nombre, String [] elementos){

        etiqueta = new JLabel(nombre);
        campocombo = new JComboBox(elementos);
        add(etiqueta);
        add(campocombo);
    }
    
    /**
     * Devuelve un objeto con el contenido del campo combo seleccionado.
     * @return 
     */
    public Object obtenerSeleccion(){
        return campocombo.getSelectedItem();
    }
    
    /**
     * Ubica el elemento preseleccionado al pasado en parametros
     * @param elemento 
     */
    public void seleccionarElemento(Object elemento){
        campocombo.setSelectedItem(elemento);
    }
}
