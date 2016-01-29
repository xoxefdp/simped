package RIF;
/**
 * Validación del RIF (Registro de Información Fiscal).
 * @author Hector Alvarez
 */
public class ValidaRif {
    
    /**
     * Verifica que un numero de RIF con formato NNNNNNNNNV, donde NNNNNNNN
     * representa el numero de RIF asignado y V un dígito validador determinado
     * con el algoritmo del modulo 11 sea correcto o no.
     * @see "http://es.wikipedia.org/wiki/Código_de_control#Cálculo_del_d.C3.ADgito_verificador"
     * @param numero representa el RIF completo a validar en formato NNNNNNNNNV
     * @return false si el numero es incorrecto, true para un numero correcto.
     */
    public static boolean validar(String numero){
        
        try{
            Long.parseLong(numero);
            int largo = numero.length();
            final int CERO = 0,
                      UNO  = 1;
        
            if (largo > CERO){
                int suma = CERO;
                char [] digitos = new char[largo];
                digitos = numero.toCharArray();
                int num = 2;
                for (int i = (largo - 2);i >= CERO;i--){
                    suma = suma + Character.getNumericValue(digitos[i]) * num;
                    num++;
                    if (num > 7){
                        num = 2;
                    }
                }

                int validador = 11 - suma % 11;
                if (validador == 11)
                    validador = CERO;
                else
                    if (validador == 10)
                        validador = UNO;
                if (Character.getNumericValue(digitos[largo-UNO]) != validador)
                    return false;
                else
                    return true;
            }
            else{
                return false;
            }
        }       
        catch(Exception e){
            return false;
        }
    }
}