package com.cruiz90.controldeganado.converters;

import org.greenrobot.greendao.converter.PropertyConverter;

import java.util.Date;

/**
 * Created by Carlos on 28/01/2017.
 */

public class DateTimeConverter implements PropertyConverter<Date, Long> {
    @Override
    public Date convertToEntityProperty(Long databaseValue) {
        return databaseValue == null ? null : new Date(databaseValue);
    }

    @Override
    public Long convertToDatabaseValue(Date entityProperty) {
        return entityProperty == null ? null : entityProperty.getTime();
    }
}
