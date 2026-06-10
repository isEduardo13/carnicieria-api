package com.isidro.carniceria_api.utils;

public class StringUtils {
    public static  void ValidateNoEmpty(String texto , String mensaje){
        if (texto== null || texto.isBlank()){
            throw new IllegalArgumentException(mensaje);
        }
    }
    public static  void validateSize(String texto , Integer min ,Integer max,String mensaje){
        ValidateNoEmpty(texto, mensaje);

        if( texto.length() < min || texto.length() > max ){
            throw new IllegalArgumentException(mensaje);
        }
    }

    public static String removeAccents(String texto){
        return texto.toLowerCase()
                .replace("á","a").replace("é", "e")
                .replace('í', 'i').replace('ú', 'u')
                .replace('ó','o').replace('ü', 'u');

    }


}
