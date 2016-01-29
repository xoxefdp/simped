package Calendario;

import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author Hector Alvarez
 */
public class PanelPrueba extends JPanel{
    JButton boton = new JButton("Probar");
    public PanelPrueba(){
        setLayout(new FlowLayout());
        add(boton);
        setVisible(false);
    }
}
