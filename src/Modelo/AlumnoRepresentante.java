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
 * Clase que establece los metodos referenctes a las consultas relacionales entre las tablas alumno y representante
 * @author josediaz
 */
public class AlumnoRepresentante {
    
    private Statement instruccionSql;
    private Connection conexion;
    private ResultSet resultados;
    private String consulta,mensaje;

    public AlumnoRepresentante(){
        conexion = ConexionDB.conectarDB();
        if (conexion == null)
            System.exit(1000);
        else{
            try{
                instruccionSql = conexion.createStatement();
            }
            catch(SQLException error){
                mensaje = errorSQL(error.getSQLState());
                JOptionPane.showMessageDialog(null,mensaje);
            }
        }
    }
    /*
    public final ResultSet consultarAlumnoRepresentantes(int codigo){
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
                       "WHERE " +
                       "representante_has_alumno.alumno_cod_al = '"+codigo+"'";
            resultados = instruccionSql.executeQuery(consulta); 
        }catch(SQLException error){
            mensaje = errorSQL(error.getSQLState());
            JOptionPane.showMessageDialog(null,mensaje);
	}
        return resultados;
    }
    */
    public final ResultSet consultarRepresentanteAlumnos(int cedulaRepresentante){
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
                       "WHERE " +
                       "representante_has_alumno.representante_cedula_rp = '"+cedulaRepresentante+"'";
            resultados = instruccionSql.executeQuery(consulta); 
        }catch(SQLException error){
            mensaje = errorSQL(error.getSQLState());
            JOptionPane.showMessageDialog(null,mensaje);
	}
        return resultados;
    }
    
    public final ResultSet consultarAlumnoRepresentante(int codigo, int cedulaRepresentante){
        try{
            consulta = "SELECT " +
                       "alumno.cod_al," +
                       "alumno.nombre_al," +
                       "alumno.apellido_al," +
                       "alumno.fecha_nac_alum," +
                       "alumno.sexo," +
                       "representante_has_alumno.parentesco," +
                       "representante.cedula_rp " +
                       "FROM alumno " +
                       "INNER JOIN representante_has_alumno ON representante_has_alumno.alumno_cod_al = alumno.cod_al " +
                       "INNER JOIN representante ON representante_has_alumno.representante_cedula_rp = representante.cedula_rp " +
                       "WHERE representante_has_alumno.representante_cedula_rp = '"+cedulaRepresentante+"' "+
                       "AND representante_has_alumno.alumno_cod_al = '"+codigo+"'";
            resultados = instruccionSql.executeQuery(consulta); 
        }catch(SQLException error){
            mensaje = errorSQL(error.getSQLState());
            JOptionPane.showMessageDialog(null,mensaje);
	}
        return resultados;
    }
    
    public final ResultSet consultarTodos(){
        try{
            consulta = "SELECT " +
                       "alumno.cod_al," +
                       "alumno.nombre_al," +
                       "alumno.apellido_al," +
                       "alumno.fecha_nac_alum," +
                       "alumno.sexo," +
                       "representante_has_alumno.parentesco," +
                       "representante.cedula_rp " +
                       "FROM alumno " +
                       "INNER JOIN representante_has_alumno ON representante_has_alumno.alumno_cod_al = alumno.cod_al " +
                       "INNER JOIN representante ON representante_has_alumno.representante_cedula_rp = representante.cedula_rp " +
                       "ORDER BY alumno.cod_al ASC";
            resultados = instruccionSql.executeQuery(consulta);
        }catch(SQLException error){
            mensaje = errorSQL(error.getSQLState());
            JOptionPane.showMessageDialog(null,mensaje);
	}
        return resultados;
    }

    public final boolean incluir(int codigo, int cedulaRepresentante, String parentesco){
        boolean inserccionOk = false;
        try{
            consulta="INSERT INTO representante_has_alumno SET "
                    +"representante_cedula_rp = '"+cedulaRepresentante+"', "
                    +"alumno_cod_al = '"+codigo+"', "
                    +"parentesco = '"+parentesco+"'";
            instruccionSql.executeUpdate("START TRANSACTION");
            instruccionSql.executeUpdate(consulta);
            instruccionSql.executeUpdate("COMMIT;");
            inserccionOk = true;
        }catch(SQLException error){
            mensaje = errorSQL(error.getSQLState());
            JOptionPane.showMessageDialog(null,mensaje);
	}
        return inserccionOk;
    }
    /*
    public final boolean eliminarAlumnoRepresentantes(int codigo){
        boolean inserccionOk = false;
        try{  
            consulta="DELETE FROM representante_has_alumno WHERE alumno_cod_al = '"+codigo+"'";
            instruccionSql.executeUpdate(consulta);
            inserccionOk = true;
        }catch(SQLException error){
            mensaje = errorSQL(error.getSQLState());
            JOptionPane.showMessageDialog(null,mensaje);
	}
        return inserccionOk;
    }
    
    public final boolean eliminarRepresentanteAlumnos(int cedulaRepresentante){
        boolean inserccionOk = false;
        try{
            consulta="DELETE FROM representante_has_alumno WHERE representante_cedula_rep = '"+cedulaRepresentante+"'";
            instruccionSql.executeUpdate(consulta);
            inserccionOk = true;
        }catch(SQLException error){
            mensaje = errorSQL(error.getSQLState());
            JOptionPane.showMessageDialog(null,mensaje);
	}
        return inserccionOk;
    }
    */
    public final boolean eliminar(int codigo, int cedulaRepresentante){
        boolean inserccionOk = false;
        try{  
            consulta="DELETE FROM representante_has_alumno WHERE alumno_cod_al = '"+codigo+"' "
                    +"AND representante_cedula_rep = '"+cedulaRepresentante+"'";
            instruccionSql.executeUpdate(consulta);
            inserccionOk = true;
        }catch(SQLException error){
            mensaje = errorSQL(error.getSQLState());
            JOptionPane.showMessageDialog(null,mensaje);
	}
        return inserccionOk;
    }

    public final boolean modificar(int codigo, int cedulaRepresentante, int codigoViejo, int cedulaRepresentanteVieja, String parentesco){
        boolean inserccionOk = false;
        try{
            consulta="UPDATE representante_has_alumno SET "
                    +"alumno_cod_al = '"+codigo+"', "
                    +"representante_cedula_rp = '"+cedulaRepresentante+"', "
                    +"parentesco = '"+parentesco+"' "
                    +"WHERE alumno_cod_al = '"+codigoViejo+"' "
                    +"AND representante_cedula_rp = '"+cedulaRepresentanteVieja+"'";
            
            instruccionSql.executeUpdate("START TRANSACTION");
            instruccionSql.executeUpdate(consulta);
            instruccionSql.executeUpdate("COMMIT;");
            inserccionOk = true;
        }catch(SQLException error){
            mensaje = errorSQL(error.getSQLState());
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