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
public class Profesor {
    
    private Statement  instruccionSql;
    private Connection conexion;
    private ResultSet  resultados;
    private String consulta, mensaje;

    public Profesor(){
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
    public final ResultSet consultarProfesor(int cedulaProfesor){
        try{
            consulta = "SELECT * FROM profesor WHERE cedula_pr = '"+cedulaProfesor+"';";
            resultados = instruccionSql.executeQuery(consulta); 
        }catch(SQLException error){
            mensaje = errorSQL(error.getSQLState());
            JOptionPane.showMessageDialog(null,mensaje);
	}
        return resultados;
    }

    public final ResultSet consultarProfesores(){
        try{
            consulta = "SELECT * FROM profesor ORDER BY cedula_pr ASC";
            resultados = instruccionSql.executeQuery(consulta);
        }catch(SQLException error){
            mensaje = errorSQL(error.getSQLState());
            JOptionPane.showMessageDialog(null,mensaje);
	}
        return resultados;
    }
    
    public final boolean incluir(int cedula, String nombre, String apellido, 
                                String fechaNacimiento, String direccion, String telefono,
                                String correo, String titulo, String sexo){
        boolean inserccionOk = false;
        try{
            consulta="INSERT INTO profesor SET "
                    +"cedula_pr = '"+cedula+"', "
                    +"nombre_pr = '"+nombre+"', "
                    +"apellido_pr = '"+apellido+"', "
                    +"fecha_nac_pr = '"+fechaNacimiento+"', "
                    +"direccion_pr = '"+direccion+"', "
                    +"telefono_pr = '"+telefono+"', "
                    +"correo_pr = '"+correo+"', "
                    +"titulo_prof = '"+titulo+"', "
                    +"sexo_pr = '"+sexo+"';";
            
            instruccionSql.executeUpdate(consulta);
            inserccionOk = true;
        }catch(SQLException error){
            mensaje = errorSQL(error.getSQLState());
            JOptionPane.showMessageDialog(null,mensaje);
	}
        return inserccionOk;
    }

    public final boolean eliminar(int cedula){
        boolean inserccionOk = false;
        try{  
            consulta="DELETE FROM profesor WHERE cedula_pr = '" +cedula+"';";
            instruccionSql.executeUpdate(consulta);
            inserccionOk = true;
        }catch(SQLException error){
            mensaje = errorSQL(error.getSQLState());
            JOptionPane.showMessageDialog(null,mensaje);
	}
        return inserccionOk;
    }

    public final boolean modificar(int cedulaProf, String nombre, String apellido, 
                                   String fechaNacimiento, String direccion, String telefono,
                                   String correo, String titulo, String sexo){
        boolean inserccionOk = false;
        try{  
            consulta="UPDATE profesor SET "
                    +"nombre_pr = '"+nombre+"', "
                    +"apellido_pr = '"+apellido+"', "
                    +"fecha_nac_pr = '"+fechaNacimiento+"', "
                    +"direccion_pr = '"+direccion+"', "
                    +"telefono_pr = '"+telefono+"', "
                    +"correo_pr = '"+correo+"', "
                    +"titulo_prof = '"+titulo+"', "
                    +"sexo_pr = '"+sexo+"' "
                    +"WHERE cedula_pr = '"+cedulaProf+"';";
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
            mensaje = errorSQL(error.getSQLState());
            JOptionPane.showMessageDialog(null,mensaje);
	}
    }
}