/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista.Tablas;

import static Modelo.MensajesDeError.errorSQL;
import Vista.Formatos.Tabla;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
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
public class TablaGradoFechaAlumnos extends JPanel{
    
    public Tabla tablaModelo;
    private final Object[] datos = new Object[9];
    public JTable tabla;
    private TableColumn colFecha,colCodigo,colNombre,colApellido,colGenero,colParentesco,colRepresentante,col8,col9;
    private JTextField campo;
    private String mensaje;
    
    /**
     * Construye la JTable.
     * @param ancho
     * @param alto
     */
    public TablaGradoFechaAlumnos(int ancho, int alto){
        
        setLayout(new FlowLayout());
        //setBorder(BorderFactory.createTitledBorder("Estudiantes Asignados(as)"));
        setOpaque(false);
        crearTabla(ancho,alto);
    }
    
    final void crearTabla(int ancho, int alto){
        
        Object[] nombreColumnas = {"Fecha","Codigo","Nombre","Apellido","","","","",""};
        Object[] claseColumnas  = {new String(),0,new String(),new String(),new String(),new String(),new String(),new String(),new String()};
        
        tablaModelo = new Tabla(nombreColumnas,claseColumnas);
        campo = new JTextField();
        campo.setEditable(false);

        tabla = new JTable(tablaModelo);
        tabla.setPreferredScrollableViewportSize(new Dimension(ancho,alto)); // 825,250
        tabla.setFillsViewportHeight(false);
        
        colFecha        = tabla.getColumnModel().getColumn(0);
        colCodigo       = tabla.getColumnModel().getColumn(1);
        colNombre       = tabla.getColumnModel().getColumn(2);
        colApellido     = tabla.getColumnModel().getColumn(3);
        colGenero     = tabla.getColumnModel().getColumn(4);
        colParentesco     = tabla.getColumnModel().getColumn(5);
        colRepresentante     = tabla.getColumnModel().getColumn(6);
        col8     = tabla.getColumnModel().getColumn(7);
        col9     = tabla.getColumnModel().getColumn(8);
        
        
        colFecha.setMinWidth(0);        colFecha.setMaxWidth(0);        colFecha.setCellEditor(new DefaultCellEditor(campo));
        colCodigo.setMinWidth(75);      colCodigo.setMaxWidth(75);      colCodigo.setCellEditor(new DefaultCellEditor(campo));
        colNombre.setMinWidth(150);     colNombre.setMaxWidth(150);     colNombre.setCellEditor(new DefaultCellEditor(campo));
        colApellido.setMinWidth(150);   colApellido.setMaxWidth(150);   colApellido.setCellEditor(new DefaultCellEditor(campo));
        colGenero.setMinWidth(150);   colGenero.setMaxWidth(150);   colGenero.setCellEditor(new DefaultCellEditor(campo));
        colParentesco.setMinWidth(150);   colParentesco.setMaxWidth(150);   colParentesco.setCellEditor(new DefaultCellEditor(campo));
        colRepresentante.setMinWidth(150);   colRepresentante.setMaxWidth(150);   colRepresentante.setCellEditor(new DefaultCellEditor(campo));
        col8.setMinWidth(150);   col8.setMaxWidth(150);   col8.setCellEditor(new DefaultCellEditor(campo));
        col9.setMinWidth(150);   col9.setMaxWidth(150);   col9.setCellEditor(new DefaultCellEditor(campo));
        JScrollPane scrollPanel = new JScrollPane(tabla);
        add(scrollPanel);
    }
    
