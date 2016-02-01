package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Hector Alvarez
 */
public class ConexionDB implements ConstantesDB{
    
    public static Connection conectarDB(){
        Connection conexion = null;
        String mensaje;
        
        try{
            Class.forName(DRIVER);
            conexion = DriverManager.getConnection(URL_CONEXION,LOGIN,CLAVE);
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