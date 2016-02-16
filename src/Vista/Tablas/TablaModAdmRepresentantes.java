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
 *
 * @author josediaz
 */
public class TablaModAdmRepresentantes extends JPanel{
    
    public Tabla tablaModelo;
    private Object[] nombreColumnas,claseColumnas;
    private final Object[] datos = new Object[8];
    public JTable tabla;
    private TableColumn colCedula,colNombre,colApellido,colTelefono,colDireccion,colCorreo,colFecha,colGenero;
    private JTextField campo;
    private ResultSet resultado;

    public TablaModAdmRepresentantes(){
        setLayout(new FlowLayout());
        setBorder(BorderFactory.createTitledBorder("Representantes"));
        setOpaque(false);
        crearTabla();
    }
    
    final void crearTabla(){
        Object[] nombreColumnas = {"Cedula","Nombre","Apellido","Telefono","Dirección","Correo","Fecha Nacimiento","Genero"};
        Object[] claseColumnas  = {0,new String(),new String(),new String(),new String(),new String(),new String(),new String()};
        
        tablaModelo = new Tabla(nombreColumnas,claseColumnas);
        campo = new JTextField();
        campo.setEditable(false);

        tabla = new JTable(tablaModelo);
        tabla.setPreferredScrollableViewportSize(new Dimension(400,75));
        tabla.setFillsViewportHeight(false);
        colCedula       = tabla.getColumnModel().getColumn(0);
        colNombre       = tabla.getColumnModel().getColumn(1);
        colApellido     = tabla.getColumnModel().getColumn(2);
        colTelefono     = tabla.getColumnModel().getColumn(3);
        colDireccion    = tabla.getColumnModel().getColumn(4);
        colCorreo       = tabla.getColumnModel().getColumn(5);
        colFecha        = tabla.getColumnModel().getColumn(6);
        colGenero       = tabla.getColumnModel().getColumn(7);
        
        colCedula.setMinWidth(100);     colCedula.setMaxWidth(100);     colCedula.setCellEditor(new DefaultCellEditor(campo));
        colNombre.setMinWidth(150);     colNombre.setMaxWidth(150);     colNombre.setCellEditor(new DefaultCellEditor(campo));
        colApellido.setMinWidth(150);   colApellido.setMaxWidth(150);   colApellido.setCellEditor(new DefaultCellEditor(campo));
        colTelefono.setMinWidth(0);     colTelefono.setMaxWidth(0);     colTelefono.setCellEditor(new DefaultCellEditor(campo));
        colDireccion.setMinWidth(0);    colDireccion.setMaxWidth(0);    colDireccion.setCellEditor(new DefaultCellEditor(campo));        
        colCorreo.setMinWidth(0);       colCorreo.setMaxWidth(0);       colCorreo.setCellEditor(new DefaultCellEditor(campo));
        colFecha.setMinWidth(0);        colFecha.setMaxWidth(0);        colFecha.setCellEditor(new DefaultCellEditor(campo));
        colGenero.setMinWidth(0);      colGenero.setMaxWidth(0);        colGenero.setCellEditor(new DefaultCellEditor(campo));
        
        JScrollPane scrollPanel = new JScrollPane(tabla);
        add(scrollPanel);
    }
    
    /**
     * Carga la JTable con los datos
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
                datos[6]= entrada.getString(7); //colFecha
                datos[7]= entrada.getString(8); //colGenero
                tablaModelo.addRow(datos);
            }
        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, "ERROR EN SENTENCIA SQL /n" + error);
        }
    } 
    
    /**
     * 
     * Agrega una nueva fila a la tabla
     * @param entrada
     * @return 
     */
    public boolean agregarFila(ResultSet entrada){
        /*
        boolean status = false;
        datos[0] = fila;
        datos[1] = "0";
        todoBien = true;
        tablaModelo.addRow(datos);
        return status;
        */
        boolean status = false;
        
        try{
            while(entrada.next()) {
                datos[0]= entrada.getString(1); //colCedula
                datos[1]= entrada.getString(2); //colNombre
                datos[2]= entrada.getString(3); //colApellido
                datos[3]= entrada.getString(4); //colTelefono
                datos[4]= entrada.getString(5); //colDireccion
                datos[5]= entrada.getString(6); //colCorreo
                datos[6]= entrada.getString(7); //colFecha
                datos[7]= entrada.getString(8); //colGenero
                tablaModelo.addRow(datos);
            }
            status = true;
        } catch (SQLException error) {
           JOptionPane.showMessageDialog(null, "ERROR EN SENTENCIA SQL /n" + error);
        }
        return status;
    }
    
    /**
     * 
     * @param modFila
     * @return 
     */
    public boolean modificarFila(String modFila){
        boolean status = false;
        int fila = tabla.getSelectedRow();
        
        if (fila >= 0){
            datos[0] = modFila;
            tablaModelo.setValueAt(modFila, fila, 0);
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
            int respuesta = JOptionPane.showConfirmDialog(this, "¿Seguro quiere eliminar a: "
                +tablaModelo.getValueAt(fila, 0));
            
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