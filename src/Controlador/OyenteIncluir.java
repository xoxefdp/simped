/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 *
 * @author José Diaz
 */
public class OyenteIncluir implements ActionListener{
    
    Incluir eventoIncluir;
    
    public OyenteIncluir(Incluir accionIncluir){
        eventoIncluir = accionIncluir;
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        eventoIncluir.incluir();
    }    
}