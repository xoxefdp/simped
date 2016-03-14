/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author josediaz
 */
public class ValidadorCorreo {
    private final Pattern pattern;
    private Matcher matcher;
    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    
    public ValidadorCorreo(){
        pattern = Pattern.compile(EMAIL_PATTERN);
    }
    
    /**
     * @param correo
     * @return true si el correo es valido, false si es invalido
     */
    public boolean validarCorreo(String correo) {
	matcher = pattern.matcher(correo);
	return matcher.matches();
    }
}