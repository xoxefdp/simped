/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Controlador.CerrarVentana;
import Controlador.Configuraciones;
import Vista.Componentes.Fondo;
import Vista.Componentes.Minutero;
import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.YES_NO_OPTION;

/**
 *
 * @author José Diaz
 */
public class MenuPrincipal extends JFrame implements ActionListener, CerrarVentana{
    private final JMenuBar barraDeNavegacion;
    private final JMenu listado, /*balance,*/ emitir, sistema;

    private final JMenuItem admisionEstudiante, /*admisionRepresentante,*/ admisionProfesor, admisionGrado, /*balanceEstudiante, balanceProfesor,*/ constanciaEstudio, constanciaInscripcion, /*reporteRegulares,*/ reporteListaGrado, cerrarSistema, manual, acercade;

    private final Minutero tiempo;
    private final Configuraciones config = new Configuraciones();
    
    //BufferedImage bImage = ImageIO.read(new File(imagePath));
    //BufferedImage bImage;
    
    // URI fuente = new URI("http://java.sun.com");
    // URI licencia = new URI("http://java.sun.com");
        
    public MenuPrincipal() throws IOException{
        super("Sistema Informatico para Manejo de Población Estudiantil y Docente");
        //bImage = ImageIO.read(new File("/home/josediaz/Desktop/proyectos-xoxe/simped/src/Vista/Componentes/principal-fondo.jpg"));
        //Fondo fondo = new Fondo(this,"/home/josediaz/Desktop/proyectos-xoxe/simped/src/Vista/Componentes/principal-fondo.jpg");
        //setContentPane(new JLabel(fondo));
        //setContentPane(new JLabel(new ImageIcon(getClass().getResource("/Vista/Componentes/principal-fondo.jpg"))));
        //Fondo fondo = new Fondo(this,"/home/josediaz/Desktop/proyectos-xoxe/simped/src/Vista/Componentes/principal-fondo.jpg");
        Fondo fondo = new Fondo(this,config.getBackgroundApp());
        fondo.setLayout(new FlowLayout());
        setLayout(new BorderLayout());
        //setLayout(new FlowLayout());
        //add(fondo);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        
        barraDeNavegacion = new JMenuBar(); // se crea la barra de menus        
        setJMenuBar(barraDeNavegacion);
        
            listado = new JMenu("Listado"); //crea un elemento del menu
            barraDeNavegacion.add(listado);
            
                admisionEstudiante = new JMenuItem("Estudiantes"); // se crea una opcion
                admisionEstudiante.addActionListener(this);
                listado.add(admisionEstudiante); // se agrega la opcion estudiante al elemento listado de la barra de navegacion
                /*
                admisionRepresentante = new JMenuItem("Representantes"); // se crea una opcion
                admisionRepresentante.addActionListener(this);
                listado.add(admisionRepresentante); // se agrega la opcion estudiante al elemento listado de la barra de navegacion
                */
                admisionProfesor = new JMenuItem("Profesores"); // se crea una opcion
                admisionProfesor.addActionListener(this);
                listado.add(admisionProfesor); // se agrega la opcion docente al elemento listado de la barra de navegacion
                
                admisionGrado = new JMenuItem("Grados"); // se crea una opcion
                admisionGrado.addActionListener(this);
                listado.add(admisionGrado); // se agrega la opcion grado al elemento listado de la barra de navegacion
            /*
            balance = new JMenu("Balance"); //crea un elemento del menu
            barraDeNavegacion.add(balance);
            
                balanceEstudiante = new JMenuItem("Estudiantes"); // se crea una opcion
                balanceEstudiante.addActionListener(this);
                balance.add(balanceEstudiante); // se agrega la opcion estudiante al elemento admision de la barra de navegacion
                
                balanceProfesor = new JMenuItem("Profesores"); // se crea una opcion
                balanceProfesor.addActionListener(this);
                balance.add(balanceProfesor); // se agrega la opcion docente al elemento admision de la barra de navegacion
            */  
            emitir = new JMenu("Emitir"); //crea un elemento del menu
            barraDeNavegacion.add(emitir);
            
                constanciaInscripcion = new JMenuItem("Constancia de Inscripción"); // se crea una opcion
                constanciaInscripcion.addActionListener(this);
                emitir.add(constanciaInscripcion); // se agrega la opcion estudiante al elemento admision de la barra de navegacion
                
                constanciaEstudio = new JMenuItem("Constancia de Estudios"); // se crea una opcion
                constanciaEstudio.addActionListener(this);
                emitir.add(constanciaEstudio); // se agrega la opcion estudiante al elemento admision de la barra de navegacion
                /*
                reporteRegulares = new JMenuItem("Balance de Alumnos Regulares"); // se crea una opcion
                reporteRegulares.addActionListener(this);
                emitir.add(reporteRegulares); // se agrega la opcion estudiante al elemento admision de la barra de navegacion
                */
                reporteListaGrado = new JMenuItem("Lista de Asistencia Grado"); // se crea una opcion
                reporteListaGrado.addActionListener(this);
                emitir.add(reporteListaGrado); // se agrega la opcion estudiante al elemento admision de la barra de navegacion

            sistema = new JMenu("Sistema"); //crea un elemento del menu
            barraDeNavegacion.add(sistema);

                acercade = new JMenuItem("Acerca De"); // se crea una opcion
                acercade.addActionListener(this);
                sistema.add(acercade); // se agrega la opcion estudiante al elemento admision de la barra de navegacion
                
                manual = new JMenuItem("Manual"); // se crea una opcion
                manual.addActionListener(this);
                sistema.add(manual); // se agrega la opcion estudiante al elemento admision de la barra de navegacion
                
                cerrarSistema = new JMenuItem("Cerrar Sistema"); // se crea una opcion
                cerrarSistema.addActionListener(this);
                sistema.add(cerrarSistema); // se agrega la opcion estudiante al elemento admision de la barra de navegacion
  
        tiempo = new Minutero();
                
        add(BorderLayout.NORTH, barraDeNavegacion);
        add(BorderLayout.CENTER, fondo);
        add(BorderLayout.SOUTH, tiempo);
        //add(barraDeNavegacion);
        //add(tiempo);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setSize(600,500);
        //formWindowOpened(null);
        setVisible(true);
    }
/*
    @Override
    public void paint(Graphics g) {
        //getContentPane().paint(g);
        super.paint(g);
        System.out.println("image");
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                            RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        int w = getWidth();
        int h = getHeight();
        int iw = bImage.getWidth();
        int ih = bImage.getHeight();
        double xScale = (double)w/iw;
        double yScale = (double)h/ih;
        //double scale = Math.min(xScale, yScale);    // scale to fit
        double scale = Math.min(xScale, yScale);  // scale to fill
        int width = (int)(scale*iw);
        int height = (int)(scale*ih);
        int x = (w - width)/2;
        int y = (h - height)/2;
        g2.drawImage(bImage, x, y, width, height, null);
    }
*/
/*
    private void formWindowOpened (java.awt.event.WindowEvent evt){
        Fondo fondo = new Fondo("principal-fondo.jpg");
        add(fondo, BorderLayout.CENTER);
        fondo.repaint();
    }
*/
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (e.getSource() == admisionEstudiante) {
            VistaListaEstudiante vistaListaEstudiante = new VistaListaEstudiante();
        }
        /*
        if (e.getSource() == admisionRepresentante) {
            VistaListaRepresentante vistaListaRepresentante = new VistaListaRepresentante();
        }
        */
        if (e.getSource() == admisionProfesor) {
            VistaListaProfesor vistaListaProfesor = new VistaListaProfesor();
        }
        
