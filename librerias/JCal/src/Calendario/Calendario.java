package Calendario;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Presenta un calendario en el cual se puede navegar a través los meses y años.
 * &nbsp
 * @author Héctor Alvarez
 * @version 1.0
 */
public class Calendario extends JDialog implements ActionListener{
    /**
     * Clase que maneja una parrilla de botones que representan los días de un
     * mes.
     * @see DiasDelMes
     */
    public DiasDelMes diasDelMes = new DiasDelMes(this);
    /**
     * Panel con botones y cajas de texto para avanzar y retroceder los meses y
     * los años partiendo del mes y año actual.
     * @see NavegarAnioMes
     */
    public NavegarAnioMes anioMes = new NavegarAnioMes(this);
    private JPanel panel1 = new JPanel();
    private JPanel panel2 = new JPanel();
    private JPanel panelFecha = new JPanel();
    private JTextField fecha = new JTextField(16);
    private int CERO = 0;
    private int dia;
    JTextField fechaEd;
    
    /**
     * Constructor de la clase.&nbspRequiere que se le pase como parámetro un
     * JTextField el cual modificará con la fecha escogida en el calendario.
     * @param fechaEditada Es un JTextField cuyo contenido será editado por esta
     * clase para colocar en ella la fecha escogida en el calendario.
     */

    public Calendario(JTextField fechaEditada){
        fechaEd = fechaEditada;
        setModal(true);
        setLayout(new FlowLayout());
        panel1.setLayout(new BorderLayout());
        panel1.add(BorderLayout.NORTH,anioMes);
        panel1.add(BorderLayout.CENTER,diasDelMes);
        panel2.setBackground(Color.LIGHT_GRAY);
        panel2.setLayout(new BorderLayout());
        panel2.add(BorderLayout.CENTER,panel1);   
        fecha.setEditable(false);
        fecha.setFont(new Font("", Font.BOLD,10));
        fecha.setHorizontalAlignment(JTextField.CENTER);
        fecha.setText("" + Calendar.getInstance().getTime());
        panelFecha.add(fecha);
        panel2.add(BorderLayout.SOUTH,panelFecha);
        add(panel2);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = CERO; i < diasDelMes.botonDia.length; i++){
            if (e.getSource() == diasDelMes.botonDia[i]){
                dia = Integer.parseInt(diasDelMes.botonDia[i].getText());
                Calendar cal = Calendar.getInstance();
                cal.set(anioMes.obtenerAnioActual(),
                        (anioMes.obtenerMesActual()),
                        dia);

                fecha.setText("" + cal.getTime());
                String cero ="00";
                String dia = (cero.substring(0,
                        2-("" + cal.get(Calendar.DAY_OF_MONTH)).length())+
                        cal.get(Calendar.DAY_OF_MONTH));
                String mes = (cero.substring(0,
                        2-("" + ((cal.get(Calendar.MONTH)) + 1)).length())+
                        (cal.get(Calendar.MONTH)+1));
                String ano = "" + cal.get(Calendar.YEAR);
                fechaEd.setText(ano + "/" + mes + "/" + dia);
                dispose();
            }
        }

        if (e.getSource() == anioMes.mesAnterior)
            anioMes.cambiarMesActual
                    (anioMes.menosMes(anioMes.obtenerMesActual()));
        
        if (e.getSource() == anioMes.mesSiguiente)
            anioMes.cambiarMesActual
                    (anioMes.masMes(anioMes.obtenerMesActual()));
        
        if (e.getSource() == anioMes.anioAnterior)
            anioMes.cambiarAnioActual
                    (anioMes.menosAnio(anioMes.obtenerAnioActual()));
          
        if (e.getSource() == anioMes.anioSiguiente)
            anioMes.cambiarAnioActual
                    (anioMes.masAnio(anioMes.obtenerAnioActual()));
        
        int d = diasDelMes.obtenerDiaQueComienzaElMes(
                                                  anioMes.obtenerMesActual(),
                                                  anioMes.obtenerAnioActual());
        int m = diasDelMes.obtenerUltimoDiaDelMes(anioMes.obtenerMesActual(),
                                                  anioMes.obtenerAnioActual());
        diasDelMes.restaurarBotones();
        diasDelMes.ubicarBotones(d, m);
        
        d = Calendar.getInstance().get(Calendar.MONTH);
        m = Calendar.getInstance().get(Calendar.YEAR);
        
        if (d == anioMes.obtenerMesActual() && m == anioMes.obtenerAnioActual()){
            d = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
            diasDelMes.resaltarElDia(d);
        }
        fecha.setText("");
    }   
}