/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author Hector Alvarez
 */
public class MensajesDeError {
    
    private static String [] codigoDelError = {
        "00000", "01000", "01001", "01002", "01004", "01006", "01S00", "01S01",
        "01S02", "07001", "07002", "07003", "07004", "07005", "07006", "07008",
        "08000", "08001", "08002", "08003", "08004", "08007", "08900", "08S01",
        "21000", "21S01", "21S02", "22000", "22001", "22003", "22007", "22012",
        "22018", "22026", "23000", "25000", "25S02", "25S03", "26000", "28000",
        "34000", "3C000", "40000", "40001", "42000", "42S01", "42S02", "42S11",
        "42S12", "42S21", "42S22", "42S23", "44000", "HY000", "HY001", "HY002",
        "HY003", "HY004", "HY008", "HY009", "HY010", "HY011", "HY012", "HY015",
        "HY018", "HY090", "HY091", "HY092", "HY093", "HY095", "HY096", "HY097",
        "HY098", "HY099", "HY100", "HY101", "HY103", "HY104", "HY105", "HY106",
        "HY107", "HY108", "HY109", "HY110", "HY111", "HYC00", "HYT00", "HZ010",
        "HZ020", "HZ080", "HZ090", "HZ100", "HZ380", "IM001", "IM002", "IM003",
        "IM004", "IM005", "IM006", "IM007", "IM008", "IM009", "IM010", "IM011",
        "IM012", "IM013"};
     private static String [] descripcionDelError = {
          "1Éxito",
          "2Advertencia general",
          "3Conflicto de las operaciones de cursor",
          "4Error en desconexión",
          "5Datos truncados",
          "6Privilegio no revocado",
          "7Conexión no válida atributo de cadena",
          "8Error en la fila",
          "9Opción de cambio de valor",
          "10Número incorrecto de parámetros",
          "11Parámetros no coincidentes",
          "12Especificación del cursor no puede ser ejecutado",
          "13Parámetros que faltan",
          "14Estado del cursor no válido",
          "15Violación de atributos de tipo de datos restringidas",
          "16Cuenta del descriptor no válido",
          "17Excepción en Conexión",
          "18No se puede conectar a fuente de datos, ej.,clave de licencia vál.",
          "19La conexión ya en uso",
          "20La conexión no abre",
          "21Fuente de datos rechazó el establecimiento de la conexión",
          "22Error en la conexión durante una transacción",
          "23La búsqueda del servidor ha fallado",
          "24Enlace de comunicación falló",
          "25Violación de la cardinalidad ",
          "26Lista de valores a insertar no coincide con la lista de columnas",
          "27Grado de tabla derivada no coincide con la lista de columnas",
          "28Datos de excepción",
          "29Los datos de cadena, truncamiento derecho",
          "30Valor numérico fuera de rango",
          "31Formato no válido de fecha y hora",
          "32La división por cero",
          "33Error en la asignación de",
          "34Los datos de cadena, disparidad entre la longitud",
          "35Violación de restricción de integridad",
          "36Estado de la transacción no válida",
          "37Transacción sigue activa",
          "38Transacción se ha retrotraído",
          "39Identificador no válido SQL",
          "40Especificación de la autorización no válido",
          "41Nombre de cursor no válido",
          "42Duplicar el nombre del cursor",
          "43Comprometerse transacción resultó en la transacción de reversión",
          "44El fracaso de serialización, por ejemplo, tiempo de espera o estancamiento",
          "45Error de sintaxis o violación de las reglas de acceso",
          "46Tabla base o punto de vista ya existe",
          "47Base de la tabla o vista que no se encuentra",
          "48El índice ya existe",
          "49Índice de que no se encuentra",
          "50Columna ya existe",
          "51La columna no se encuentra",
          "52Ningún valor por defecto para la columna",
          "53En la violación CHECK OPTION",
          "54Error general",
          "55Error de asignación de almacenamiento",
          "56Número de columna no válido",
          "57De aplicación no válido tipo de búfer",
          "58No válido tipo de datos SQL",
          "59Operación cancelada",
          "60Válido el uso de puntero nulo",
          "61Función de error de secuencia",
          "62Operación no válida en este momento",
          "63Transacción no válida código de operación",
          "64Ningún nombre avilable cursor",
          "65Servidor se negó cancelar la solicitud de",
          "66Cadena no válida o longitud de búfer",
          "67Descriptor de tipos fuera de rango",
          "68Atributo o tipo de opción fuera del alcance",
          "69Número de parámetro no válido",
          "70Función de escribir fuera de rango",
          "71Tipo de información fuera de rango",
          "72Columna Tipo de fuera de rango",
          "73Ámbito de aplicación el tipo fuera de rango",
          "74Tipo Nullable fuera de rango",
          "75Singularidad del tipo de opción fuera de rango",
          "76Opción de Precisión escribir fuera de rango",
          "77Dirección opción fuera de rango",
          "78Valor no válido precisión o escala",
          "79Tipo de parámetro no válido",
          "80Fetch escribir fuera de rango",
          "81valor de la fila fuera de rango",
          "82Concurrencia opción fuera de rango",
          "83Posición del cursor no válido",
          "84La finalización del controlador no válido",
          "85Valor pr defecto no válido",
          "86El driver no pueda",
          "87Tiempo de espera agotado",
          "88Error de RDA: la violación de control de acceso",
          "89Error de RDA: número de repeticiones Bad",
          "90Error de RDA: Recurso no disponible",
          "91Error de RDA: Recursos que ya están abiertos",
          "92Error de RDA: desconocido recursos",
          "93Error de RDA: SQL violación de uso",
          "94El driver no es compatible con esta función",
          "95Nombre de origen de datos no se encuentra y no se especifica ningún driver predeterminado",
          "96Driver especificado no se pudo cargar",
          "97AllocEnv conductor no",
          "98AllocConnect conductor no",
          "99SetConnectOption conductor no",
          "100Ninguna fuente de datos o el driver de diálogo prohibida",
          "101De diálogo no",
          "102No se puede cargar DLL",
          "103Nombre del driver demasiado largo",
          "104Nombre de la fuente de datos muy largo",
          "105La palabra clave DRIVER error de sintaxis",
          "106Traza de error de archivo"};
     
     public static String errorSQL(String codigo){
         
         String descripcion = "Error desconocido";
         
         for (int i=0;i<codigoDelError.length;i++){
             
             if (codigo.equals(codigoDelError[i])){
                 descripcion = descripcionDelError[i];
                 break;
             }
         }
         return descripcion;
     }
}