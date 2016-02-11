/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Controlador.Configuraciones;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author josediaz
 */
public class ConexionDB{
    
    public static Connection conectarDB(){
        Configuraciones config = new Configuraciones();
        Connection conexion = null;
        String mensaje;
        
        try{
            Class.forName(config.getDBController());
            conexion = DriverManager.getConnection(config.getConnectionURL(),config.getUserName(),config.getDBPassWord());
        }
	catch(ClassNotFoundException error){
            mensaje = "Driver errado o no existe. " + error.getMessage();
            JOptionPane.showMessageDialog(null, mensaje);
	}
	catch(SQLException error){
            mensaje = error.getSQLState();
            JOptionPane.showMessageDialog(null, "En conexion " + mensaje);
	}
        return conexion;
    }
}