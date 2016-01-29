package Calendario;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import RIF.*;
/**
 *
 * @author hector
 */
public class Probar extends JFrame implements ActionListener {
    Calendario cal;
    
    ComboAnios anios = new ComboAnios();
    JButton calendario1;
    JTextField fechaEd = new JTextField();
    public Probar(){
        setLayout(new FlowLayout());
        setSize (300,200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        calendario1 = new JButton("Calendario");
        calendario1.addActionListener(this);
        add(calendario1);
        this.setLocationRelativeTo(null);
        setVisible(true);
    } 

    public static void main (String[] args){ 
        new Probar();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(calendario1)){
            cal = new Calendario(fechaEd);
            JOptionPane.showMessageDialog(null, fechaEd);
        
    }
}
    
    }