/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista.Componentes;

import Controlador.Configuraciones;
import Modelo.Alumno;
import static Modelo.MensajesDeError.errorSQL;
import Modelo.Representante;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * yonalix garcia
 */
public class ReporteConstanciaInscripcion extends JFrame {
    
    private Alumno alumno;
    private Representante representante;
    private ResultSet datos;
    private PdfPTable contenido;
    private float[] anchos = {30,30,30,30};
    private Paragraph parrafo,parrafo2;
    private Document documento;
    private String nombreAl,apellidoAl,sexoAl,cedulaRep,nombreRep,apellidoRep,sexoRep,contenidoSalida;
    Image imghead;
    FileOutputStream archivoDeSalida;
    
    Configuraciones config = new Configuraciones();
    
    private String ano, mes, dia, hora, minuto, segundo,mesActual;
    private final Calendar calendario = new GregorianCalendar();
    private Date tiempoActual;
    
    public ReporteConstanciaInscripcion(ResultSet datosEstudiante,ResultSet datosRepresentante){
        documento = new Document (PageSize.LETTER);
        contenido = new PdfPTable(anchos.length);
        contenido.setHorizontalAlignment(PdfPTable.ALIGN_CENTER);
        alumno = new Alumno();
        representante = new Representante();
        
        try{ 
            tiempoActual = new Date();
            calendario.setTime(tiempoActual);

            ano = calendario.get(Calendar.YEAR) > 9 ? "" + calendario.get(Calendar.YEAR) : "0" + calendario.get(Calendar.YEAR);
            mes = calendario.get(Calendar.MONTH) > 9 ? "" + calendario.get(Calendar.MONTH) : "0" + calendario.get(Calendar.MONTH);
            dia = calendario.get(Calendar.DATE) > 9 ? "" + calendario.get(Calendar.DATE) : "0" + calendario.get(Calendar.DATE);
            hora = String.valueOf(calendario.get(Calendar.HOUR_OF_DAY));
            minuto = calendario.get(Calendar.MINUTE) > 9 ? "" + calendario.get(Calendar.MINUTE) : "0" + calendario.get(Calendar.MINUTE);
            segundo = calendario.get(Calendar.SECOND) > 9 ? "" + calendario.get(Calendar.SECOND) : "0" + calendario.get(Calendar.SECOND);
            
            int mesA = Integer.valueOf(mes) + 1; // PROBLEMA, el mes siempre es mostrado con un entero menos
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
            
            //archivoDeSalida = new FileOutputStream("/home/yonalix/Escritorio/prueba.pdf");
            archivoDeSalida = new FileOutputStream(config.getReportRoute()+ano+"-"+mesA+"-"+dia+"_"+hora+"-"+minuto+"-"+segundo+"_"+"ConstanciaInscripcion.pdf");
                     
            PdfWriter.getInstance(documento, archivoDeSalida);
            documento.open();

            // Crea el párrafo
            parrafo = new Paragraph();
            logo();
            imprimeEncabezado();
            /*
            try {
                while (datos.next()){
                    int lineas = 0;
                    if (lineas > 10){
                        
                        lineas = 0;
                        
                    }
                    contenido.addCell(datos.getString(1));
                    contenido.addCell(datos.getString(2));
                    contenido.addCell(datos.getString(3));
                    contenido.addCell(datos.getString(4));
                    lineas++;
                }
            } catch (SQLException error) {
                String mensaje = errorSQL(error.getSQLState());
                JOptionPane.showMessageDialog(null,mensaje);
            }
            */
           try {
                while (datosEstudiante.next()){
                    nombreAl = datosEstudiante.getString(2);
                    apellidoAl = datosEstudiante.getString(3);
                    sexoAl = datosEstudiante.getString(5);
                }
            } catch (SQLException error) {
                String mensaje = errorSQL(error.getSQLState());
                JOptionPane.showMessageDialog(null,mensaje);
            }
           try {
                while (datosRepresentante.next()){
                    cedulaRep = datosRepresentante.getString(1);
                    nombreRep = datosRepresentante.getString(2);
                    apellidoRep = datosRepresentante.getString(3);
                    sexoRep = datosRepresentante.getString(8);
                }
            } catch (SQLException error) {
                String mensaje = errorSQL(error.getSQLState());
                JOptionPane.showMessageDialog(null,mensaje);
            }
           /*
            if ("masculino".equals(sexoAl)) {
                if ("masculino".equals(sexoRep)) {
                    contenidoSalida = "Por medio de la presente se hace constar que el ciudadano "+nombreRep+" "+apellidoRep+", con cédula de identidad Nº "+cedulaRep+",Representante del estudiante "+nombreAl+" "+apellidoAl+" que se encuentra inscrita en nuestra institucion U.E.E. “Antonio Rangel”,Constancia que se emite en la ciudad de Valencia, .\n\n\n 	Prof.  xxxxxxxxxx\n       Secretaria de Control de Estudios";
                } else {
                    contenidoSalida = "Por medio de la presente se hace constar que la ciudadana "+nombreRep+" "+apellidoRep+", con cédula de identidad Nº "+cedulaRep+",Representante del estudiante "+nombreAl+" "+apellidoAl+" que se encuentra inscrita en nuestra institucion U.E.E. “Antonio Rangel”,Constancia que se emite en la ciudad de Valencia, .\n\n\n 	Prof.  xxxxxxxxxx\n       Secretaria de Control de Estudios";
                }
            } else {
                if ("masculino".equals(sexoRep)) {
                    contenidoSalida = "Por medio de la presente se hace constar que el ciudadano "+nombreRep+" "+apellidoRep+", con cédula de identidad Nº "+cedulaRep+",Representante del estudiante "+nombreAl+" "+apellidoAl+" que se encuentra inscrita en nuestra institucion U.E.E. “Antonio Rangel”,Constancia que se emite en la ciudad de Valencia, .\n\n\n 	Prof.  xxxxxxxxxx\n       Secretaria de Control de Estudios";
                } else {
                    contenidoSalida = "Por medio de la presente se hace constar que la ciudadana "+nombreRep+" "+apellidoRep+", con cédula de identidad Nº "+cedulaRep+",Representante del estudiante "+nombreAl+" "+apellidoAl+" que se encuentra inscrita en nuestra institucion U.E.E. “Antonio Rangel”,Constancia que se emite en la ciudad de Valencia, .\n\n\n 	Prof.  xxxxxxxxxx\n       Secretaria de Control de Estudios";
                }
            }
           */
            /*
            parrafo2 = new Paragraph("Por medio de la presente se hace constar que el (la) ciudadano(a) JUAN MANUEL PATIÑO, con cédula de identidad   Nº  V- 9.475.711,Representante de el(la) estudiante yoalis garcia que se encuentra  inscrita en nuestra institucion U.E.E. “Antonio Rangel”,Constancia que se emite en la ciudad de Valencia, .\n" 
                                    +"\n"+"\n"+"	Prof.  xxxxxxxxxx\n"+"       Directora titular ");
            */
            /*
            parrafo2 = new Paragraph("Por medio de la presente se hace constar que el (la) ciudadano(a) "+nombreRep+" "+apellidoRep+", con cédula de identidad Nº "+cedulaRep+",Representante de el estudiante "+nombreAl+" "+apellidoAl+" que se encuentra  inscrita en nuestra institucion U.E.E. “Antonio Rangel”,Constancia que se emite en la ciudad de Valencia, .\n" 
                                    +"\n"
                                    +"\n" 
                                    +"	Prof.  xxxxxxxxxx\n" 
                                    +"       Directora titular ");
            */
            
            if (sexoRep.equals("Masculino")) {
                contenidoSalida = "Por medio de la presente se hace constar que el ciudadano "+nombreRep+" "+apellidoRep+", con cédula de identidad Nº "+cedulaRep+", Representante del estudiante "+nombreAl+" "+apellidoAl+" que se encuentra inscrito en nuestra institución U.E.E. “Antonio Rangel”, Constancia de Inscripción que emite Control de Estudios U.E.E. “Antonio Rangel” en la ciudad de Valencia, a los "+dia+" dias del mes de "+mesActual+" de "+ano+" a las "+hora+" horas y "+minuto+" minutos.\n\n\n	Firma CE. __________                   Sello Secretaria de CE. __________";
            } else {
                contenidoSalida = "Por medio de la presente se hace constar que la ciudadana "+nombreRep+" "+apellidoRep+", con cédula de identidad Nº "+cedulaRep+", Representante del estudiante "+nombreAl+" "+apellidoAl+" que se encuentra inscrito en nuestra institución U.E.E. “Antonio Rangel”, Constancia de Inscripción que emite Control de Estudios U.E.E. “Antonio Rangel” en la ciudad de Valencia, a los "+dia+" dias del mes de "+mesActual+" de "+ano+" a las "+hora+" horas y "+minuto+" minutos.\n\n\n	Firma CE. __________                   Sello Secretaria de CE. __________";
            }
            parrafo2 = new Paragraph(contenidoSalida);
            
            documento.add(parrafo2);
            parrafo2.setAlignment(Paragraph.ALIGN_CENTER);
            documento.close();
            archivoDeSalida.close();
            
            if (Desktop.isDesktopSupported()) {
                try {
                    File reporte = new File(config.getReportRoute()+ano+"-"+mesA+"-"+dia+"_"+hora+"-"+minuto+"-"+segundo+"_"+"ConstanciaInscripcion.pdf");
                    Desktop.getDesktop().open(reporte);
                } catch (IOException ex) {
                    // no application registered for PDFs
                    JOptionPane.showMessageDialog(null,"No posee aplicación para abrir el reporte");
                }
            }
            
        }
        catch (IOException | DocumentException ioe){
            JOptionPane.showMessageDialog(null,"Error de escritura en disco");
        }
    }
    
