package Vista;

import Controlador.CerrarVentana;
import Controlador.AceptarCancelar;
import Controlador.OyenteAceptar;
import Controlador.OyenteCancelar;
import Vista.Formatos.Botonera;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextField;
/**
 *
 * @author José Diaz
 */
public class VistaBusqueda extends JDialog implements AceptarCancelar, ActionListener, CerrarVentana{

    private Container contenedor;
    private JPanel panel;
    private JTextField text;
    private Botonera botonera;
    
    public VistaBusqueda(String tituloBusqueda, String campoBusqueda){
        setTitle("Busqueda "+tituloBusqueda);
        setModal(true);        
        
        panel = new JPanel();
        panel.setBorder(BorderFactory.createTitledBorder(campoBusqueda));
            text = new JTextField(20);
            panel.add(text);
        add(panel, BorderLayout.NORTH);
        
        botonera = new Botonera(2);
        add(botonera, BorderLayout.SOUTH);
        botonera.adherirEscucha(0,new OyenteAceptar(this));
        botonera.adherirEscucha(1,new OyenteCancelar(this));
        
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
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void cerrarVentana() {
        dispose();
    }
}