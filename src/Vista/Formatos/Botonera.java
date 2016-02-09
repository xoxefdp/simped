/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista.Formatos;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author José Diaz
*/
public class Botonera extends JPanel{
    private final JButton[] botones;
    private final JPanel cuadroBotonera;
    private String[] nombresBotones;
    
    /**
     * Genera una botonera con las caracteristicas de los parametros de entrada
     * @param numeroBotones cantidad de botones a generar
     * @param nombresBotones  nombre identificador de los botones
     */
    public Botonera(int numeroBotones, String[] nombresBotones){
    
        cuadroBotonera = new JPanel();
        cuadroBotonera.setLayout(new FlowLayout());
        
        botones = new JButton[numeroBotones];
        for (int i = 0; i < numeroBotones ; i++) {
            botones[i] = new JButton(nombresBotones[i]);
            cuadroBotonera.add(botones[i]);
        }
        add(cuadroBotonera);
    }
    
    /**
     * Adiciona escuchas de eventos a los botones
     * @param posBoton posicion del boton en la botonera generada
     * @param escucha  oyente adherido al boton
     */
    public void adherirEscucha(int posBoton, ActionListener escucha){
        botones[posBoton].addActionListener(escucha);
    }
}