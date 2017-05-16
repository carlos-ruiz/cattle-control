package com.cruiz90.controldeganado.converters;

import org.greenrobot.greendao.converter.PropertyConverter;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Carlos on 21/02/2017.
 */

public class ListConverter implements PropertyConverter<List<String>, String> {
    @Override
    public List<String> convertToEntityProperty(String databaseValue) {
        if (databaseValue == null) {
            return null;
        }
        else {
            List lista = Arrays.asList(databaseValue.split(","));
            return lista;
        }
    }

    @Override
    public String convertToDatabaseValue(List<String> entityProperty) {
        if(entityProperty==null){
            return null;
        }
        else{
            StringBuilder sb= new StringBuilder();
            for(String link:entityProperty){
                sb.append(link);
                sb.append(",");
            }
            return sb.toString();
        }
    }
}
