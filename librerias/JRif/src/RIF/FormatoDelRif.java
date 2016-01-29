package RIF;

import Botoneras.CampoEntero;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.*;
/**
 * FormatoDelRif es un Panel que permite ingresar el RIF en formato X NNNNNNNN V
 * donde X corresponde a J o V para persona juridica o natural,
 * respectivamente&middot; NNNNNNNN representa el numero de RIF asignado y V se
 * refiere a un digito validador&middot; Aplica a Venezuela.
 * @author Hector Alvarez
 */
public class FormatoDelRif extends JPanel{
    
    JComboBox comboLetras;
    String [] letras;
    int []    valores;
    final int cantDigitosDelRif,
              CERO = 0,
              UNO = 1;
    CampoEntero numero;
    CampoEntero digitoVerifica;
    
    /**
     * Crea un objeto de la clase definiendose la cantidad de dígitos que tiene
     * el RIF a manejar.
     * @param cantDigitosRif Define la cantidad de digitos que tiene el numero 
     * RIF.
     */
    public FormatoDelRif(int cantDigitosRif){
        letras = new String[]{"J", "V"}; //Letras prefijas del RIF
        valores = new int[]{3, 1};       //Valor asignado a la letra prefija
        cantDigitosDelRif = cantDigitosRif;
        setLayout(new FlowLayout());
        setBackground(Color.LIGHT_GRAY);
        armaLetras();
        crearGui();
    }
    
    final void crearGui(){
        JPanel panelIn = new JPanel();
        panelIn.setLayout(new BorderLayout());
        numero = new CampoEntero(cantDigitosDelRif);
        digitoVerifica = new CampoEntero(UNO);
        panelIn.add(BorderLayout.WEST,comboLetras);
        panelIn.add(BorderLayout.CENTER,numero);
        panelIn.add(BorderLayout.EAST,digitoVerifica);
        add(panelIn);
    }
    
    final void armaLetras(){
        comboLetras = new JComboBox();
        for (int i = CERO; i < letras.length;i++){
            comboLetras.addItem(letras[i]);
        }
    }
    
    /**
     * Devuelve una cadena que representa el RIF completo ingresado en la
     * interfase de la clase con formato XNNNNNNNV (el tamaño total depende
     * del parametro del constructor (int cantDigitosRif).
     * @return Rif completo XNNNNNNNNV.
     */
    public String obtenerCadenaDelRif(){
        String cadena = numero.obtenerNumero();
        for (int i = cadena.length();i < cantDigitosDelRif;i++){
            cadena = CERO + cadena;
        }
        numero.actualizarNumero(cadena);
        cadena = valores[comboLetras.getSelectedIndex()] + cadena;
        if (digitoVerifica.obtenerNumero().length() == CERO){
            cadena = cadena + CERO;
            digitoVerifica.actualizarNumero("" + CERO);
        }
        else{
            cadena = cadena + digitoVerifica.obtenerNumero();
        }  
        return cadena;
    }
}