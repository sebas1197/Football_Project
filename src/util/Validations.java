/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

/**
 *
 * @author omard
 */
public class Validations {
    public static boolean isNumeric(String cadena){
       try {
               Integer.parseInt(cadena);
               return true;
       } catch (NumberFormatException nfe){
               return false;
       }
    }
    
    public static boolean isNumericPositive(String cadena){
       try {
            int num=Integer.parseInt(cadena);
               if(num>=0)
                    return true;
               else 
                   return false;
       } catch (NumberFormatException nfe){
               return false;
       }
    }
    public static boolean validateString(String x) {
        if(!x.matches("^[A-Za-z ]*$"))
            return false;
        else
            return true;

	}
}
