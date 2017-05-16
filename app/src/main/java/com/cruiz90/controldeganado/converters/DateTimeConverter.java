package com.cruiz90.controldeganado.converters;

import org.greenrobot.greendao.converter.PropertyConverter;
import org.joda.time.DateTime;

/**
 * Created by Carlos on 28/01/2017.
 */

public class DateTimeConverter implements PropertyConverter<DateTime, Long> {
    @Override
    public DateTime convertToEntityProperty(Long databaseValue) {
        return databaseValue==null ? null : new DateTime(databaseValue);
    }

    @Override
    public Long convertToDatabaseValue(DateTime entityProperty) {
        return  entityProperty==null?null:entityProperty.getMillis();
    }
}
