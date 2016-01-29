package Calendario;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import javax.swing.JApplet;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Presenta un calendario en el cual se puede navegar a través los meses y años.
 * &nbsp
 * @author Héctor Alvarez
 * @version 1.0
 */
public class CalendarioApplet extends JApplet implements ActionListener{
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
    private JPanel panel0 = new JPanel();
    private JPanel panel1 = new JPanel();
    private JPanel panel2 = new JPanel();
    JTextField fecha = new JTextField(16);
    private int CERO = 0;
    private Image imagen;
    /**
     * Constructor de la clase.&nbspRequiere que la clase que lo utilice sea del
     * tipo ActionListener ya que tendrá que manejará los eventos de los
     * botones.
     */
    @Override
    public void init(){
        String nombreImagen = "Logo.jpeg";
        setLayout(new FlowLayout());
        panel1.setLayout(new BorderLayout());
        panel1.add(BorderLayout.NORTH,anioMes);
        panel1.add(BorderLayout.CENTER,diasDelMes);
        fecha.setEditable(false);
        fecha.setFont(new Font("", Font.BOLD,10));
        fecha.setHorizontalAlignment(JTextField.CENTER);
        fecha.setText("" + Calendar.getInstance().getTime());
        panel2.setLayout(new FlowLayout());
        panel2.add(fecha);
        panel1.add(BorderLayout.SOUTH,panel2);
        panel0.add(panel1);
        panel0.setOpaque(false);
        //panel1.setOpaque(false);
        panel2.setOpaque(false);
        
        panel0.setLayout(new FlowLayout());
        panel0.setBackground(Color.LIGHT_GRAY);
        add(panel0);
        setVisible(true);
        if (loadImages(nombreImagen)){
            Graphics g = panel1.getGraphics();
            paint(g);
        }
    }
    @Override
    public void start(){
        
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = CERO; i < diasDelMes.botonDia.length; i++){
            if (e.getSource() == diasDelMes.botonDia[i]){
                int dia = Integer.parseInt(diasDelMes.botonDia[i].getText());
                Calendar cal = Calendar.getInstance();
                cal.set(anioMes.obtenerAnioActual(),
                        (anioMes.obtenerMesActual()),
                        dia);
                fecha.setText("" + cal.getTime());
              return;  
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
    /**
     * Devuelve un String con el valor de la fecha (TimeStamp) escogida en el
     * calendario.
     * @return String de la fecha escogida en el calendario (TimeStamp)
     */
    public String obtenerFechaCalendario(){
        return fecha.getText();
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