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
 * Clase que establece los metodos referenctes a las consultas relacionales entre las tablas alumno y grado
 * @author josediaz
 */
public class AlumnoGrado {
    
    private Statement instruccionSql;
    private Connection conexion;
    private ResultSet resultados;
    private String consulta,mensaje;

    public AlumnoGrado(){
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
    
    // devuelve el alumno inscrito, en un grado, en un año escolar
    public final ResultSet consultarAlumnoGradoFecha(String aescolar, int codigoGrado, int codigoAlumno){
        try{
            consulta = "SELECT " +
                       "alumno.cod_al," +
                       "alumno.nombre_al," +
                       "alumno.apellido_al," +
                       "alumno.fecha_nac_alum," +
                       "alumno.sexo," +
                       "YEAR(alumno_has_grado.a_escolar)," +
                       "grado.cod_gr," +
                       "grado.grado," +
                       "grado.seccion " +
                       "FROM alumno " +
                       "INNER JOIN alumno_has_grado ON alumno_has_grado.alumno_cod_al = alumno.cod_al " +
                       "INNER JOIN grado ON alumno_has_grado.grado_cod_gr = grado.cod_gr " +
                       "WHERE YEAR(alumno_has_grado.a_escolar) = '"+aescolar+"' " +
                       "AND alumno_has_grado.grado_cod_gr = '"+codigoGrado+"' " +
                       "AND alumno_has_grado.alumno_cod_al = '"+codigoAlumno+"'";
            resultados = instruccionSql.executeQuery(consulta); 
        }catch(SQLException error){
            mensaje = errorSQL(error.getSQLState());
            JOptionPane.showMessageDialog(null,mensaje);
	}
        return resultados;
    }

    // devuelve todos los alumnos inscritos en un grado, en un año escolar
    public final ResultSet consultarListaAlumnosGradoFecha(String aescolar, String codigoGrado){
        try{
            int codigo = Integer.parseInt(codigoGrado);
            consulta = "SELECT " +
                       "YEAR(alumno_has_grado.a_escolar)," +
                       "alumno.cod_al," +
                       "alumno.nombre_al," +
                       "alumno.apellido_al," +
                       "alumno.fecha_nac_alum," +
                       "alumno.sexo," +
                       "grado.cod_gr," +
                       "grado.grado," +
                       "grado.seccion " +
                       "FROM alumno " +
                       "INNER JOIN alumno_has_grado ON alumno_has_grado.alumno_cod_al = alumno.cod_al " +
                       "INNER JOIN grado ON alumno_has_grado.grado_cod_gr = grado.cod_gr " +
                       "WHERE YEAR(alumno_has_grado.a_escolar) = '"+aescolar+"' " +
                       "AND alumno_has_grado.grado_cod_gr = '"+codigo+"'";
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
                       "grado.cod_gr," +
                       "grado.grado," +
                       "grado.seccion," +
                       "alumno_has_grado.a_escolar " +
                       "FROM alumno " +
                       "INNER JOIN alumno_has_grado ON alumno_has_grado.alumno_cod_al = alumno.cod_al " +
                       "INNER JOIN grado ON alumno_has_grado.grado_cod_gr = grado.cod_gr " +
                       "ORDER BY alumno.cod_al ASC";
            resultados = instruccionSql.executeQuery(consulta);
        }catch(SQLException error){
            mensaje = errorSQL(error.getSQLState());
            JOptionPane.showMessageDialog(null,mensaje);
	}
        return resultados;
    }
    
    // devuelve los años que contienen grados y alumnos asignados
    public final ResultSet consultarFechas(){
        try{
            consulta="SELECT YEAR(alumno_has_grado.a_escolar) "
                    +"FROM alumno_has_grado "
                    +"INNER JOIN grado "
                    +"ON grado.cod_gr = alumno_has_grado.grado_cod_gr "
                    +"GROUP BY YEAR(alumno_has_grado.a_escolar)";
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