package com.healthmate.service.activity;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.healthmate.service.dynamodb.HospitalDao;
import com.healthmate.service.dynamodb.models.Doctor;
import com.healthmate.service.dynamodb.models.Hospital;
import com.healthmate.service.dynamodb.models.TimeRange;
import com.healthmate.service.exceptions.InvalidAttributeValueException;
import com.healthmate.service.models.requests.GetDoctorRequest;
import com.healthmate.service.models.response.DoctorDetails;
import com.healthmate.service.models.response.GetDoctorResponse;
import com.healthmate.service.util.DoctorServiceUtils;
import com.healthmate.service.util.JwtUtils;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetNearByDoctorActivity implements RequestHandler<GetDoctorRequest,GetDoctorResponse> {
    private HospitalDao hospitalDao;
    private GetDoctorAvailability getDoctorAvailability;

    @Inject
    public GetNearByDoctorActivity(HospitalDao hospitalDao, GetDoctorAvailability getDoctorAvailability) {
        this.hospitalDao = hospitalDao;
        this.getDoctorAvailability = getDoctorAvailability;
    }
    @Override
    public GetDoctorResponse handleRequest(final GetDoctorRequest getDoctorRequest, Context context) {
        if(!JwtUtils.validateToken(getDoctorRequest.getToken())) {
            throw new IllegalArgumentException("Invalid User Credentials.Please Login");
        }
        if (!DoctorServiceUtils.isValidRequest(getDoctorRequest)) {
            throw new InvalidAttributeValueException("All fields are mandatory");
        }
        List<Hospital> hospitals = hospitalDao.getHospitalByPincode(getDoctorRequest.getPincode());
        //List<String> nearByDoctorsId = new ArrayList<>();
//        for (Hospital hospital:hospitals) {
//
//            if (hospital.getDoctorsInDept().containsKey(getDoctorRequest.getDepartment())) {
//                List<String> doctorIds = hospital.getDoctorsInDept().get(getDoctorRequest.getDepartment());
//                for (String id:doctorIds) {
//                    nearByDoctorsId.add(id);
//                }
//            }
//        }
        Map<Hospital,List<String>> nearByDoctors = new HashMap<>();
        for (Hospital hospital:hospitals) {
            if (hospital.getDoctorsInDept().containsKey(getDoctorRequest.getDepartment())) {
                List<String> doctorIds = hospital.getDoctorsInDept().get(getDoctorRequest.getDepartment());
                nearByDoctors.put(hospital,doctorIds);
            }


        }
        List<DoctorDetails> doctorDetails = new ArrayList<>();
        for(Hospital hospital:nearByDoctors.keySet()) {
            Map<String, List<TimeRange>> nearByAvailableDoctors = getDoctorAvailability.getAvailableDoctors(getDoctorRequest.getDate(), nearByDoctors.get(hospital));

            for (String s : nearByAvailableDoctors.keySet()) {
                Doctor doctor = getDoctorAvailability.getDoctorDao().getDoctor(s);
                doctorDetails.add(DoctorDetails.builder()
                                .firstName(doctor.getFirstName())
                                .lastName(doctor.getLastName())
                                .about(doctor.getAbout())
                                .hospitalName(hospital.getName())
                                .hospitalAddress(hospital.getAddress())
                                .pincode(hospital.getPincode())
                                .availableSlots(nearByAvailableDoctors.get(s))
                                .licenseNumber(doctor.getDoctorId())
                                .department(doctor.getDepartment())
                                .hospitalId(hospital.getHospitalId())
                        .build());
            }
        }
        return new GetDoctorResponse(doctorDetails);
    }


}
