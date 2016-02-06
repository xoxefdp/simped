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
import java.sql.SQLException;
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
public class TablaAlumnos extends JPanel{
    
    //private ModeloDeTablaAlumnos tablaModelo;
    public Tabla tablaModelo;
    private Object[] nombreColumnas,claseColumnas,datos = new Object[6];
    public JTable tabla;
    private TableColumn colNombre,colCodigo,colApellido,colFecha,colGenero,colRepresentante;
    private JTextField campo;
    private ResultSet resultado;
    
    /**
     * Construye la JTable.
     */
    public TablaAlumnos(){
        
        setLayout(new FlowLayout());
        setBorder(BorderFactory.createTitledBorder("Alumnos"));
        setOpaque(false);
        crearTabla();
    }
    
    final void crearTabla(){
        
        Object[] nombreColumnas = {"Codigo","Nombre","Apellido","Fecha de Nacimiento","Genero","Representante"};
        Object[] claseColumnas  = {0,new String(),new String(),new String(),new String(),0};
        
        tablaModelo = new Tabla(nombreColumnas,claseColumnas);
        campo = new JTextField();
        campo.setEditable(false);

        tabla = new JTable(tablaModelo);
        tabla.setPreferredScrollableViewportSize(new Dimension(805,250));
        tabla.setFillsViewportHeight(false);
        colCodigo       = tabla.getColumnModel().getColumn(0);
        colNombre       = tabla.getColumnModel().getColumn(1);
        colApellido     = tabla.getColumnModel().getColumn(2);
        colFecha        = tabla.getColumnModel().getColumn(3);
        colGenero       = tabla.getColumnModel().getColumn(4);
        colRepresentante= tabla.getColumnModel().getColumn(5);
        
        colCodigo.setMinWidth(100);         colCodigo.setMaxWidth(100);         colCodigo.setCellEditor(new DefaultCellEditor(campo));        
        colNombre.setMinWidth(200);         colNombre.setMaxWidth(200);         colNombre.setCellEditor(new DefaultCellEditor(campo));        
        colApellido.setMinWidth(200);       colApellido.setMaxWidth(200);       colApellido.setCellEditor(new DefaultCellEditor(campo));        
        colFecha.setMinWidth(150);          colFecha.setMaxWidth(150);          colFecha.setCellEditor(new DefaultCellEditor(campo));        
        colGenero.setMinWidth(50);          colGenero.setMaxWidth(50);          colGenero.setCellEditor(new DefaultCellEditor(campo));        
        colRepresentante.setMinWidth(100);  colRepresentante.setMaxWidth(100);  colRepresentante.setCellEditor(new DefaultCellEditor(campo));        
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
                datos[0]= entrada.getString(1); //colCedula
                datos[1]= entrada.getString(2); //colNombre
                datos[2]= entrada.getString(3); //colApellido
                datos[3]= entrada.getString(4); //colTelefono
                datos[4]= entrada.getString(5); //colDireccion
                datos[5]= entrada.getString(6); //colCorreo
                tablaModelo.addRow(datos);
            }
        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, "ERROR EN SENTENCIA SQL /n" + error);
        }
    } 
    /**
     * Agrega un fila a la JTable con los datos pasados por parametros.
     * @param entrada
     * @param 
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
                datos[4]= entrada.getString(5);
                datos[5]= entrada.getString(6);
                tablaModelo.addRow(datos);
            }
            status = true;
        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, "ERROR EN SENTENCIA SQL /n" + error);
        }
        return status;
    }
    /**
     * Modifica una fila a la JTable con los datos pasados por parametros.
     * @param 
     * @return Verdadero si la modificacion fue exitosa,falso en caso contrario.
     */
    public boolean modificarFila(String nombreLaboratorio){
        boolean status = false;
        int fila = tabla.getSelectedRow();
        
        if (fila >= 0){
            datos[0] = nombreLaboratorio;
            tablaModelo.setValueAt(nombreLaboratorio, fila, 0);
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