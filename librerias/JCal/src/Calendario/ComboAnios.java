package Calendario;

import java.awt.Color;
import java.awt.Font;
import java.util.Calendar;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.border.Border;

/**
 *
 * @author Hector Alvarez
 */
public class ComboAnios extends JPanel{
    private JComboBox comboAnio = new JComboBox();
    
    public ComboAnios(){
        
        Border tituloDelPanel = BorderFactory.createTitledBorder
                                   (null,"Años", 1,0,new Font("",Font.BOLD,10));
        
        Border bordeColorAzul = BorderFactory.createLineBorder(Color.BLUE, 2);
        Border bordeDoble = BorderFactory.createCompoundBorder
                                                (bordeColorAzul,tituloDelPanel);

        setBorder(bordeDoble);
       
        Calendar calendario = Calendar.getInstance();
        int anioDeInicio = calendario.get(Calendar.YEAR) - 30;
        for (int i=-30;i<30;i++){
            if (i == 1) comboAnio.setSelectedIndex(30); 
            comboAnio.addItem(anioDeInicio);
            anioDeInicio++;
        }  
        add(comboAnio);
    }  
}
