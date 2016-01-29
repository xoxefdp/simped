package Calendario;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.Calendar;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Panel de botones que permite navegar entre meses y años partiendo de la fecha
 * actual.&nbspRequiere que la clase que lo utilice sea del tipo ActionListener
 * ya que tendrá que manejará los eventos de los botones.
 * @author Héctor Alvarez
 * @version 1.0
 */
public class NavegarAnioMes extends JPanel{
    
    protected JButton mesAnterior, mesSiguiente, anioAnterior, anioSiguiente;
    private JTextField mes  = new JTextField(7);
    private JTextField anio = new JTextField(3);
    private JPanel panelBotones = new JPanel();
    private JPanel panelMesAnio = new JPanel();
    private String[] meses = {"Enero", "Febrero", "Marzo", "Abril", "Mayo",
                              "Junio", "Julio", "Agosto", "Septiembre", 
                              "Octubre", "Noviembre", "Diciembre"};
    private int mesActual, anioActual;
    ActionListener quienLlama;
    final int CERO = 0;   
    /**
     * Constructor de la clase.
     * @param llamante Es la clase ActionListener que incluye a esta clase y que
     * será la responsable de manejar los eventos de los botones.
     */
    public NavegarAnioMes(ActionListener llamante){
        quienLlama = llamante;
        setLayout(new BorderLayout());
        crearGui();
    }
    
    private void crearGui(){
        panelBotones.setLayout(new FlowLayout());
        mesAnterior   = new JButton("<");
        mesSiguiente  = new JButton(">");
        anioAnterior  = new JButton("<<");
        anioSiguiente = new JButton(">>");
        mesAnterior.setFont(new Font("",Font.BOLD,7));
        mesSiguiente.setFont(new Font("",Font.BOLD,7));
        anioAnterior.setFont(new Font("",Font.BOLD,7));
        anioSiguiente.setFont(new Font("",Font.BOLD,7));
        mesAnterior.setToolTipText("Mes anterior");
        mesSiguiente.setToolTipText("Mes siguiente");
        anioAnterior.setToolTipText("Año anterior");
        anioSiguiente.setToolTipText("Año siguiente");
        
        mesAnterior.addActionListener(quienLlama);
        mesSiguiente.addActionListener(quienLlama);
        anioAnterior.addActionListener(quienLlama);
        anioSiguiente.addActionListener(quienLlama);

        mesActual  = Calendar.getInstance().get(Calendar.MONTH);
        anioActual = Calendar.getInstance().get(Calendar.YEAR);
        mes.setEditable(false);
        anio.setEditable(false);
        mes.setText(meses[mesActual]);
        anio.setText("" + anioActual);
        mes.setHorizontalAlignment(JTextField.CENTER);
        anio.setHorizontalAlignment(JTextField.CENTER);
        mes.setFont(new Font("",Font.BOLD,7));
        anio.setFont(new Font("",Font.BOLD,7));
        panelMesAnio.setLayout(new FlowLayout());
        panelBotones.add(anioAnterior);
        panelBotones.add(mesAnterior);
        panelBotones.add(mes);
        panelBotones.add(anio);
        panelBotones.add(mesSiguiente);
        panelBotones.add(anioSiguiente);
        //panelMesAnio.add(mes);
        //panelMesAnio.add(anio);
        
        //add(BorderLayout.NORTH,panelMesAnio);
        add(BorderLayout.CENTER,panelBotones);
    }
    /**
     * Incrementa en uno el mes, si el resultado excede 11 (los meses están nu-
     * merados del 0 -Enero- al 11 -Diciembre-) lo coloca en cero e invoca el
     * método que aumenta en uno al año. El método se llama simulando un click
     * en el botón que avanza al año siguiente
     * @param mes Valor del mes a incrementar
     * @return devuelve el valor resultante
     */
    protected int masMes(int mes){
        mes++;
        if (mes > 11){
            mes = 0;
            anioSiguiente.doClick();
        }
             
        return mes;
    }
    /**
     * Decrementa en uno el mes, si el resultado es menor a 0 (los meses están
     * numerados del 0 -Enero- al 11 -Diciembre-) lo coloca en 11 e invoca el
     * método que disminuye en uno al año. El método se llama simulando un click
     * en el botón que retrocede al año anterior
     * @param mes Valor del mes a decrementar
     * @return devuelve el valor resultante
     */
    protected int menosMes(int mes){
        mes--;
        if (mes < CERO){
            mes = 11;
            anioAnterior.doClick();
        }
        return mes;
    }
    /**
     * Incrementa en uno el año y devuelve el resultado
     * @param anio Valor del año a incrementar
     * @return devuelve el valor resultante
     */
    protected int masAnio(int anio){
        anio++;
        return anio;
    }
    /**
     * Decrementa en uno el año y devuelve el resultado
     * @param anio Valor del año a decrementar
     * @return devuelve el valor resultante
     */
    protected int menosAnio(int anio){
        anio--;
        return anio;
    }
    /**
     * Devuelve el valor del mes actual que está mostrado en la interface 
     * @return devuelve el valor solicitado (valor entre 0 y 11)
     */
    protected int obtenerMesActual(){
        return mesActual;
    }
    /**
     * Devuelve el valor del año actual que está mostrado en la interface 
     * @return devuelve el valor solicitado
     */
    protected int obtenerAnioActual(){
        return anioActual;
    }
    /**
     * Actualiza el valor del mes actual y modifica la interfaz para reflejar
     * el cambio.
     * @param mesNuevo valor del nuevo mes actual
     */
    protected void cambiarMesActual(int mesNuevo){
        mesActual = mesNuevo;
        mes.setText(meses[mesActual]);    
    }
    /**
     * Actualiza el valor del año actual y modifica la interfaz para reflejar
     * el cambio.
     * @param anioNuevo valor del nuevo año actual
     */
    protected void cambiarAnioActual(int anioNuevo){
        anioActual = anioNuevo;
        anio.setText("" + anioActual);
    }
}
