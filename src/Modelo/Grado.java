/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import static Modelo.MensajesDeError.errorSQL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author josediaz
 */
public class Grado {
    
    private Statement  instruccionSql;
    private Connection conexion;
    private ResultSet  resultados;
    private String consulta,mensaje;
    
    public Grado(){
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
    
    public final ResultSet consultarGrado(int codigo){
        try{
            consulta = "SELECT * FROM grado WHERE cod_gr = '"+codigo+"'";
            resultados = instruccionSql.executeQuery(consulta); 
        }catch(SQLException error){
            mensaje = errorSQL(error.getSQLState());
            JOptionPane.showMessageDialog(null,mensaje);
	}
        return resultados;
    }
    
    public final ResultSet consultarGrados(){
        try{
            consulta = "SELECT * FROM grado ORDER BY grado ASC";
            
            resultados = instruccionSql.executeQuery(consulta); 
        }catch(SQLException error){
            mensaje = errorSQL(error.getSQLState());
            JOptionPane.showMessageDialog(null,mensaje);
	}
        return resultados;
    }

    public final boolean incluir(String grado, String seccion){
        boolean inserccionOk = false;
        try{
            consulta="INSERT INTO grado SET "
                    +"grado = '"+grado+"', "
                    +"seccion = '"+seccion+"'";
            
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

    public final boolean eliminar(int codigo, String seccion){
        boolean inserccionOk = false;
        try{  
            consulta="DELETE FROM grado WHERE cod_gr = '" +codigo+"'";
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
    public final boolean modificar(String grado ){
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
                    +"WHERE alumno_cod_al = '"+codigo+"'";
            
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
*/
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
