package Vista;


import Modelo.Alumno;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
/**
 * 
 */
public class ReporteDeEstudiante extends JFrame, {
   private Alumno marca;
    private ResultSet datos;
    private PdfPTable contenido;
    private float[] anchos = {30,30,30,30};
    private Paragraph parrafo,parrafo2;
    private Document documento;
    Image imghead ;  
    
    public ReporteDeEstudiante(){
       marca = new Alumno();
       //datos = marca.consultarAlumno(WIDTH)
        documento = new Document (PageSize.LETTER);
        contenido = new PdfPTable(anchos.length);
        contenido.setHorizontalAlignment(PdfPTable.ALIGN_CENTER);
        
        try{ 
            FileOutputStream archivoDeSalida; 
            archivoDeSalida = new FileOutputStream 
                            ("/home/yonalix/Escritorio/prueba.pdf");
                                          
                                
                     
            PdfWriter.getInstance (documento, archivoDeSalida);
            documento.open();

            // Crea el párrafo
            parrafo = new Paragraph();
            imprimeEncabezado();
            
          /*  try {
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
            } catch (SQLException ex) {
                Logger.getLogger(ListadoAlfabeticoDelPersonal.class.getName()).log(Level.SEVERE, null, ex);
            }*/
            
        
            
           
                     parrafo2 = new Paragraph("Por medio de la presente se hace constar que el (la) ciudadano(a) JUAN MANUEL PATIÑO, con cédula de identidad   Nº  V- 9.475.711,Representante de el(la) estudiante yoalis garcia que se encuentra  inscrita en nuestra institucion U.E.E. “Antonio Rangel”,Constancia que se emite en la ciudad de Valencia, .\n" 
                                            + "\n"
                                            +"\n" 
                                            +    "	Prof.  xxxxxxxxxx\n" 
                                            +    "       Directora titular ")
                        ;
          
            documento.add(parrafo2);
            parrafo2.setAlignment(Paragraph.ALIGN_CENTER);
            documento.close ();
            archivoDeSalida.close();
        }
        catch (IOException ioe){
            
        }
        catch (DocumentException ioe){
            
        }
    }
    
    private void imprimeEncabezado(){
        try{
            //Alineas el párrafo al centro
            parrafo.setAlignment(Paragraph.ALIGN_CENTER);
            // Agregas contenido al párrafo
            parrafo.add("CONTANCIA DE ESTUDIO");
            //Añades el párrafo al documento
            documento.add(parrafo);
            parrafo.clear();
            parrafo.add(" ");
            documento.add(parrafo);
            
           
        }
        catch(DocumentException de){
            
        }
        
    }
     public void logo(){
        
                
            try {
                imghead = Image.getInstance("/imagenes/logo2.png");
                imghead.setAbsolutePosition(0, 0);
                imghead.setAlignment(Image.ALIGN_CENTER);
                imghead.scalePercent(60f);

               // cbhead = writer.getDirectContent();
               // PdfTemplate tp = cbhead.createTemplate(500, 250); //el área destinada para el encabezado
              //  tp.addImage(imghead);

              //  cbhead.addTemplate(tp,300, 700);//posición del témplate derecha y abajo

            } catch (BadElementException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (DocumentException e) {
                e.printStackTrace();
            }

          // Phrase headPhraseImg = new Phrase(cbhead + "", FontFactory.getFont(FontFactory.TIMES_ROMAN, 7, Font.NORMAL));

        }
        
    public static void main(String[] args) {
        new ReporteDeEstudiante();
    }
}