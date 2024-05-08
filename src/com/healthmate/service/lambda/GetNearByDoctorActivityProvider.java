package com.healthmate.service.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.healthmate.service.dependency.DaggerServiceComponent;
import com.healthmate.service.dependency.ServiceComponent;
import com.healthmate.service.models.requests.GetDoctorRequest;
import com.healthmate.service.models.response.GetDoctorResponse;

public class GetNearByDoctorActivityProvider implements RequestHandler<GetDoctorRequest, GetDoctorResponse> {
    public GetNearByDoctorActivityProvider() {
    }

    @Override
    public GetDoctorResponse handleRequest(final GetDoctorRequest getDoctorRequest, Context context) {
        return getServiceComponent().provideGetNearByDoctorActivity().handleRequest(getDoctorRequest, context);
    }

    private ServiceComponent getServiceComponent() {
        ServiceComponent dagger = DaggerServiceComponent.create();
        return dagger;
    }
}
