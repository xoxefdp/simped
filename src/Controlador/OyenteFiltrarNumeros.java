/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 *
 * @author josediaz
 */
public class OyenteFiltrarNumeros extends KeyAdapter{

    FiltrarNumeros eventoFiltrarNumeros;

    public OyenteFiltrarNumeros(FiltrarNumeros accionFiltrarNumeros){
        eventoFiltrarNumeros = accionFiltrarNumeros;
    }
    
    @Override
    public void keyTyped(KeyEvent eventoFiltrarNumeros) {
        
        int escrito = eventoFiltrarNumeros.getKeyChar();

        if((escrito<'0' || escrito>'9')){
            eventoFiltrarNumeros.consume(); 
        }
    }
}