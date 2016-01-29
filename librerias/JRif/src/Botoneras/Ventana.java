package Botoneras;

import RIF.FormatoDelRif;
import RIF.ValidaRif;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

public class Ventana  extends JFrame implements ActionListener{
    
    BotoneraCool botonesCool;
    String [] nombres= {"Aceptar","Guardar"};
    //BotoneraInterface [] acciones = {new Clase1(), new Clase2()};
    FormatoDelRif valida = new FormatoDelRif(8);
    
    Ventana (){
        setTitle("Prueba");
        setLayout(new BorderLayout());
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        botonesCool = new BotoneraCool(nombres);
        botonesCool.boton[0].addActionListener(this);
        add(valida,BorderLayout.NORTH);
        add(botonesCool,BorderLayout.SOUTH);
        setLocationRelativeTo(null);
        pack();
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        System.out.println(ValidaRif.validar(valida.obtenerCadenaDelRif()));
        int i;
        for (i = 0; i < botonesCool.boton.length; i++)
            if (e.getSource().equals(botonesCool.boton[i]))
                break;
        //acciones[i].procesar(); 
    }
}