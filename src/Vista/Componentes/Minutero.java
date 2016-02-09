/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista.Componentes;

import Vista.Formatos.CampoTexto;
import java.util.Calendar; 
import java.util.Date; 
import java.util.GregorianCalendar; 
import javax.swing.BorderFactory;
/**
 *
 * @author josediaz
 */
public final class Minutero extends CampoTexto implements Runnable{
    
    private String ano, mes, dia, hora, minuto, segundo;
    private final Calendar calendario = new GregorianCalendar();
    private Date tiempoActual;
    private String valorTiempo;
    Thread hilo;
    
    /**
     * Monitorea y muestra el tiempo actual
     */
    public Minutero() {
        super(null,31);
        setBorder(BorderFactory.createTitledBorder("Tiempo Actual"));
        alinearTexto("CENTER");
        cambiarTipografia("Monospaced",1, 20);
        hacerEditable(false);
        hilo = new Thread(this);
        hilo.start();
        run();
    }
    
    /**
     * Recoge los datos inherentes al calculo del tiempo
     */
    public void actualiza() {
        tiempoActual = new Date();
        calendario.setTime(tiempoActual);
        
        ano = calendario.get(Calendar.YEAR) > 9 ? "" + calendario.get(Calendar.YEAR) : "0" + calendario.get(Calendar.YEAR);
        mes = calendario.get(Calendar.MONTH) > 9 ? "" + calendario.get(Calendar.MONTH) : "0" + calendario.get(Calendar.MONTH);
        dia = calendario.get(Calendar.DATE) > 9 ? "" + calendario.get(Calendar.DATE) : "0" + calendario.get(Calendar.DATE);
        hora = String.valueOf(calendario.get(Calendar.HOUR_OF_DAY));
        minuto = calendario.get(Calendar.MINUTE) > 9 ? "" + calendario.get(Calendar.MINUTE) : "0" + calendario.get(Calendar.MINUTE);
        segundo = calendario.get(Calendar.SECOND) > 9 ? "" + calendario.get(Calendar.SECOND) : "0" + calendario.get(Calendar.SECOND);
    }

    public String getYear(){
        tiempoActual = new Date();
        calendario.setTime(tiempoActual);
        ano = calendario.get(Calendar.YEAR) > 9 ? "" + calendario.get(Calendar.YEAR) : "0" + calendario.get(Calendar.YEAR);
        return ano;
    }
    
    @ Override
    public void run() {
        
        Thread ct = Thread.currentThread();
        while (ct == hilo) {
            try {
                actualiza();
                int mesA = Integer.valueOf(mes) + 1; // PROBLEMA, el mes siempre es mostrado con un entero menos
                String mesActual;
                switch (mesA) {
                    case 1:  mesActual = "Enero"; break;
                    case 2:  mesActual = "Febrero"; break;
                    case 3:  mesActual = "Marzo"; break;
                    case 4:  mesActual = "Abril"; break;
                    case 5:  mesActual = "Mayo"; break;
                    case 6:  mesActual = "Junio"; break;
                    case 7:  mesActual = "Julio"; break;
                    case 8:  mesActual = "Agosto"; break;
                    case 9:  mesActual = "Septiembre"; break;
                    case 10: mesActual = "Octubre"; break;
                    case 11: mesActual = "Noviembre"; break;
                    case 12: mesActual = "Diciembre"; break;
                    default: mesActual = "Mes invalido"; break;
                }
                
                valorTiempo=mesActual+" "+dia+ " de " + ano + "\n" + hora + ":" + minuto + ":" + segundo;
                cambiarContenido(valorTiempo); //propiedad de campo tiempo
                
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

}
