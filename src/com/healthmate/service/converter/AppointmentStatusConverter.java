package com.healthmate.service.converter;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;
import com.healthmate.service.dynamodb.models.AppointmentStatus;

public class AppointmentStatusConverter implements DynamoDBTypeConverter<String, AppointmentStatus> {

    @Override
    public String convert(AppointmentStatus status) {
        return status.name();
    }

    @Override
    public AppointmentStatus unconvert(String s) {
       return AppointmentStatus.valueOf(s);
    }
}
