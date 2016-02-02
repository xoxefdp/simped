/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Controlador.Aceptar;
import Controlador.CerrarVentana;
import Controlador.Cancelar;
import Controlador.OyenteAceptar;
import Controlador.OyenteCancelar;
import Vista.Formatos.Botonera;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextField;
/**
 *
 * @author José Diaz
 */
public class VistaBusqueda extends JDialog implements Aceptar, Cancelar, CerrarVentana{

    private Container contenedor;
    private final JPanel panel;
    private final JTextField text;
    private final Botonera botonera;
    private final String[] AC = {"Aceptar","Cancelar"};
    
    public VistaBusqueda(String tituloBusqueda, String campoBusqueda){
        setTitle("Busqueda "+tituloBusqueda);
        setModal(true);        
                
        text = new JTextField(20);
        panel = new JPanel();
        panel.setBorder(BorderFactory.createTitledBorder(campoBusqueda));
        panel.add(text);
        
        
        botonera = new Botonera(2,AC);        
        botonera.adherirEscucha(0,new OyenteAceptar(this));
        botonera.adherirEscucha(1,new OyenteCancelar(this));
        
        add(botonera, BorderLayout.SOUTH);
        add(panel, BorderLayout.NORTH);
        
        pack();
        setVisible(true);
    }

    @Override
    public void aceptar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void cancelar() {
        cerrarVentana();
    }

    @Override
    public void cerrarVentana() {
        dispose();
    }
}