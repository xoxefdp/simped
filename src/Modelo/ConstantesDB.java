package Modelo;

public interface ConstantesDB{
    
    public final String BASE_DE_DATOS = "simped",
                        LOGIN = "root",
                        CLAVE = "mysqlroot",
                        URL_DEL_SERVIDOR = "localhost",
                        DRIVER = "com.mysql.jdbc.Driver",
                        URL_CONEXION = "jdbc:mysql://" + URL_DEL_SERVIDOR +
                                       "/" + BASE_DE_DATOS;
}