    /**
     * Carga la JTable con los datos iniciales.
     * @param entrada
     */
    public void cargarTabla(ResultSet entrada){
        //Limpia la tabla de registros, sirve para prepararla para otras consultas
        int filas = tabla.getRowCount();
        for (int i = 0; i < filas; i++){
            tablaModelo.removeRow(0);
        }
        
        try{
            while (entrada.next()){
                /*
                int f=1;
                for (int i = 0; i < filas; i++) {
                    if (f!=filas) {
                        datos[i] = entrada.getString(f); //columnas
                        System.out.println(datos[i]);
                        f++;
                    } else {
                        tablaModelo.addRow(datos);
                    }
                }
                */
                datos[0]= entrada.getString(1); //colCodigo
                datos[1]= entrada.getString(2); //colNombre
                datos[2]= entrada.getString(3); //colApellido
                datos[3]= entrada.getString(4); //colFecha
                datos[4]= entrada.getString(5); //colFecha
                datos[5]= entrada.getString(6); //colFecha
                datos[6]= entrada.getString(7); //colFecha
                datos[7]= entrada.getString(8); //colFecha
                
                tablaModelo.addRow(datos);
            }
        } catch (SQLException error) {
            mensaje = errorSQL(error.getSQLState());
            JOptionPane.showMessageDialog(null,mensaje);
        }
    } 
    /**
     * Agrega un fila a la JTable con los datos pasados por parametros.
     * @param entrada
     * @return Verdadero si la inclusion fue exitosa, falso en caso contrario.
     */
    public boolean agregarFila(ResultSet entrada){
        /*
        boolean status = false;
        datos[0] = nombreLaboratorio;
        datos[1] = "0";
        status = true;
        tablaModelo.addRow(datos);
        return status;
        */
        boolean status = false;
        try{
            while (entrada.next()){
                /*
                int f=1;
                for (int i = 0; i < filas; i++) {
                    if (f!=filas) {
                        datos[i] = entrada.getString(f); //columnas
                        System.out.println(datos[i]);
                        f++;
                    } else {
                        tablaModelo.addRow(datos);
                    }
                }
                */
                datos[0]= entrada.getString(1);
                datos[1]= entrada.getString(2);
                datos[2]= entrada.getString(3);
                datos[3]= entrada.getString(4);
                datos[4]= entrada.getString(5); //colFecha
                datos[5]= entrada.getString(6); //colFecha
                datos[6]= entrada.getString(7); //colFecha
                datos[7]= entrada.getString(8); //colFecha
                tablaModelo.addRow(datos);
            }
            status = true;
        } catch (SQLException error) {
            mensaje = errorSQL(error.getSQLState());
            JOptionPane.showMessageDialog(null,mensaje);
        }
        return status;
    }
    
    /**
     * Modifica una fila a la JTable con los datos pasados por parametros.
     * @param 
     * @return Verdadero si la modificacion fue exitosa,falso en caso contrario.
     */
    public boolean modificarFila(int codigoAlumno){
        boolean status = false;
        int fila = tabla.getSelectedRow();
        
        if (fila >= 0){
            datos[0] = codigoAlumno;
            tablaModelo.setValueAt(codigoAlumno, fila, 0);
            status = true;
        }
        return status;
    }
    
    /**
     * Elimina la fila que este seleccionada en la JTable.
     * @return Verdadero si la eliminación fue exitosa, falso en caso contrario.
     */
    public boolean eliminarFila(){
        int fila = tabla.getSelectedRow();
        boolean status = false;
        if (fila >= 0){
            int respuesta = JOptionPane.showConfirmDialog(this,
                                               "¿Seguro quiere eliminar a: "+
                                               tablaModelo.getValueAt(fila, 0));
            if (respuesta == JOptionPane.OK_OPTION){
                tablaModelo.removeRow(fila);
                status = true;
            }   
        }
        return status;
    }
    
    /**
     * 
     * @return 
     */
    public String obtenerDescripcion(){
        int fila = tabla.getSelectedRow();
        if (fila >= 0)
            return (String)tabla.getValueAt(fila, 0);
        else
            return null;
    }
    
    /**
     * 
     * @return 
     */
    public String obtenerId(){
        int fila = tabla.getSelectedRow();
        if (fila >= 0)
            return (String)tabla.getValueAt(fila, 1);
        else
            return null;
    }
    
    /**
     * Preselecciona un campo en la tabla
     * @param entrada
     *
     */
    public void seleccionarFila(int entrada){
        int fila = 0;
        try {
            fila = entrada;
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        if (fila < 0 || fila >= tabla.getRowCount()) {
            JOptionPane.showMessageDialog(tabla, "Selección fuera de rango de tabla!");
        } else {
            tabla.setRowSelectionInterval(fila, fila);
        }
    }
}