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
    private String consulta,consulta2,consulta3, mensaje;

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
    
    public final ResultSet consultarAlumnoRepresentante(int codigo){
        try{
            //consulta = "SELECT * FROM alumno WHERE cod_al = '"+codigo+"';";
            consulta = "SELECT " +
                        "alumno.cod_al," +
                        "alumno.nombre_al," +
                        "alumno.apellido_al," +
                        "alumno.fecha_nac_alum," +
                        "alumno.sexo," +
                        "representante_has_alumno.parentesco," +
                        "representante.cedula_rp " +
                        "FROM " +
                        "alumno " +
                        "INNER JOIN representante_has_alumno ON representante_has_alumno.alumno_cod_al = alumno.cod_al " +
                        "INNER JOIN representante ON representante_has_alumno.representante_cedula_rp = representante.cedula_rp " +
                        "WHERE " +
                        "representante_has_alumno.alumno_cod_al = '"+codigo+"';";
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
    
    public final ResultSet consultarAlumnosRepresentantes(){
        try{
            consulta = "SELECT " +
                        "alumno.cod_al," +
                        "alumno.nombre_al," +
                        "alumno.apellido_al," +
                        "alumno.fecha_nac_alum," +
                        "alumno.sexo," +
                        "representante_has_alumno.parentesco," +
                        "representante.cedula_rp " +
                        "FROM " +
                        "alumno " +
                        "INNER JOIN representante_has_alumno ON representante_has_alumno.alumno_cod_al = alumno.cod_al " +
                        "INNER JOIN representante ON representante_has_alumno.representante_cedula_rp = representante.cedula_rp " +
                        "ORDER BY " +
                        "alumno.cod_al ASC";
            resultados = instruccionSql.executeQuery(consulta);
        }catch(SQLException error){
            mensaje = errorSQL(error.getSQLState());
            JOptionPane.showMessageDialog(null,mensaje);
            System.exit(200);
	}
        return resultados;
    }
    
    public final boolean incluir(String nombre, String apellido, String fechaNacimiento, 
                                 String sexo, int cedulaRepresentante, String parentesco){
        boolean inserccionOk = false;
        try{    // inserto alumno
            consulta="INSERT INTO alumno SET "
                    +"nombre_al = '"+nombre+"', "
                    +"apellido_al = '"+apellido+"', "
                    +"fecha_nac_alum = '"+fechaNacimiento+"', "
                    +"sexo = '"+sexo+"';";
                // inserto en tabla nm ultimo registro
            consulta2="INSERT INTO representante_has_alumno SET "
                    +"representante_cedula_rp = '"+cedulaRepresentante+"', "
                    +"alumno_cod_al = LAST_INSERT_ID(), "
                    +"parentesco = '"+parentesco+"';";
            instruccionSql.executeUpdate("START TRANSACTION");
            instruccionSql.executeUpdate(consulta);
            instruccionSql.executeUpdate(consulta2);
            instruccionSql.executeUpdate("COMMIT;");
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
                                 String sexo, int cedulaRepresentante, String parentesco){
        boolean inserccionOk = false;
        try{  
            consulta="UPDATE alumno SET "
                    +"nombre_al = '"+nombre+"', "
                    +"apellido_al = '"+apellido+"', "
                    +"fecha_nac_alum = '"+fechaNacimiento+"', "
                    +"sexo = '"+sexo+"' "
                    +"WHERE cod_al = '"+codigo+"';";
            
            consulta2="UPDATE representante_has_alumno SET "
                    +"representante_cedula_rp = '"+cedulaRepresentante+"', "
                    +"parentesco = '"+parentesco+"' "
                    +"WHERE alumno_cod_al = '"+codigo+"';";
            
            instruccionSql.executeUpdate("START TRANSACTION");
            instruccionSql.executeUpdate(consulta);
            instruccionSql.executeUpdate(consulta2);
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