package com.healthmate.service.dynamodb.models;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverted;
import com.healthmate.service.converter.DoctorsInDeptConverter;
import lombok.EqualsAndHashCode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@DynamoDBTable(tableName = "hospitals")
@EqualsAndHashCode
public class Hospital {
    private String hospitalId;
    private String name;
    private String address;
    private String pincode;


    private Map<String, List<String>> doctorsInDept = new HashMap<>();
    private  String contact;
    @DynamoDBRangeKey(attributeName = "hospital_id")
    public String getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(String hospitalId) {
        this.hospitalId = hospitalId;
    }
    @DynamoDBAttribute(attributeName = "name")
    public String getName() {
        return name;
    }
    @DynamoDBAttribute(attributeName = "address")
    public String getAddress() {
        return address;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    @DynamoDBAttribute(attributeName = "doctors_in_dept")
    @DynamoDBTypeConverted(converter = DoctorsInDeptConverter.class)
    public Map<String, List<String>> getDoctorsInDept() {
        return doctorsInDept;
    }
    public void setDoctorsInDept(Map<String,List<String>> doctorsInDept) {
        this.doctorsInDept = doctorsInDept;
    }

    @DynamoDBAttribute(attributeName = "contact")
    public String getContact() {
        return contact;
    }
    public void setContact(String contact) {
        this.contact = contact;
    }
    @DynamoDBHashKey
    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

}
