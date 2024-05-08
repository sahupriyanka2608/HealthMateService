package com.healthmate.service.util;

import com.healthmate.service.models.requests.GetDoctorRequest;
import com.healthmate.service.models.requests.RegisterDoctorRequest;
import com.healthmate.service.models.requests.RegisterHospitalRequest;
import org.apache.commons.lang3.StringUtils;

public class DoctorServiceUtils {
    public static boolean isValidRequest(final GetDoctorRequest getDoctorRequest) {
        if (StringUtils.isBlank(String.valueOf(getDoctorRequest.getDate())) || StringUtils.isBlank(getDoctorRequest.getDepartment())
                || StringUtils.isBlank(getDoctorRequest.getPincode())) {
            return false;
        }
        return true;

    }
    public static boolean isValidCreateRequest(final RegisterDoctorRequest registerDoctorRequest) {
        if (StringUtils.isBlank(registerDoctorRequest.getFirstName()) || StringUtils.isBlank(registerDoctorRequest.getLastName())
                || StringUtils.isBlank(registerDoctorRequest.getEmail())|| StringUtils.isBlank(registerDoctorRequest.getContact())
                || StringUtils.isBlank(registerDoctorRequest.getAbout()) || StringUtils.isBlank(registerDoctorRequest.getLicenseNumber()) ||
        StringUtils.isBlank(registerDoctorRequest.getDepartment()) || registerDoctorRequest.getSchedule().isEmpty()) {
            return false;
        }
        return true;
    }
    public static boolean isValidRegisterHospitalRequest(final RegisterHospitalRequest registerHospitalRequest) {
        if (StringUtils.isBlank(registerHospitalRequest.getName()) || StringUtils.isBlank(registerHospitalRequest.getPincode())
        || StringUtils.isBlank(registerHospitalRequest.getContact()) || StringUtils.isBlank(registerHospitalRequest.getAddress())
        || registerHospitalRequest.getDoctorsInDept().isEmpty() || StringUtils.isBlank(registerHospitalRequest.getHospitalId())) {
            return false;
        }
        return true;
    }
}
