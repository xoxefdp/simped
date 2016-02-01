/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista.Tablas;

import Vista.Formatos.Tabla;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.sql.ResultSet;
import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableColumn;

/**
 * Define una JTable a traves de la cual se gestionara la actualizacion de la
 * tabla Laboratorio.
 * @author Hector Alvarez
 */
public class TablaProfesores extends JPanel{
    
    private Tabla tablaModelo;
    private Object[] nombreColumnas,claseColumnas,datos = new Object[6];
    private JTable tabla;
    private TableColumn colCedula,colNombre,colApellido,colFecha,colDireccion,colCorreo,colTelefono,colGenero,colTitulo;
    private JTextField campo;
    private ResultSet resultado;
    
    /**
     * Construye la JTable.
     */
    public TablaProfesores(){
        
        setLayout(new FlowLayout());
        setBorder(BorderFactory.createTitledBorder("Profesores"));
        setOpaque(false);
        crearTabla();
    }
    
    final void crearTabla(){
        
        //Object[] nombreColumnas = {"Cedula","Nombre","Apellido","Fecha de Nacimiento","Dirección","Correo","Telefono","Genero","Titulo"};
        Object[] nombreColumnas = {"Cedula","Nombre","Apellido","Telefono","Correo","Genero"};
        //Object[] claseColumnas  = {new String(),new String(),new String(),new String(),new String(),new String(),new String(),new String(),new String()};
        Object[] claseColumnas  = {new String(),new String(),new String(),new String(),new String(),new String()};
        
        tablaModelo = new Tabla(nombreColumnas,claseColumnas);
        campo = new JTextField();
        campo.setEditable(false);

        tabla = new JTable(tablaModelo);
        tabla.setPreferredScrollableViewportSize(new Dimension(950,200));
        tabla.setFillsViewportHeight(false);
        colCedula       = tabla.getColumnModel().getColumn(0);
        colNombre       = tabla.getColumnModel().getColumn(1);
        colApellido     = tabla.getColumnModel().getColumn(2);
        colTelefono     = tabla.getColumnModel().getColumn(3);
        //colFecha        = tabla.getColumnModel().getColumn(3);
        //colDireccion    = tabla.getColumnModel().getColumn(4);
        colCorreo       = tabla.getColumnModel().getColumn(4);
        colGenero       = tabla.getColumnModel().getColumn(5);
/*
        colTelefono     = tabla.getColumnModel().getColumn(6);
        colGenero       = tabla.getColumnModel().getColumn(7);
        colTitulo       = tabla.getColumnModel().getColumn(8);
*/
        colCedula.setMinWidth(100);
        colCedula.setMaxWidth(100);
        colCedula.setCellEditor(new DefaultCellEditor(campo));
        
        colNombre.setMinWidth(200);
        colNombre.setMaxWidth(200);
        colNombre.setCellEditor(new DefaultCellEditor(campo));
        
        colApellido.setMinWidth(200);
        colApellido.setMaxWidth(200);
        colApellido.setCellEditor(new DefaultCellEditor(campo));
/*        
        colFecha.setMinWidth(100);
        colFecha.setMaxWidth(100);
        colFecha.setCellEditor(new DefaultCellEditor(campo));
        
        colDireccion.setMinWidth(50);
        colDireccion.setMaxWidth(50);
        colDireccion.setCellEditor(new DefaultCellEditor(campo));
*/
        colTelefono.setMinWidth(200);
        colTelefono.setMaxWidth(200);
        colTelefono.setCellEditor(new DefaultCellEditor(campo));
                
        colCorreo.setMinWidth(200);
        colCorreo.setMaxWidth(200);
        colCorreo.setCellEditor(new DefaultCellEditor(campo));
        
        colGenero.setMinWidth(50);
        colGenero.setMaxWidth(50);
        colGenero.setCellEditor(new DefaultCellEditor(campo));
/*        
        colTitulo.setMinWidth(200);
        colTitulo.setMaxWidth(200);
        colTitulo.setCellEditor(new DefaultCellEditor(campo));
*/      
        JScrollPane scrollPanel = new JScrollPane(tabla);
        add(scrollPanel);
    }
    
    /**
     * Carga la JTable con los datos iniciales.
     */
    public void cargarTabla(){
        int filas = tabla.getRowCount();
        /**
         * El siguiente for borra los registros previamente cargados.
         */ 
        for (int i = 0; i < filas; i++)
            tablaModelo.removeRow(0);
        
        datos[0] = "Carga inicial";
        datos[1] = "0";
        tablaModelo.addRow(datos);
    } 
    /**
     * Agrega un fila a la JTable con los datos pasados por parametros.
     * @param nombreLaboratorio Nombre del laboratorio a agregar.
     * @return Verdadero si la inclusion fue exitosa, falso en caso contrario.
     */
    public boolean agregarFila(String nombreLaboratorio){
        boolean todoBien = false;
        datos[0] = nombreLaboratorio;
        datos[1] = "0";
        todoBien = true;
        tablaModelo.addRow(datos);
        return todoBien;
    }
    /**
     * Modifica una fila a la JTable con los datos pasados por parametros.
     * @param nombreLaboratorio Nombre del laboratorio a agregar.
     * @return Verdadero si la modificacion fue exitosa,falso en caso contrario.
     */
    public boolean modificarFila(String nombreLaboratorio){
        boolean todoBien = false;
        int fila = tabla.getSelectedRow();
        
        if (fila >= 0){
            datos[0] = nombreLaboratorio;
            tablaModelo.setValueAt(nombreLaboratorio, fila, 0);
            todoBien = true;
        }
        return todoBien;
    }  
    /**
     * Elimina la fila que este seleccionada en la JTable.
     * @return Verdadero si la eliminación fue exitosa, falso en caso contrario.
     */
    public boolean eliminarFila(){
        int fila = tabla.getSelectedRow();
        boolean todoBien = false;
        if (fila >= 0){
            int respuesta = JOptionPane.showConfirmDialog(this,
                                               "¿Seguro quiere eliminar a: "+
                                               tablaModelo.getValueAt(fila, 0));
            if (respuesta == JOptionPane.OK_OPTION){
                tablaModelo.removeRow(fila);
                todoBien = true;
            }   
        }
        return todoBien;
    }
    
    public String obtenerDescripcion(){
        int fila = tabla.getSelectedRow();
        if (fila >= 0)
            return (String)tabla.getValueAt(fila, 0);
        else
            return null;
    }
    
    public String obtenerId(){
        int fila = tabla.getSelectedRow();
        if (fila >= 0)
            return (String)tabla.getValueAt(fila, 1);
        else
            return null;
    }
}