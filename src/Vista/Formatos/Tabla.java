/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista.Formatos;

import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author José Diaz
 */
public class Tabla extends DefaultTableModel{
    
    private ArrayList <Object> data;
    private Object[] nombreColumnas;
    private Object[] claseColumnas;
    
    public Tabla(Object[] nombresCampos, Object[] claseColumnas){
        nombreColumnas = nombresCampos;
        data = new ArrayList<>();
        setColumnIdentifiers(nombreColumnas);
    }

    @Override
    public Class getColumnClass(int c) {
        return claseColumnas[c].getClass();
    }
}