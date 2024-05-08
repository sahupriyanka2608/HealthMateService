package com.healthmate.service.dynamodb.models;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverted;
import com.healthmate.service.converter.AvailableSlotConverter;
import com.healthmate.service.converter.ScheduleConverter;
import lombok.EqualsAndHashCode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@DynamoDBTable(tableName = "doctors")
@EqualsAndHashCode
public class Doctor {
    private String licenseNumber;
    private String firstName;
    private String lastName;
    private String department;
    private String about;
    private  String contact;
    private  String email;
    private Map<Day, List<TimeRange>> schedule;
    private Map<LocalDateMapper,List<Availability>> availableSlot = new HashMap<>();
    @DynamoDBAttribute
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @DynamoDBHashKey(attributeName = "license_number")
    public String getDoctorId() {
        return licenseNumber;
    }
    public void setDoctorId(String doctorId) {
        this.licenseNumber = doctorId;
    }
    @DynamoDBAttribute(attributeName = "department")
    public String getDepartment() {
        return department;
    }
    public void setDepartment(String department) {
        this.department = department;
    }
    @DynamoDBAttribute(attributeName = "first_name")
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    @DynamoDBAttribute(attributeName = "last_name")
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    @DynamoDBAttribute(attributeName = "contact")
    public String getContact() {
        return contact;
    }
    public void setContact(String contact) {
        this.contact = contact;
    }
    @DynamoDBAttribute(attributeName = "about")
    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }
    @DynamoDBAttribute(attributeName = "schedule")
    @DynamoDBTypeConverted(converter = ScheduleConverter.class)
    public Map<Day, List<TimeRange>> getSchedule() {
        return schedule;
    }
    public void setSchedule(Map<Day, List<TimeRange>> availableSlot) {
        this.schedule = availableSlot;
    }
    @DynamoDBAttribute(attributeName = "available_slot")
    @DynamoDBTypeConverted(converter = AvailableSlotConverter.class)
    public Map<LocalDateMapper,List<Availability>> getAvailableSlot() {
        return availableSlot;
    }
    public void setAvailableSlot(Map<LocalDateMapper,List<Availability>> availableSlot) {
        this.availableSlot = availableSlot;
    }
}
