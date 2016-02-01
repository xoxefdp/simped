/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista.Tablas;

import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Hector Alvarez
 */
public class ModeloDeTablaAlumnos extends DefaultTableModel{
    
    ArrayList <Object> data;
    Object[] nombreColumnas  = {"Codigo","Nombre","Apellido","Fecha de Nacimiento","Genero","Representante",};
    Object [] claseColumnas  = {new String(),new String()
                               ,new String(),new String()
                               ,new String(),new String()};
    
    public ModeloDeTablaAlumnos(){
        data = new ArrayList<>();
        setColumnIdentifiers(nombreColumnas);
    }

    @Override
    public Class getColumnClass(int c) {
        return claseColumnas[c].getClass();
    }
}