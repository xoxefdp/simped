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
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * josediaz
 */
public class ReporteListaGrado extends JFrame {
    
    private Alumno alumno;
    private Representante representante;
    private ResultSet datos;
    private PdfPTable contenidoPro,contenidoEst;
    private float[] anchosPro = {30,30,30};
    private float[] anchosEst = {30,30,30,30};
    private Paragraph parrafo;
    private Document documento;
    private String codigoAl,nombreAl,apellidoAl,sexoAl,grado,seccion,fecha,cedulaPr,nombrePr,apellidoPr,contenidoSalida;
    Image imghead;
    FileOutputStream archivoDeSalida;
    
    Configuraciones config = new Configuraciones();
    
    private String ano, mes, dia, hora, minuto, segundo,mesActual;
    private final Calendar calendario = new GregorianCalendar();
    private Date tiempoActual;
    
    public ReporteListaGrado(ResultSet datosEstudiante, ResultSet datosProfesor){
        documento = new Document (PageSize.LETTER);
        contenidoPro = new PdfPTable(anchosPro.length);
        contenidoPro.setHorizontalAlignment(PdfPTable.ALIGN_CENTER);
        
        contenidoEst = new PdfPTable(anchosEst.length);
        contenidoEst.setHorizontalAlignment(PdfPTable.ALIGN_CENTER);
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
            archivoDeSalida = new FileOutputStream(config.getReportRoute()+ano+"-"+mesA+"-"+dia+"_"+hora+"-"+minuto+"-"+segundo+"_"+"ListadoGrado.pdf");

/*************************************************************************************************/
           try{
                while(datosProfesor.next()){
                    fecha = datosProfesor.getString(1);
                    grado =datosProfesor.getString(12);
                    seccion = datosProfesor.getString(13);
                    //datosProfesor.first();
                }
                datosProfesor.beforeFirst();
            }catch(SQLException error){
                String mensaje = errorSQL(error.getSQLState());
                JOptionPane.showMessageDialog(null,mensaje);
            }

            PdfWriter.getInstance(documento, archivoDeSalida);
            documento.open();

            // Crea el párrafo
            parrafo = new Paragraph();
            logo();
            imprimeEncabezado();
            
            //alinear el parrafo al centro
            parrafo.setAlignment(Paragraph.ALIGN_CENTER);
            //agregar contenido al parrafo
            parrafo.add("Profesor(es) Asignado(s)");
            //añade el parrafo al documento
            documento.add(parrafo);
            parrafo.clear();
            parrafo.add(" ");
            
            contenidoPro.addCell("Cedula");
            contenidoPro.addCell("Nombre");
            contenidoPro.addCell("Apellido");
            
            try {
                while(datosProfesor.next()){
                    //cedulaPr = datosProfesor.getString(2);
                    //nombrePr = datosProfesor.getString(3);
                    //apellidoPr = datosProfesor.getString(4);
                    contenidoPro.addCell(datosProfesor.getString(2));
                    contenidoPro.addCell(datosProfesor.getString(3));
                    contenidoPro.addCell(datosProfesor.getString(4));
                }
            } catch (SQLException error) {
                String mensaje = errorSQL(error.getSQLState());
                JOptionPane.showMessageDialog(null,mensaje);
            }
            documento.add(parrafo);
            parrafo.clear();
            parrafo.add(" ");
            documento.add(contenidoPro);
            contenidoPro.flushContent();
            
            parrafo.add("Estudiante(es) Asignado(s)");
            parrafo.add(" ");
            
            contenidoEst.addCell("Codigo");
            contenidoEst.addCell("Nombre");
            contenidoEst.addCell("Apellido");
            contenidoEst.addCell("Sexo");
            
            try {
                while(datosEstudiante.next()){
                    //codigoAl = datosEstudiante.getString(1);
                    //nombreAl = datosEstudiante.getString(2);
                    //apellidoAl = datosEstudiante.getString(3);
                    //sexoAl = datosEstudiante.getString(5); // se usa en caso de cambio en la escritura del calificativo estudiante
                    //fecha = datosEstudiante.getString(6);
                    //grado = datosEstudiante.getString(8);
                    //seccion = datosEstudiante.getString(9);
                    contenidoEst.addCell(datosEstudiante.getString(2));
                    contenidoEst.addCell(datosEstudiante.getString(3));
                    contenidoEst.addCell(datosEstudiante.getString(4));
                    contenidoEst.addCell(datosEstudiante.getString(6));
                }
            } catch (SQLException error) {
                String mensaje = errorSQL(error.getSQLState());
                JOptionPane.showMessageDialog(null,mensaje);
            }
            documento.add(parrafo);
            documento.add(contenidoEst);
            contenidoEst.flushContent();
            parrafo.clear();
            
            documento.close();
            archivoDeSalida.close();
            
            if (Desktop.isDesktopSupported()) {
                try {
                    File reporte = new File(config.getReportRoute()+ano+"-"+mesA+"-"+dia+"_"+hora+"-"+minuto+"-"+segundo+"_"+"ListadoGrado.pdf");
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
            parrafo.add("Lista de Asistencia "+fecha+" "+grado+" "+seccion);
            //Añades el párrafo al documento
            documento.add(parrafo);
            parrafo.clear();
            parrafo.add(" ");
            documento.add(parrafo);
            contenidoPro.flushContent();
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