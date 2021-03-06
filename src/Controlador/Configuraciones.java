/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * 
 * @author josediaz
 */
public class Configuraciones {
    Properties configuraciones;
    private final String rutaConfiguraciones="config.properties";
    // Variables de configuracion para la base de datos
    private String BASE_DE_DATOS,USUARIO_BASE_DE_DATOS,CLAVE_USUARIO,URL_DEL_SERVIDOR,DRIVER,URL_CONEXION;
    // Variable de configuracion para la emision de reportes
    private String RUTA_REPORTES,RUTA_LOGO_REPORTES;
    // 
    private String RUTA_FONDO, RUTA_MANUAL;

    public Configuraciones(){
        configuraciones = new Properties();
    }
    
    /************METODOS DE CONFIGURACION PARA LA BASE DE DATOS****************/    
    /**
     * Retorna el nombre de la base de datos
     * @return 
     */
    public String getDBName(){
        String DBName = null;
        try {
            configuraciones.load(new FileInputStream(rutaConfiguraciones));
            BASE_DE_DATOS = configuraciones.getProperty("BASE_DE_DATOS"); 
            DBName=BASE_DE_DATOS;
        } catch (FileNotFoundException e) {
            System.out.println("Error 10, No existe el archivo");
        } catch (IOException e) {
            System.out.println("Error 10, No se puede leer el archivo");
        }
       return DBName;
    }  
    /**
     * Retorna el usuario de la base de datos
     * @return 
     */ 
    public String getUserName(){
        String UserName = null;
        try {
            configuraciones.load(new FileInputStream(rutaConfiguraciones));
            USUARIO_BASE_DE_DATOS = configuraciones.getProperty("USUARIO_BASE_DE_DATOS"); 
            UserName=USUARIO_BASE_DE_DATOS;
        } catch (FileNotFoundException e) {
            System.out.println("Error 20, No existe el archivo");
        } catch (IOException e) {
            System.out.println("Error 20, No se puede leer el archivo");
        }
        return UserName;
    }
    /**
     * Retorna la contraseña del usuario
     * @return 
     */ 
    public String getDBPassWord(){
        String PassWord = null;
        try {
            configuraciones.load(new FileInputStream(rutaConfiguraciones));
            CLAVE_USUARIO = configuraciones.getProperty("CLAVE_USUARIO"); 
            PassWord=CLAVE_USUARIO;
        } catch (FileNotFoundException e) {
            System.out.println("Error 30, No existe el archivo");
        } catch (IOException e) {
            System.out.println("Error 30, No se puede leer el archivo");
        }
        return PassWord;
    }
    /**
     * Retorna la url del servidor de base de datos
     * @return 
     */    
    public String getServerURL(){
        String ServerURL = null;
        try {
            configuraciones.load(new FileInputStream(rutaConfiguraciones));
            URL_DEL_SERVIDOR = configuraciones.getProperty("URL_DEL_SERVIDOR"); 
            ServerURL=URL_DEL_SERVIDOR;
        } catch (FileNotFoundException e) {
            System.out.println("Error 40, No existe el archivo");
        } catch (IOException e) {
            System.out.println("Error 40, No se puede leer el archivo");
        }
        return ServerURL;
    }
    /**
     * Retorna el controlador a utilizar para establecer la conexion con la base de datos
     * @return 
     */ 
    public String getDBController(){
        String Controller = null;
        try {
            configuraciones.load(new FileInputStream(rutaConfiguraciones));
            DRIVER = configuraciones.getProperty("DRIVER"); 
            Controller=DRIVER;
        } catch (FileNotFoundException e) {
            System.out.println("Error 50, No existe el archivo");
        } catch (IOException e) {
            System.out.println("Error 50, No se puede leer el archivo");
        }
        return Controller;
    }
    /**
     * Retorna el enlace de conexion con la base de datos
     * @return 
     */ 
    public String getConnectionURL(){
        String ConnectionURL = null;
        try {
            configuraciones.load(new FileInputStream(rutaConfiguraciones));
            URL_CONEXION = configuraciones.getProperty("URL_CONEXION"); 
            ConnectionURL=URL_CONEXION + getServerURL() +"/" + getDBName();
        } catch (FileNotFoundException e) {
            System.out.println("Error 60, No existe el archivo");
        } catch (IOException e) {
            System.out.println("Error 60, No se puede leer el archivo");
        }
        return ConnectionURL;
    }
    /**************************************************************************/
    
    /**
     * Retorna la ruta de archivos para la emisión de reportes
     * @return 
     */
    public String getReportRoute(){
        String ReportRoute = null;
        try {
            configuraciones.load(new FileInputStream(rutaConfiguraciones));
            RUTA_REPORTES = configuraciones.getProperty("RUTA_REPORTES"); 
            ReportRoute=RUTA_REPORTES;
        } catch (FileNotFoundException e) {
            System.out.println("Error 70, No existe el archivo");
        } catch (IOException e) {
            System.out.println("Error 70, No se puede leer el archivo");
        }
       return ReportRoute;
    }
    
    /**
     * Retorna la ruta de archivos para la emisión de reportes
     * @return 
     */
    public String getReportLogoRoute(){
        String ReportLogoRoute = null;
        try {
            configuraciones.load(new FileInputStream(rutaConfiguraciones));
            RUTA_LOGO_REPORTES = configuraciones.getProperty("RUTA_LOGO_REPORTES"); 
            ReportLogoRoute=RUTA_LOGO_REPORTES;
        } catch (FileNotFoundException e) {
            System.out.println("Error 80, No existe el archivo");
        } catch (IOException e) {
            System.out.println("Error 80, No se puede leer el archivo");
        }
       return ReportLogoRoute;
    }
    
    public String getBackgroundApp(){
        String BackgroundApp = null;
        try {
            configuraciones.load(new FileInputStream(rutaConfiguraciones));
            RUTA_FONDO = configuraciones.getProperty("RUTA_FONDO");
            BackgroundApp=RUTA_FONDO;
        } catch (FileNotFoundException e) {
            System.out.println("Error 90, No existe el archivo");
        } catch (IOException e) {
            System.out.println("Error 90, No se puede leer el archivo");
        }
       return BackgroundApp;
    }

    public String getManual(){
        String BackgroundApp = null;
        try {
            configuraciones.load(new FileInputStream(rutaConfiguraciones));
            RUTA_MANUAL = configuraciones.getProperty("RUTA_MANUAL");
            BackgroundApp=RUTA_MANUAL;
        } catch (FileNotFoundException e) {
            System.out.println("Error 100, No existe el archivo");
        } catch (IOException e) {
            System.out.println("Error 100, No se puede leer el archivo");
        }
       return BackgroundApp;
    }
}