    private void imprimeEncabezado(){
        try{
            //Alineas el párrafo al centro
            parrafo.setAlignment(Paragraph.ALIGN_CENTER);
            // Agregas contenido al párrafo
            parrafo.add("CONTANCIA DE INSCRIPCIÓN");
            //Añades el párrafo al documento
            documento.add(parrafo);
            parrafo.clear();
            parrafo.add(" ");
            documento.add(parrafo);
        }
        catch(DocumentException de){
            de.printStackTrace();
        }
        
    }
     public void logo(){
        try {
            //imghead =Image.getInstance("/src/imag/logo2.png");
            //imghead =Image.getInstance("/home/yonalix/Escritorio/simped/src/imagenes/logo2.png");
            //imghead =Image.getInstance("/home/josediaz/Desktop/proyectos-xoxe/simped/src/Vista/Componentes/logo-colegioFIT.png");
            imghead =Image.getInstance(config.getReportLogoRoute());
            imghead.setAlignment(Image.ALIGN_CENTER);
                
            documento.add(imghead);
            // cbhead = writer.getDirectContent();
            // PdfTemplate tp = cbhead.createTemplate(500, 250); //el área destinada para el encabezado
            // tp.addImage(imghead);
            // cbhead.addTemplate(tp,300, 700);//posición del témplate derecha y abajo
        } catch (BadElementException e) {
            e.printStackTrace();
        } catch (IOException | DocumentException e) {
            e.printStackTrace();
        }
        // Phrase headPhraseImg = new Phrase(cbhead + "", FontFactory.getFont(FontFactory.TIMES_ROMAN, 7, Font.NORMAL));
        // Phrase headPhraseImg = new Phrase(cbhead + "", FontFactory.getFont(FontFactory.TIMES_ROMAN, 7, Font.NORMAL));

    }
    /*    
    public static void main(String[] args) {
        new ReporteConstanciaInscripcion();
    }
    */
}