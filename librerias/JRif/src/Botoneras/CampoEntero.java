package Botoneras;

import java.awt.FlowLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JTextField;

public class CampoEntero extends JTextField implements KeyListener{
    
    String cadena;
    int maxCantDigitos;
    
    public CampoEntero(){
        super(10);
        setLayout(new FlowLayout());
        maxCantDigitos = 10;
        crearGui();
    }
    
    public CampoEntero(int digitos){
        super(digitos);
        setLayout(new FlowLayout());
        maxCantDigitos = digitos;
        crearGui();
    }
    
    public final void crearGui(){
        addKeyListener(this);
    }
        
    public String obtenerNumero(){
        return getText();
    }
    
    public void activarDesactivar(boolean onOff){
        setEnabled(onOff);
    }
    
    
    public void editable(boolean onOff){
        setEditable(onOff);
    }
    
    public void actualizarNumero(String cadena){
        setText(cadena);
    }

    @Override
    public void keyReleased(KeyEvent ekr){

    }
    
    @Override
    public void keyTyped(KeyEvent ekt){
        char c = ekt.getKeyChar();
        if(((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE) ||
             getText().length() + 1 > maxCantDigitos)
                ekt.consume();
    }

    @Override
    public void keyPressed(KeyEvent ekp){
    }
}