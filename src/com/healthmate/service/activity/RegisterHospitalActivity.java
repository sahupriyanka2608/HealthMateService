package com.healthmate.service.activity;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.healthmate.service.dynamodb.HospitalDao;
import com.healthmate.service.dynamodb.models.Hospital;
import com.healthmate.service.exceptions.InvalidAttributeValueException;
import com.healthmate.service.models.requests.CreateObjectFromRequest;
import com.healthmate.service.models.requests.RegisterHospitalRequest;
import com.healthmate.service.util.DoctorServiceUtils;

import javax.inject.Inject;

public class RegisterHospitalActivity implements RequestHandler<RegisterHospitalRequest, Hospital> {
    private HospitalDao hospitalDao;
    @Inject
    public RegisterHospitalActivity(HospitalDao hospitalDao) {
        this.hospitalDao = hospitalDao;
    }
    @Override
    public Hospital handleRequest(final RegisterHospitalRequest registerHospitalRequest, Context context) {
        if (!DoctorServiceUtils.isValidRegisterHospitalRequest(registerHospitalRequest)) {
            throw new InvalidAttributeValueException("Detailed info is not given to register hospital");
        }
        Hospital hospital = CreateObjectFromRequest.createHospital(registerHospitalRequest);
        return hospitalDao.save(hospital);
    }
}
