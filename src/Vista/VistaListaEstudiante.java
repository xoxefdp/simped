/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Controlador.Eliminar;
import Controlador.Incluir;
import Controlador.Modificar;
import Controlador.OyenteEliminar;
import Controlador.OyenteIncluir;
import Controlador.OyenteModificar;
import Vista.Componentes.CampoTexto;
import Vista.Formatos.Botonera;
import Vista.Tablas.TablaAlumnos;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JPanel;
/**
 * 
 * @author josediaz
 */
public class VistaListaEstudiante extends JFrame implements Incluir, Modificar, Eliminar{
    TablaAlumnos tablaAlumnos;
    Botonera botonera;
    JButton b1;
    CampoTexto cedula;
    JPanel panelito;
    String[] IME = {"Incluir","Modificar","Eliminar"};
    /**
     * Crea la interface de la clase.
     */
    public VistaListaEstudiante(){
        crearGui();
    }
    
    final void crearGui(){
        setTitle("Lista de Estudiantes");
        setResizable(true);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        tablaAlumnos = new TablaAlumnos();
        b1=new JButton("Buscar");
        botonera = new Botonera(3, IME);
        
        botonera.adherirEscucha(0, new OyenteIncluir(this));
        botonera.adherirEscucha(1, new OyenteModificar(this));
        botonera.adherirEscucha(2, new OyenteEliminar(this));
                
        cedula=new CampoTexto("Codigo Estudiante",12);
        panelito =new JPanel();
        panelito.add(cedula);
        panelito.add(b1);
        add(BorderLayout.NORTH,panelito);
        add(BorderLayout.CENTER, tablaAlumnos);
        add(BorderLayout.SOUTH,  botonera);
        
        setSize(400,400);
        pack();
        setVisible(true);
    }
    
    /*
    // Objeto  que contiene los metodos de consulta para la tabla de representantes
    Representante representante = new Representante();
    Object resultados = representante.consultarRepresentantes();
    while(resultados.next()){
        String cadena = resultados.getString(1)+" "+  //R.getString(cedula) + " " +
                        resultados.getString(2)+" "+  //R.getString(nombre) + " " +
                        resultados.getString(3); //R.getString(genero) + " ";
        System.out.print(cadena);
    }
    */

    @Override
    public void incluir() {
        VistaAdmisionEstudiante vistaAdmisionEstudiante = new VistaAdmisionEstudiante();
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modificar() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eliminar() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}