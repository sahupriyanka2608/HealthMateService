package com.healthmate.service.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.healthmate.service.dynamodb.models.Appointment;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AppointmentDao {
    private final DynamoDBMapper dynamoDBMapper;
    @Inject
    public  AppointmentDao(DynamoDBMapper dynamoDBMapper) {
        this.dynamoDBMapper = dynamoDBMapper;
    }
    public List<Appointment> getAppointmentsByUser(String email) {
        //return dynamoDBMapper.query(Appointment.class, new DynamoDBQueryExpression().withHashKeyValues(email));
        Map<String, AttributeValue> eav = new HashMap<>();
        eav.put(":val1", new AttributeValue().withS(email));
        DynamoDBQueryExpression<Appointment> queryExpression = new DynamoDBQueryExpression<Appointment>()
                .withKeyConditionExpression("user_id = :val1")
                .withExpressionAttributeValues(eav);
        List<Appointment> appointments = dynamoDBMapper.query(Appointment.class, queryExpression);
        return appointments;
    }
    public Appointment getAppointment(String email, String appointmentTime) {
        return  dynamoDBMapper.load(Appointment.class, email, appointmentTime);
    }
    public void deleteAppointment(Appointment appointment) {
        dynamoDBMapper.delete(appointment);
    }
    public Appointment saveAppointment(Appointment appointment) {
        dynamoDBMapper.save(appointment);
        return appointment;
    }
}
