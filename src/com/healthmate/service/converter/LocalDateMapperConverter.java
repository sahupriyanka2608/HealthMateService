package com.healthmate.service.converter;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;
import com.healthmate.service.dynamodb.models.LocalDateMapper;

public class LocalDateMapperConverter implements DynamoDBTypeConverter<String, LocalDateMapper> {
    @Override
    public String convert(LocalDateMapper object) {
        String date = null;
        if (object != null) {
            date = String.format("%s",object.getDate());
        }
        return date;
    }
    @Override
    public LocalDateMapper unconvert(String s) {
        LocalDateMapper date = new LocalDateMapper(s);
        date.setDate(s);
        return date;
    }
}
