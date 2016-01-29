package Calendario;

import Botoneras.LienzoDeVentana;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Calendar;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Presenta un panel de botones, cada uno representando el día del mes
 * seleccionado.
 * @author Héctor Alvarez
 * @version 1.0
 */
public class DiasDelMes extends JPanel{
    
    final int UNO = 1, CERO = 0;
    private Calendar cal = Calendar.getInstance();
    private JTextField[] nombreDia={new JTextField("D"),new JTextField("L"),
                                    new JTextField("M"),new JTextField("M"),
                                    new JTextField("J"),new JTextField("V"),
                                    new JTextField("S")};
    /**
     * botonDía es un arreglo de hasta 42 botones que representan los días de
     * un calendario, los cuales son colocados en una parrilla de 6 filas por 7
     * columnas (Máximo de seis semanas y siete días).
     */
    protected JButton [] botonDia = new JButton[42];
    private ActionListener quienLlama;
    Image imagen;
    
    /**
     * Constructor de la clase.&nbspRequiere que la clase que lo utilice sea del
     * tipo ActionListener ya que tendrá que manejará los eventos de los
     * botones.
     * @param llamante Es la clase ActionListener que incluye a esta clase y que
     * será la responsable de manejar los eventos de los botones.
     */
    public DiasDelMes(ActionListener llamante){
        quienLlama = llamante;
        int mes, anio;
        mes  = cal.get(Calendar.MONTH);
        anio = cal.get(Calendar.YEAR);
        setLayout(new GridLayout(CERO,7));
        setOpaque(false);
        if (loadImages("Torre.jeg")){
            Graphics g = getGraphics();
            paint(g);
        }
        cargarNombreDeDias();

        int cantidadDeDias;
        int diaQueComienzaElMes = CERO;
        
        if (validarMes(mes)){
            cantidadDeDias = obtenerUltimoDiaDelMes(mes, anio);
            diaQueComienzaElMes = obtenerDiaQueComienzaElMes(mes, anio);
        }  
        else{
            cantidadDeDias = obtenerUltimoDiaDelMes(CERO, 1900);
            JOptionPane.showMessageDialog(null, "!Fecha errada, verifique!");
        }
        
        cargarBotones();
        ubicarBotones(diaQueComienzaElMes, cantidadDeDias);
        resaltarElDia(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
    }
    
    private void cargarBotones(){
        for (int i = CERO; i < botonDia.length; i++){
            botonDia[i] = new JButton();
            botonDia[i].setBorderPainted(false);
            botonDia[i].setOpaque(false);
            //botonDia[i].setBackground(null);
            botonDia[i].setForeground(Color.BLACK);
            botonDia[i].setEnabled(false);
            botonDia[i].setFont(new Font("",Font.BOLD,7));
            botonDia[i].addActionListener(quienLlama);
            botonDia[i].setActionCommand("botonDia");
            add(botonDia[i]);
        }
    }
    
    /**
     * Numera los cuarenta y dos botones en la parrilla, comenzando a numerar
     * los botones a partir del día (columna) en que comienza e mes a ubicar,
     * hasta la cantidad de días que tenga el mes.
     * @param posicionDelPrimerBoton Cuál día de la semana (respecto a 0)
     * comienza el mes. (Entre 0 y 6)
     * @param cantidadDeDias Cantidad de días que tiene el mes (<= 31)
     * @return Si los parámetros de entrada son correctos devuelve true, false
     * en caso contrario.
     */
    protected final boolean ubicarBotones(int posicionDelPrimerBoton,
                                       int cantidadDeDias){
        if (posicionDelPrimerBoton < 7 && cantidadDeDias <= 31){
            
            int hasta = cantidadDeDias + posicionDelPrimerBoton;
            
            for (int i = CERO; i < hasta; i++){
                if (i >= posicionDelPrimerBoton){
                    botonDia[i].setText("" + (i - posicionDelPrimerBoton + 1));
                    //botonDia[i].setBackground(null);
                    botonDia[i].setFont(new Font("", Font.BOLD, 8));
                    botonDia[i].setForeground(Color.BLACK);
                    botonDia[i].setOpaque(false);
                    botonDia[i].setEnabled(true);
                }   
            }
            return true;
        }
        else
            return false;
    }
    /**
     * Reestablece los valores iniciales de los botones: Sin borde, sin texto,
     * con el color por defecto, deshabilitados.
     */
    protected void restaurarBotones(){
        for (int i = CERO; i < botonDia.length; i++){
            botonDia[i].setText("");
            botonDia[i].setBorderPainted(false);
            botonDia[i].setOpaque(false);
            //botonDia[i].setBackground(null);
            botonDia[i].setForeground(Color.BLACK);
            botonDia[i].setEnabled(false);
            botonDia[i].setFont(new Font("",Font.BOLD,8));
        }
    }
    
    private void cargarNombreDeDias(){
        
        for (int i = CERO; i < nombreDia.length; i++){
            nombreDia[i].setFont(new Font("", Font.BOLD, 7));
            nombreDia[i].setHorizontalAlignment(JTextField.CENTER);
            add(nombreDia[i]);
        }
    }
    
    /**
     * Resalta el botón escogido colocandole el borde visible.
     * @param dia Dia escogido del mes a ser resaltando pintando su borde.
     */
    public final void resaltarElDia(int dia){

        for (int i = CERO; i < botonDia.length; i++){
            
            try{
                if (Integer.parseInt(botonDia[i].getText()) == dia)
                   botonDia[i].setBorderPainted(true);
            }
            catch(Exception e){
            }
        }
    }
    
    /**
     * Cambia el color del botón del día escogido.
     * @param dia Día del mes al que se cambiará el color.
     * @param color Color (class @see java.awt.Color) que se colocará al botón.
     */
    public void cambiarColorAlDia(int dia, Color color){
        
        for (int i = CERO; i < botonDia.length; i++){
            
            try{
                if (Integer.parseInt(botonDia[i].getText()) == dia){
                    botonDia[i].setFont(new Font("", Font.BOLD, 8));
                    botonDia[i].setBackground(color);
                }    
            }
            catch(Exception e){
            }
        }
    }
    
    /**
     * Restaura el aspecto inicial por defecto del botón del día indicado.
     * @param dia Dia al que se quiere restaurar los valores iniciales por
     * defecto.
     */
    public void restaurarColorAlDia(int dia){
        
        for (int i = CERO; i < botonDia.length; i++){
            
            try{
                if (Integer.parseInt(botonDia[i].getText()) == dia){
                    resaltarElDia(dia);
                    botonDia[i].setFont(new Font("", CERO, 8));
                    botonDia[i].setBorderPainted(false);
                    //botonDia[i].setBackground(null);
                    botonDia[i].setForeground(Color.BLACK);
                }
            }
            catch(Exception e){
            }
        }
    }
    
    /**
     * Para un mes y año dado, devuelve el valor del útimo día.
     * @param mes Mes del cual se quiere saber la cantidad de días
     * @param anio Año asociado al mes cuyo último día se desea conocer
     * @return Entero que representa la cantidad de días del mes.
     */
    protected final int obtenerUltimoDiaDelMes(int mes, int anio) {
        cal.set(Calendar.YEAR, anio);
        cal.set(Calendar.MONTH, mes);
        return cal.getActualMaximum(Calendar.DAY_OF_MONTH);
    }
    /**
     * Para un mes y año dado, devuelve un valor entre 0 y 6 que representa el
     * día de la semana que comienza el mes (0: Domingo, 6: Sábado)
     * @param mes Mes del cual se quiere saber el dia de semana donde comienza
     * @param anio Año asociado al mes
     * @return Entero que representa el día de la semana [0..6].
     */
    protected final int obtenerDiaQueComienzaElMes(int mes, int anio) {
        cal.set(Calendar.YEAR, anio);
        cal.set(Calendar.MONTH, mes);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        return cal.get(Calendar.DAY_OF_WEEK) - 1;
    }
    
    private boolean validarMes(int mes){
        if (mes >= 0 && mes < 12)return true;
        else                      return false;
    }
                
    @Override
    public void paint(Graphics g){
        g.drawImage(imagen,0,0,getWidth(), getHeight(),this);
        super.paint(g);
    }
    
    private boolean loadImages(String nombreImagen){

	MediaTracker tracker = new MediaTracker(this);
        imagen = Toolkit.getDefaultToolkit().getImage(nombreImagen);

        tracker.addImage(imagen, 1);
        try{
            tracker.waitForID(1);
        }
        catch (InterruptedException e){
        }
        if (tracker.isErrorID(1)){
            return false;
        }
        return true;
    }
}