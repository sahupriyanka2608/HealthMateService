package com.healthmate.service.models.requests;

import com.healthmate.service.dynamodb.models.Doctor;
import com.healthmate.service.dynamodb.models.Hospital;

public class CreateObjectFromRequest {
    public static Doctor createdoctor(RegisterDoctorRequest registerDoctorRequest) {
        Doctor doctor = new Doctor();
        doctor.setAbout(registerDoctorRequest.getAbout());
        doctor.setSchedule(registerDoctorRequest.getSchedule());
        doctor.setFirstName(registerDoctorRequest.getFirstName());
        doctor.setDepartment(registerDoctorRequest.getDepartment());
        doctor.setEmail(registerDoctorRequest.getEmail());
        doctor.setContact(registerDoctorRequest.getContact());
        doctor.setLastName(registerDoctorRequest.getLastName());
        doctor.setDoctorId(registerDoctorRequest.getLicenseNumber());
        return doctor;
    }
    public static Hospital createHospital(RegisterHospitalRequest registerHospitalRequest) {
        Hospital hospital = new Hospital();
        hospital.setAddress(registerHospitalRequest.getAddress());
        hospital.setContact(registerHospitalRequest.getContact());
        hospital.setHospitalId(registerHospitalRequest.getHospitalId());
        hospital.setName(registerHospitalRequest.getName());
        hospital.setDoctorsInDept(registerHospitalRequest.getDoctorsInDept());
        hospital.setPincode(registerHospitalRequest.getPincode());
        return hospital;
    }

}
