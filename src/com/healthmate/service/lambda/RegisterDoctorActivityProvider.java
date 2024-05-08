package com.healthmate.service.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.healthmate.service.dependency.DaggerServiceComponent;
import com.healthmate.service.dependency.ServiceComponent;
import com.healthmate.service.dynamodb.models.Doctor;
import com.healthmate.service.models.requests.RegisterDoctorRequest;

public class RegisterDoctorActivityProvider implements RequestHandler<RegisterDoctorRequest, Doctor> {
    public RegisterDoctorActivityProvider() {

    }

    @Override
    public Doctor handleRequest(final RegisterDoctorRequest registerDoctorRequest, Context context) {
        return getServiceComponent().provideRegisterDoctorActivity().handleRequest(registerDoctorRequest, context);
    }

    private ServiceComponent getServiceComponent() {
        ServiceComponent dagger = DaggerServiceComponent.create();
        return dagger;
    }
}
