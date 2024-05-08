package com.healthmate.service.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.healthmate.service.dynamodb.models.Hospital;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HospitalDao {
    private final DynamoDBMapper dynamoDBMapper;
    @Inject
    public  HospitalDao(DynamoDBMapper dynamoDBMapper) {
        this.dynamoDBMapper = dynamoDBMapper;
    }
    public List<Hospital> getHospitalByPincode(String pincode) {
        Map<String, AttributeValue> eav = new HashMap<>();
        eav.put(":val1", new AttributeValue().withS(pincode));
        //return dynamoDBMapper.query(Hospital.class, new DynamoDBQueryExpression().withHashKeyValues(pincode));
        DynamoDBQueryExpression<Hospital> queryExpression = new DynamoDBQueryExpression<Hospital>()
                .withKeyConditionExpression("pincode = :val1")
                .withExpressionAttributeValues(eav);
        List<Hospital> hospitals = dynamoDBMapper.query(Hospital.class, queryExpression);
        return hospitals;
    }

    public Hospital get(String pincode, String hospitalId) {
        return dynamoDBMapper.load(Hospital.class, pincode, hospitalId);
    }
    public Hospital save(Hospital hospital) {
        dynamoDBMapper.save(hospital);
        return hospital;
    }

}
