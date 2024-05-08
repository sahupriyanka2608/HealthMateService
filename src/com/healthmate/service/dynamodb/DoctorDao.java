package com.healthmate.service.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.healthmate.service.dynamodb.models.Doctor;
import com.healthmate.service.exceptions.DoctorNotFoundException;

import javax.inject.Inject;

public class DoctorDao {
    private final DynamoDBMapper dynamoDBMapper;
    @Inject
    public  DoctorDao(DynamoDBMapper dynamoDBMapper) {
        this.dynamoDBMapper = dynamoDBMapper;
    }
    public Doctor getDoctor(String licenseNumber) {
        Doctor doctor = dynamoDBMapper.load(Doctor.class, licenseNumber);
        if (doctor == null) {
            throw new DoctorNotFoundException();
        }
        return doctor;
    }
    public Doctor save(Doctor doctor) {
        dynamoDBMapper.save(doctor);
        return doctor;
    }
}
