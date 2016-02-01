/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.*;
import javax.swing.JOptionPane;
import static Modelo.MensajesDeError.errorSQL;

/**
 *
 * @author josediaz
 */
public class Representante {
    
    private Statement  instruccionSql;
    private Connection conexion;
    private ResultSet  resultados;
    private String consulta, mensaje;

    public Representante(){
        conexion = ConexionDB.conectarDB();
        if (conexion == null)
            System.exit(1000);
        else{
            try{
                instruccionSql = conexion.createStatement();
            }
            catch(SQLException error){
                mensaje = errorSQL(error.getSQLState());
                JOptionPane.showMessageDialog(null, mensaje);
            }
        } 
    }
    public final ResultSet consultarRepresentante(int cedula){
        try{
            consulta = "SELECT * FROM representante WHERE cedula_rp = '"+cedula+"';";
            resultados = instruccionSql.executeQuery(consulta); 
        }catch(SQLException error){
            mensaje = errorSQL(error.getSQLState());
            JOptionPane.showMessageDialog(null,mensaje);
            System.exit(200);
	}
        return resultados;
    }

    public final ResultSet consultarRepresentantes(){
        try{
            consulta = "SELECT * FROM representante ORDER BY nombre_rp ASC";
            resultados = instruccionSql.executeQuery(consulta);
        }catch(SQLException error){
            mensaje = errorSQL(error.getSQLState());
            JOptionPane.showMessageDialog(null,mensaje);
            System.exit(200);
	}
        return resultados;
    }
    
    public final boolean incluir(int cedula, String nombre, String apellido,
                                String telefono, String direccion, String correo,
                                String parentesco, String fechaNacimiento, String sexo){
        boolean inserccionOk = false;
        try{
            consulta="INSERT INTO persona SET "
                    +"cedula_rp = '"+cedula+"', "
                    +"nombre_rp = '"+nombre+"', "
                    +"apellido_rp = '"+apellido+"', "
                    +"telefono_rp = '"+telefono+"', "
                    +"direccion_rp = '"+direccion+"', "
                    +"correo_rp = '"+correo+"', "
                    +"parentesco = '"+parentesco+"', "
                    +"fecha_nac_rp = '"+fechaNacimiento+"', "
                    +"sexo_rp = '"+sexo+"';";
            
            /*
            consulta="INSERT INTO representante ("
            + "cedula_rp, nombre_rp, apellido_rp, telefono_rp, direccion_rp, correo_rp,"
            + " parentesco, fecha_nac_rp, sexo_rp) VALUES ('"+cedula+"', '"+nombre+"', '"+apellido+"', '"
            +telefono+"', '"+direccion+"', '"+correo+"', '"+parentesco+"', '"+fechaNacimiento+"', '"
            +sexo+"');";
            */
            
            instruccionSql.executeUpdate(consulta);
            inserccionOk = true;
        }catch(SQLException error){
            mensaje = errorSQL(error.getSQLState());
            JOptionPane.showMessageDialog(null,mensaje);
	}
        return inserccionOk;
    }

    public final boolean eliminar(String eliminarCedula){
        boolean inserccionOk = false;
        try{  
            consulta="DELETE FROM representante WHERE cedule_rp = '" +eliminarCedula+"';";
            instruccionSql.executeUpdate(consulta);
            inserccionOk = true;
        }catch(SQLException error){
            mensaje = errorSQL(error.getSQLState());
            JOptionPane.showMessageDialog(null,mensaje);
	}
        return inserccionOk;
    }

    public final boolean modificar(int cedula, String nombre, String apellido,
                                   String telefono, String direccion, String correo,
                                   String parentesco, String fechaNacimiento, String sexo){
        boolean inserccionOk = false;
        try{  
            consulta="UPDATE representante SET "
                    +"nombre_rp = '"+nombre+"' ,"
                    +"apellido_rp = '"+apellido+"' ,"
                    +"telefono_rp = '"+telefono+"' ,"
                    +"direccion_rp = '"+direccion+"' ,"
                    +"correo_rp = '"+correo+"' ,"
                    +"parentesco = '"+parentesco+"' ,"
                    +"fecha_nac_rp = '"+fechaNacimiento+"' ,"
                    +"sexo_rp = '"+sexo+"' ,"
                    +"where cedula_rp = "+cedula+";";
            instruccionSql.executeUpdate("START TRANSACTION");
            instruccionSql.executeUpdate(consulta);
            instruccionSql.executeUpdate("COMMIT;");
            inserccionOk = true;
        }catch(SQLException error){
            mensaje = error.getSQLState();
            JOptionPane.showMessageDialog(null,mensaje);
	}
        return inserccionOk;
    }

    public void cerrarConexion(){
        try{  
            instruccionSql.close();
            conexion.close();
        }catch(SQLException error){
            JOptionPane.showMessageDialog(null,"Error en conexion. \n"+error);
            System.exit(200);
	}
    }
}