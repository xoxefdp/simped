package Botoneras;

import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

public class BotoneraCool extends JPanel implements BotoneraInterface{
    public JButton [] boton ;
    int cantElementos;
    
    public BotoneraCool (String[] nombreBotones){
        
        cantElementos = nombreBotones.length;
        boton = new JButton[cantElementos];
        
        for (int i = CERO; i < cantElementos;i++){
             boton[i]= new JButton();
             boton[i].setText(nombreBotones[i]);
             add(boton[i]);
        }
    }
    
    public void activarBoton(int subIndice){
        if (subIndice<cantElementos)
            boton[subIndice].setEnabled(true);
    }
    
    public void desactivarBoton(int subIndice){
        if (subIndice<cantElementos)
            boton[subIndice].setEnabled(false);
    }
    
    public void asignarOyente(ActionListener ven){
        for (int i = CERO; i < cantElementos;i++)
            boton[i].addActionListener(ven);
    }
    
    public void asignarOyenteBoton(ActionListener ven, int i){
        if (i >=0 && i < cantElementos)
            boton[i].addActionListener(ven);
    }

    @Override
    public void procesar() {
        
    }
}