        if (e.getSource() == admisionGrado) {
            VistaListaGrado vistaListaGrado = new VistaListaGrado();
        }
        /*
        if (e.getSource() == balanceEstudiante) {
            VistaBalanceEstudiante vistaBalanceEstudiante = new VistaBalanceEstudiante();
        }
        if (e.getSource() == balanceProfesor) {
            VistaBalanceProfesor vistaBalanceProfesor = new VistaBalanceProfesor();
        }
        */
        if (e.getSource() == constanciaEstudio) {
            VistaReporteEstudios vistaConstanciaEstudiante = new VistaReporteEstudios();
        }
        /*
        if (e.getSource() == reporteRegulares) {
            //new VistaListaEstudiantes();
            //new VistaAdmisionProfesor();
        }
        */
        if (e.getSource() == constanciaInscripcion) {
            VistaReporteInscripcion vistaConstanciaEstudiante = new VistaReporteInscripcion();
        }
        if (e.getSource() == reporteListaGrado) {
            VistaReporteListaGrado vistaConstanciaEstudiante = new VistaReporteListaGrado();
        }
        
        
        if (e.getSource() == manual) {
            try {
                File path = new File (this.config.getManual());
                Desktop.getDesktop().open(path);
            }catch (IOException ex) {
                ex.printStackTrace();
            }   
        }
        
        
        if (e.getSource() == acercade) {
            acercade();
        }
        
