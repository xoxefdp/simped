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
 * tabla Representantes
 * @author josediaz
 */
public class TablaRepresentantes extends JPanel{
    
    public Tabla tablaModelo;
    private Object[] nombreColumnas,claseColumnas,datos = new Object[9];
    public JTable tabla;
    private TableColumn colCedula,colNombre,colApellido,colTelefono,colDireccion,colCorreo,colParentesco,colFecha,colGenero;
    private JTextField campo;
    private ResultSet resultado;

    public TablaRepresentantes(){
        setLayout(new FlowLayout());
        setBorder(BorderFactory.createTitledBorder("Representantes"));
        setOpaque(false);
        crearTabla();
    }
    
    final void crearTabla(){
        Object[] nombreColumnas = {"Cedula","Nombre","Apellido","Telefono","Dirección","Correo","Parentesco","Fecha Nacimiento","Genero"};
        Object[] claseColumnas  = {0,new String(),new String(),new String(),new String(),new String(),new String(),new String(),new String()};
        //Object[] nombreColumnas = {"Cedula","Nombre","Apellido","Telefono","Correo","Parentesco"};
        //Object[] claseColumnas  = {new String(),new String(),new String(),new String(),new String()};
        
        tablaModelo = new Tabla(nombreColumnas,claseColumnas);
        campo = new JTextField();
        campo.setEditable(false);

        tabla = new JTable(tablaModelo);
        tabla.setPreferredScrollableViewportSize(new Dimension(1200,250));
        tabla.setFillsViewportHeight(false);
        colCedula       = tabla.getColumnModel().getColumn(0);
        colNombre       = tabla.getColumnModel().getColumn(1);
        colApellido     = tabla.getColumnModel().getColumn(2);
        colTelefono     = tabla.getColumnModel().getColumn(3);
        colDireccion    = tabla.getColumnModel().getColumn(4);
        colCorreo       = tabla.getColumnModel().getColumn(5);
        colParentesco   = tabla.getColumnModel().getColumn(6);
        colFecha        = tabla.getColumnModel().getColumn(7);
        colGenero       = tabla.getColumnModel().getColumn(8);
        
        colCedula.setMinWidth(75);     colCedula.setMaxWidth(75);     colCedula.setCellEditor(new DefaultCellEditor(campo));
        colNombre.setMinWidth(150);     colNombre.setMaxWidth(150);     colNombre.setCellEditor(new DefaultCellEditor(campo));
        colApellido.setMinWidth(150);   colApellido.setMaxWidth(150);   colApellido.setCellEditor(new DefaultCellEditor(campo));
        colTelefono.setMinWidth(100);   colTelefono.setMaxWidth(100);   colTelefono.setCellEditor(new DefaultCellEditor(campo));
        colDireccion.setMinWidth(200);  colDireccion.setMaxWidth(200);  colDireccion.setCellEditor(new DefaultCellEditor(campo));        
        colCorreo.setMinWidth(225);     colCorreo.setMaxWidth(225);     colCorreo.setCellEditor(new DefaultCellEditor(campo));
        colParentesco.setMinWidth(100); colParentesco.setMaxWidth(100); colParentesco.setCellEditor(new DefaultCellEditor(campo));
        colFecha.setMinWidth(125);      colFecha.setMaxWidth(125);      colFecha.setCellEditor(new DefaultCellEditor(campo));
        colGenero.setMinWidth(50);      colGenero.setMaxWidth(50);      colGenero.setCellEditor(new DefaultCellEditor(campo));
        
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
                datos[6]= entrada.getString(7); //colParentesco
                datos[7]= entrada.getString(8); //colFecha
                datos[8]= entrada.getString(9); //colGenero
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
                datos[6]= entrada.getString(7); //colParentesco
                datos[7]= entrada.getString(8); //colFecha
                datos[8]= entrada.getString(9); //colGenero
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
}