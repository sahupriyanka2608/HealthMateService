package com.healthmate.service.dynamodb.models;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverted;
import com.healthmate.service.converter.AppointmentStatusConverter;

@DynamoDBTable(tableName = "appointments")
public class Appointment {
    private String email;
    private String doctorId;
    private String hospitalId;
    private String pincode;
    private String appointmentTime;  //Format:(appointmentDate#time#bookingTime)Eg:2024-04-08#10:15:00#time when appointment was created
    private AppointmentStatus status;
    @DynamoDBHashKey(attributeName = "user_id")
    public String getUserId() {
        return email;
    }

    public void setUserId(String email) {
        this.email = email;
    }
    @DynamoDBAttribute(attributeName = "doctor_id")
    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }
    @DynamoDBAttribute(attributeName = "hospital_id")
    public String getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(String hospitalId) {
        this.hospitalId = hospitalId;
    }

    @DynamoDBRangeKey(attributeName = "appointment_time")
    public String getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(String time) {
        this.appointmentTime = time;
    }
    @DynamoDBAttribute
    @DynamoDBTypeConverted(converter = AppointmentStatusConverter.class)
    public AppointmentStatus getStatus() {
        return status;
    }

    public void setStatus(AppointmentStatus status) {
        this.status = status;
    }
    @DynamoDBAttribute
    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }
}
