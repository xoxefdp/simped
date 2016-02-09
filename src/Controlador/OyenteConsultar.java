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
public class OyenteConsultar implements ActionListener{
    
    ConsultarListar eventoConsultar;
    
    public OyenteConsultar(ConsultarListar accionConsultar){
        eventoConsultar = accionConsultar;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        eventoConsultar.consultar();
    }
}