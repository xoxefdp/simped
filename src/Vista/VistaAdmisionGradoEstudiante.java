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
import Modelo.Alumno;
import Modelo.AlumnoGrado;
import Vista.Formatos.Botonera;
import Vista.Formatos.CampoTexto;
import Vista.Tablas.TablaAlumnos;
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
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author josediaz
 */
public class VistaAdmisionGradoEstudiante extends JFrame implements Aceptar, Cancelar, CerrarVentana{
    private final CampoTexto fechanac;
    private final JPanel panelTop;
    private final Botonera boton;
    private final String[] AC = {"Aceptar","Cancelar"};
    private final Alumno alumno;
    private AlumnoGrado alumnoGrado;
    private final ResultSet resultadoAlum;
    private final TablaAlumnos tablaAlumnos;
    private final int codigoGradoLocal;
    String fechaReducida=null;

    public VistaAdmisionGradoEstudiante(String codigoGrado){
        setTitle("Asignar Estudiante a Grado");
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
                if(fechanac.longuitudDelContenido() >= 4) ke.consume(); 
            }
        });
        /**
         * Invoca calendario al enfocar
         */
        fechanac.campo.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (fechanac.longuitudDelContenido() == 0) {
                    fechanac.campo.getDocument().addDocumentListener(new DocumentListener() {
                        @Override
                        public void insertUpdate(DocumentEvent de) {
                            //System.out.println(fechanac.obtenerContenido());
                            fechaReducida = (fechanac.obtenerContenido()).substring(0,4)+"-01-01";
                            //System.out.println(fechaReducida);
                        }

                        @Override
                        public void removeUpdate(DocumentEvent de) {
                            //System.out.println(de.getChange((Element)fechanac.campo));
                            //System.out.println("Salida1");
                        }

                        @Override
                        public void changedUpdate(DocumentEvent de) {
                            //System.out.println(de.getChange((Element)fechanac.campo));
                            //System.out.println("Salida2");
                        }
                    });
                    Calendario calendario = new Calendario(fechanac.campo);
                    fechanac.cambiarContenido(fechaReducida);
                    //System.out.println(fechanac.obtenerContenido());
                }
            }
        });
        
        /**
         * Llenado de campos
         */
        alumno = new Alumno();
        resultadoAlum = alumno.consultarAlumnosRepresentantes();
        tablaAlumnos = new TablaAlumnos(825,250);
        tablaAlumnos.cargarTabla(resultadoAlum);
        
        panelTop = new JPanel();
        panelTop.setLayout(new FlowLayout());
        panelTop.add(fechanac);
        panelTop.add(tablaAlumnos);
        
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
        if (fechanac.longuitudDelContenido() != 0 && tablaAlumnos.tabla.getSelectedRow()>=0) {
            
            alumnoGrado = new AlumnoGrado();
            
            int row = tablaAlumnos.tabla.getSelectedRow();
            
            String strCodigoAlumno = (String)tablaAlumnos.tabla.getValueAt(row, 0);
            int codigoAlumno = Integer.parseInt(strCodigoAlumno);
            
            String fechaNacProf = fechanac.obtenerContenido();
            
            if (alumnoGrado.incluir(codigoGradoLocal,codigoAlumno,fechaNacProf)){
                cerrarVentana();
            } else {
                JOptionPane.showMessageDialog(this,"Error al insertar, registro existe");
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
