/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */ 
package Vista;

import Controlador.Aceptar;
import Controlador.Cancelar;
import Controlador.CerrarVentana;
import Controlador.OyenteAceptar;
import Controlador.OyenteCancelar;
import Modelo.Grado;
import Vista.Formatos.Botonera;
import Vista.Formatos.CampoTexto;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

/**
 *
 * @author josediaz
 */
public final class VistaAdmisionGrado extends JFrame implements Aceptar, Cancelar, CerrarVentana{
    private final CampoTexto grado,seccion;
    private final JPanel panelTop;
    private final Botonera boton;
    private final String[] AC = {"Aceptar","Cancelar"};

    public VistaAdmisionGrado(){
        setTitle("Creacion de Grados y Secciones");
        setResizable(false);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        /**
         * Elementos del panel superior
         */
        grado = new CampoTexto("Grado",20);
        grado.campo.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent ke) {
                int escrito = ke.getKeyChar();
                if((escrito<'0' || escrito>'9')) ke.consume(); 
                if(grado.longuitudDelContenido() >= 2) ke.consume(); 
            }
        });
                
        seccion = new CampoTexto("Sección",20);
        seccion.campo.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent ke) {
                char escrito = ke.getKeyChar();
                if((escrito<'A' || escrito>'Z')) ke.consume();
                if(seccion.longuitudDelContenido() >= 1) ke.consume();
            }
        });

        panelTop = new JPanel();
        panelTop.setLayout(new GridLayout(1,2));
        panelTop.add(grado);
        panelTop.add(seccion);
        
        /**
         * Elementos inferiores
         */
        boton=new Botonera(AC);
        boton.adherirEscucha(0, new OyenteAceptar(this));
        boton.adherirEscucha(1, new OyenteCancelar(this));

        /**
         * Configuracion de Vista
         */
        add(BorderLayout.NORTH,panelTop);
        add(BorderLayout.SOUTH,boton);
        pack();
        setVisible(true);
    }

    @Override
    public void aceptar() {
        Grado objetoGrado = new Grado();
        
        if (grado.longuitudDelContenido() != 0 && seccion.longuitudDelContenido() != 0) {
            
            String gradoGrado = grado.obtenerContenido();
            String seccionSeccion = seccion.obtenerContenido();
            
            if (objetoGrado.incluir(gradoGrado,seccionSeccion)) {
                cerrarVentana();
            } else {
                JOptionPane.showMessageDialog(this,"Error al insertar");
            }
        } else {
            JOptionPane.showMessageDialog(this,"Existen campos vacios");
        }
    }

    @Override
    public void cancelar() {
        cerrarVentana();
    }

    @Override
    public void cerrarVentana() {
        this.dispose();
    }
}