        if (e.getSource() == cerrarSistema) {
            cerrarVentana();
        }
    }

    @Override
    public void cerrarVentana() {
        int confirmarSalida = JOptionPane.showConfirmDialog(null, "Desea salir del sistema?", "Saliendo...", YES_NO_OPTION);
        if (confirmarSalida == JOptionPane.OK_OPTION){
            System.exit(0);
        }
    }

    public void acercade() {
        /*
        // html content
        JEditorPane ep = new JEditorPane("text/html", "<html><body style=\"" + style + "\">" //
                + "some text, and <a href=\"http://google.com/\">a link</a>" //
                + "</body></html>");

        // handle link events
        ep.addHyperlinkListener(new HyperlinkListener()
        {
            @Override
            public void hyperlinkUpdate(HyperlinkEvent e)
            {
                if (e.getEventType().equals(HyperlinkEvent.EventType.ACTIVATED))
                    ProcessHandler.launchUrl(e.getURL().toString()); // roll your own link launcher or use Desktop if J6+
            }
        });
        ep.setEditable(false);
        ep.setBackground(label.getBackground());
        */
        
        /*
        Uri sourceUri = new Uri("<!DOCTYPE html><html><body><a href=\"https://github.com/xoxefdp/simped\">https://github.com/xoxefdp/simped</a></body><html>");
        JEditorPane source = new JEditorPane("text/html", "<!DOCTYPE html><html><body><a href=\"https://github.com/xoxefdp/simped\">https://github.com/xoxefdp/simped</a></body><html>");
        source.addHyperlinkListener((HyperlinkEvent e) -> {
            if (e.getEventType().equals(HyperlinkEvent.EventType.ACTIVATED)) {
                ProcessHandler.launchUrl(e.getURL().toString());
            }
        });
        source.setEditable(false);
        */
        
        /*
        Uri licenseUri = new Uri("<!DOCTYPE html><html><body><a href=\"https://github.com/xoxefdp/simped/blob/master/LICENSE\">https://github.com/xoxefdp/simped/blob/master/LICENSE</a></body><html>");
        JEditorPane license = new JEditorPane("text/html", "<!DOCTYPE html><html><body><a href=\"https://github.com/xoxefdp/simped/blob/master/LICENSE\">https://github.com/xoxefdp/simped/blob/master/LICENSE</a></body><html>");
        license.addHyperlinkListener((HyperlinkEvent e) -> {
            if (e.getEventType().equals(HyperlinkEvent.EventType.ACTIVATED)) {
                ProcessHandler.launchUrl(e.getURL().toString());
            }
        });
        license.setEditable(false);
        */

        String sourceLink = "https://github.com/xoxefdp/simped";
        String licenseLink = "https://github.com/xoxefdp/simped/blob/master/LICENSE";
        
        JOptionPane.showMessageDialog(this,"Aplicación Orientada al sector educativo \n"
                                        + "Desarrollada por: \n\n"
                                        + " José Francisco Diaz Perez \n"
                                        + " Yonalix Garcia \n"
                                        + " Meibert Hernandez \n\n"
                                        + "Source Code \n"
                                        + " "+sourceLink+"\n\n"
                                        + "License GPL 2015 / "+tiempo.getYear()+" \n"
                                        + " "+licenseLink+"\n\n");
    }

    public static void main(String[] args) throws IOException{
        MenuPrincipal principal = new MenuPrincipal();
    }

}