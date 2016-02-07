/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Controlador.CerrarVentana;
import Vista.Componentes.FondoPrincipal;
import Vista.Componentes.Minutero;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    private final JMenu listado, balance, emitir, sistema;
    private final JMenuItem admisionEstudiante, admisionRepresentante, admisionProfesor, balanceEstudiante, balanceProfesor, constanciaEstudio, reporteInscripciones, cerrarSistema, manual, acercade;
    private Minutero tiempo;
        
    public MenuPrincipal(){
        super("Sistema Informatico para Manejo de Población Estudiantil y Docente");
        setLayout(new BorderLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        barraDeNavegacion = new JMenuBar(); // se crea la barra de menus        
        setJMenuBar(barraDeNavegacion);
        
            listado = new JMenu("Listado"); //crea un elemento del menu
            barraDeNavegacion.add(listado);
            
                admisionEstudiante = new JMenuItem("Estudiantes"); // se crea una opcion
                admisionEstudiante.addActionListener(this);
                listado.add(admisionEstudiante); // se agrega la opcion estudiante al elemento admision de la barra de navegacion
                
                admisionRepresentante = new JMenuItem("Representantes"); // se crea una opcion
                admisionRepresentante.addActionListener(this);
                listado.add(admisionRepresentante); // se agrega la opcion estudiante al elemento admision de la barra de navegacion
                
                admisionProfesor = new JMenuItem("Profesores"); // se crea una opcion
                admisionProfesor.addActionListener(this);
                listado.add(admisionProfesor); // se agrega la opcion docente al elemento admision de la barra de navegacion
            
            balance = new JMenu("Balance"); //crea un elemento del menu
            barraDeNavegacion.add(balance);
            
                balanceEstudiante = new JMenuItem("Estudiantes"); // se crea una opcion
                balanceEstudiante.addActionListener(this);
                balance.add(balanceEstudiante); // se agrega la opcion estudiante al elemento admision de la barra de navegacion
                
                balanceProfesor = new JMenuItem("Profesores"); // se crea una opcion
                balanceProfesor.addActionListener(this);
                balance.add(balanceProfesor); // se agrega la opcion docente al elemento admision de la barra de navegacion
                
            emitir = new JMenu("Emitir"); //crea un elemento del menu
            barraDeNavegacion.add(emitir);
            
                constanciaEstudio = new JMenuItem("Constancia de Estudios"); // se crea una opcion
                constanciaEstudio.addActionListener(this);
                emitir.add(constanciaEstudio); // se agrega la opcion estudiante al elemento admision de la barra de navegacion
                
                reporteInscripciones = new JMenuItem("Balance de Inscripciones"); // se crea una opcion
                reporteInscripciones.addActionListener(this);
                emitir.add(reporteInscripciones); // se agrega la opcion estudiante al elemento admision de la barra de navegacion

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
        add(BorderLayout.SOUTH, tiempo);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
        formWindowOpened(null);
    }

    private void formWindowOpened (java.awt.event.WindowEvent evt){
        FondoPrincipal fondoPrincipal = new FondoPrincipal();
        add(fondoPrincipal, BorderLayout.CENTER);
        fondoPrincipal.repaint();
    }         

    
    /*verificar si puedo generar una estructura que pueda reutilizarse para el evento de apertura de ventanas*/
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (e.getSource() == admisionEstudiante) {
            VistaListaEstudiante vistaListaEstudiante = new VistaListaEstudiante();
        }
        
        if (e.getSource() == admisionRepresentante) {
            VistaListaRepresentante vistaListaRepresentante = new VistaListaRepresentante();
        }
        
        if (e.getSource() == admisionProfesor) {
            VistaListaProfesor vistaListaProfesor = new VistaListaProfesor();
        }
        
        if (e.getSource() == balanceEstudiante) {
            VistaBalanceEstudiante vistaBalanceEstudiante = new VistaBalanceEstudiante();
        }
        if (e.getSource() == balanceProfesor) {
            VistaBalanceProfesor vistaBalanceProfesor = new VistaBalanceProfesor();
        }
        
        if (e.getSource() == constanciaEstudio) {
            //new VistaListaEstudiantes();
            //new VistaAdmisionEstudiante();
        }
        if (e.getSource() == reporteInscripciones) {
            //new VistaListaEstudiantes();
            //new VistaAdmisionProfesor();
        }
        
        if (e.getSource() == manual) {
            //cerrarVentana();
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
        //JOptionPane.showConfirmDialog(null, "Desea salir del sistema de farmacia?", "Salir de sistema farmacia", WIDTH);
        int confirmarSalida = JOptionPane.showConfirmDialog(null, "Desea salir del sistema?", "Saliendo...", YES_NO_OPTION);
        if (confirmarSalida == JOptionPane.OK_OPTION){
            System.out.print("si");
            System.exit(0);
        }else{
            System.out.print("no");
        }
    }
    
    public void acercade(){
        JOptionPane.showMessageDialog(this,"Aplicación Orientada al sector educativo \n Desarrollada por: \n José Francisco Diaz Perez \n Yonalix Garcia \n Meibert Hernandez");
    }

    public static void main(String[] args) {
        MenuPrincipal principal = new MenuPrincipal();
    }

}