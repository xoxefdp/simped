/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */ 
package Vista;

import Calendario.Calendario;
import Controlador.Aceptar;
import Controlador.Cancelar;
import Controlador.CerrarVentana;
import Controlador.OyenteAceptar;
import Controlador.OyenteCancelar;
import Modelo.GradoProfesor;
import Modelo.Profesor;
import Vista.Formatos.Botonera;
import Vista.Formatos.CampoTexto;
import Vista.Tablas.TablaProfesores;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

/**
 *
 * @author josediaz
 */
public class VistaAdmisionGradoProfesor extends JFrame implements Aceptar, Cancelar, CerrarVentana{
    private final CampoTexto fechanac;
    private final JPanel panelTop;
    private final Botonera boton;
    private final String[] AC = {"Aceptar","Cancelar"};
    private final Profesor profesor;
    private GradoProfesor gradoProfesor;
    private final ResultSet resultadoProf;
    private final TablaProfesores tablaProfesores;
    private final int codigoGradoLocal;

    public VistaAdmisionGradoProfesor(String codigoGrado){
        setTitle("Asignar Profesor a Grado");
        setResizable(false);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        codigoGradoLocal = Integer.parseInt(codigoGrado);
        
        /**
         * Elementos del panel superior
         */
        fechanac = new CampoTexto("Año",20);
        fechanac.campo.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent ke) {
                int escrito = ke.getKeyChar();
                if((escrito<'0' || escrito>'9') && (escrito!=KeyEvent.VK_MINUS)) ke.consume(); 
                if(fechanac.longuitudDelContenido() >= 10) ke.consume(); 
            }
        });
        /**
         * Invoca calendario al enfocar
         */
        fechanac.campo.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (fechanac.longuitudDelContenido() == 0) {
                    Calendario calendario = new Calendario(fechanac.campo);
                }
            }
        });
        
        /**
         * Llenado de campos
         */
        profesor = new Profesor();
        resultadoProf = profesor.consultarProfesores();
        tablaProfesores = new TablaProfesores();
        tablaProfesores.cargarTabla(resultadoProf);
        
        panelTop = new JPanel();
        panelTop.setLayout(new FlowLayout());
        panelTop.add(fechanac);
        panelTop.add(tablaProfesores);
        
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
        if (fechanac.longuitudDelContenido() != 0 && tablaProfesores.tabla.getSelectedRow()>=0) {
            
            gradoProfesor = new GradoProfesor();
            
            int row = tablaProfesores.tabla.getSelectedRow();
            
            String strCedulaProf = (String)tablaProfesores.tabla.getValueAt(row, 0);
            int cedulaProf = Integer.parseInt(strCedulaProf);
            
            String fechaNacProf = fechanac.obtenerContenido();
            
            if (gradoProfesor.incluir(codigoGradoLocal,cedulaProf,fechaNacProf)){
                cerrarVentana();
            } else {
                JOptionPane.showMessageDialog(this,"Error al modificar");
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
