package com.healthmate.service.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.healthmate.service.dependency.DaggerServiceComponent;
import com.healthmate.service.dependency.ServiceComponent;
import com.healthmate.service.dynamodb.models.Hospital;
import com.healthmate.service.models.requests.RegisterHospitalRequest;

public class RegisterHospitalActivityProvider implements RequestHandler<RegisterHospitalRequest, Hospital> {
    public RegisterHospitalActivityProvider() {

    }

    @Override
    public Hospital handleRequest(final RegisterHospitalRequest registerHospitalRequest, Context context) {
        return getServiceComponent().provideRegisterHospitalActivity().handleRequest(registerHospitalRequest, context);
    }

    private ServiceComponent getServiceComponent() {
        ServiceComponent dagger = DaggerServiceComponent.create();
        return dagger;
    }
}
