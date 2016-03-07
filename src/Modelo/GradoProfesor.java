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
 * Clase que establece los metodos referenctes a las consultas relacionales entre las tablas grado y profesor
 * @author josediaz
 */
public class GradoProfesor {
    
    private Statement instruccionSql;
    private Connection conexion;
    private ResultSet resultados;
    private String consulta,mensaje;

    public GradoProfesor(){
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
    */

    // devuelve el profesor asignado, en un grado, en un año
    public final ResultSet consultarGradoFechaProfesor(String aescolar, int codigoGrado){
        try{
            consulta = "SELECT " +
                       "grado.cod_gr," +
                       "grado.grado," +
                       "grado.seccion," +
                       "profesor.cedula_pr," +
                       "profesor.nombre_pr," +
                       "profesor.apellido_pr," +
                       "profesor.fecha_nac_pr," +
                       "profesor.direccion_pr," +
                       "profesor.correo_pr," +
                       "profesor.telefono_pr," +
                       "profesor.sexo_pr," +
                       "profesor.titulo_prof," +
                       "YEAR(grado_has_profesor.a_escolar) " +
                       "FROM grado_has_profesor " +
                       "INNER JOIN grado ON grado_has_profesor.grado_cod_gr = grado.cod_gr " +
                       "INNER JOIN profesor ON grado_has_profesor.profesor_cedula_pr = profesor.cedula_pr " +
                       "WHERE grado_has_profesor.grado_cod_gr = '"+codigoGrado+"' "+
                       "AND YEAR(grado_has_profesor.a_escolar) = '"+aescolar+"'";
            resultados = instruccionSql.executeQuery(consulta); 
        }catch(SQLException error){
            mensaje = errorSQL(error.getSQLState());
            JOptionPane.showMessageDialog(null,mensaje);
	}
        return resultados;
    }
    
    /*
    public final ResultSet consultarTodos(){
        try{
            consulta = "SELECT " +
                       "grado.cod_gr," +
                       "grado.grado," +
                       "grado.seccion," +
                       "profesor.cedula_pr," +
                       "profesor.nombre_pr," +
                       "profesor.apellido_pr," +
                       "profesor.fecha_nac_pr," +
                       "profesor.direccion_pr," +
                       "profesor.correo_pr," +
                       "profesor.telefono_pr," +
                       "profesor.sexo_pr," +
                       "profesor.titulo_prof," +
                       "grado_has_profesor.a_escolar " +
                       "FROM " +
                       "grado_has_profesor " +
                       "INNER JOIN grado ON grado_has_profesor.grado_cod_gr = grado.cod_gr " +
                       "INNER JOIN profesor ON grado_has_profesor.profesor_cedula_pr = profesor.cedula_pr " +
                       "ORDER BY grado.cod_gr ASC";
            resultados = instruccionSql.executeQuery(consulta);
        }catch(SQLException error){
            mensaje = errorSQL(error.getSQLState());
            JOptionPane.showMessageDialog(null,mensaje);
	}
        return resultados;
    }
    */
    
    // devuelve los años que contienen grados y profesores asignados
    public final ResultSet consultarFechas(){
        try{
            consulta="SELECT YEAR(grado_has_profesor.a_escolar) "
                    +"FROM grado_has_profesor "
                    +"INNER JOIN grado "
                    +"ON grado.cod_gr = grado_has_profesor.grado_cod_gr "
                    +"GROUP BY YEAR(grado_has_profesor.a_escolar)";
            resultados = instruccionSql.executeQuery(consulta);
        }catch(SQLException error){
            mensaje = errorSQL(error.getSQLState());
            JOptionPane.showMessageDialog(null,mensaje);
	}
        return resultados;
    }

    public final boolean incluir(int codigoGrado, int cedulaProfesor, String aescolar){
        boolean inserccionOk = false;
        try{
            consulta="INSERT INTO grado_has_profesor SET "
                    +"grado_cod_gr = '"+codigoGrado+"', "
                    +"profesor_cedula_pr = '"+cedulaProfesor+"', "
                    +"a_escolar = '"+aescolar+"'";
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
    public final boolean eliminar(int codigoGrado, int cedulaProfesor, String aescolar){
        boolean inserccionOk = false;
        try{  
            consulta="DELETE FROM grado_has_profesor WHERE grado_cod_gr='"+codigoGrado+"' "
                    +"AND profesor_cedula_pr='"+cedulaProfesor+"' "
                    +"AND a_escolar='"+aescolar+"'";
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

    public final boolean modificar(int codigoGrado, int cedulaProfesor, int codigoGradoViejo, int cedulaProfesorVieja, String aescolar){
        boolean inserccionOk = false;
        try{
            consulta="UPDATE grado_has_profesor SET "
                    +"grado_cod_gr = '"+codigoGrado+"', "
                    +"profesor_cedula_pr = '"+cedulaProfesor+"' "
                    +"WHERE a_escolar = '"+aescolar+"' "
                    +"AND alumno_cod_al = '"+codigoGradoViejo+"' "
                    +"AND representante_cedula_rp = '"+cedulaProfesorVieja+"'";
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