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
public class Alumno {
    
    private Statement  instruccionSql;
    private Connection conexion;
    private ResultSet  resultados;
    private String consulta, mensaje;

    public Alumno(){
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
    public final ResultSet consultarAlumno(int codigo){
        try{
            consulta = "SELECT * FROM alumno WHERE cod_al = '"+codigo+"';";
            resultados = instruccionSql.executeQuery(consulta); 
        }catch(SQLException error){
            mensaje = errorSQL(error.getSQLState());
            JOptionPane.showMessageDialog(null,mensaje);
            System.exit(200);
	}
        return resultados;
    }

    public final ResultSet consultarAlumnos(){
        try{
            consulta = "SELECT * FROM alumno ORDER BY cod_al ASC";
            resultados = instruccionSql.executeQuery(consulta);
        }catch(SQLException error){
            mensaje = errorSQL(error.getSQLState());
            JOptionPane.showMessageDialog(null,mensaje);
            System.exit(200);
	}
        return resultados;
    }
    
    public final boolean incluir(String nombre, String apellido, String fechaNacimiento, 
                                 String sexo, int cedulaRepresentante){
        boolean inserccionOk = false;
        try{
            consulta="INSERT INTO alumno SET "
                    +"nombre_al = '"+nombre+"', "
                    +"apellido_al = '"+apellido+"', "
                    +"fecha_nac_alum = '"+fechaNacimiento+"', "
                    +"representante_cedula_rp = '"+cedulaRepresentante+"', "
                    +"sexo = '"+sexo+"';";
            
            instruccionSql.executeUpdate(consulta);
            inserccionOk = true;
        }catch(SQLException error){
            mensaje = errorSQL(error.getSQLState());
            JOptionPane.showMessageDialog(null,mensaje);
	}
        return inserccionOk;
    }

    public final boolean eliminar(int codigo){
        boolean inserccionOk = false;
        try{  
            consulta="DELETE FROM alumno WHERE cod_al = '" +codigo+"';";
            instruccionSql.executeUpdate(consulta);
            inserccionOk = true;
        }catch(SQLException error){
            mensaje = errorSQL(error.getSQLState());
            JOptionPane.showMessageDialog(null,mensaje);
	}
        return inserccionOk;
    }

    public final boolean modificar(int codigo, String nombre, String apellido, String fechaNacimiento, 
                                 String sexo, int cedulaRepresentante){
        boolean inserccionOk = false;
        try{  
            consulta="UPDATE alumno SET "
                    +"nombre_al = '"+nombre+"', "
                    +"apellido_al = '"+apellido+"', "
                    +"fecha_nac_alum = '"+fechaNacimiento+"', "
                    +"sexo = '"+sexo+"', "
                    +"representante_cedula_rp = '"+cedulaRepresentante+"', "
                    +"WHERE cod_al = '"+codigo+"';";
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