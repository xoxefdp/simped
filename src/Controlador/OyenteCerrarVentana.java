/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 *
 * @author José Diaz
 */
public class OyenteCerrarVentana extends WindowAdapter{
    
    CerrarVentana ventanaCerrar;
    
    public OyenteCerrarVentana(CerrarVentana objetoEventoCerrar){
        ventanaCerrar = objetoEventoCerrar;
    }

    @Override
    public void windowClosing(WindowEvent e) {
        ventanaCerrar.cerrarVentana();
    }
}
