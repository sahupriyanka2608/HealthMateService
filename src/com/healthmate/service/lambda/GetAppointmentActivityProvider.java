package com.healthmate.service.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.healthmate.service.dependency.DaggerServiceComponent;
import com.healthmate.service.dependency.ServiceComponent;
import com.healthmate.service.models.requests.GetAppointmentRequest;
import com.healthmate.service.models.response.GetAppointmentResponse;

public class GetAppointmentActivityProvider implements RequestHandler<GetAppointmentRequest, GetAppointmentResponse> {
    public GetAppointmentActivityProvider() {
    }

    @Override
    public GetAppointmentResponse handleRequest(final GetAppointmentRequest getAppointmentRequest, Context context) {
        return getServiceComponent().provideGetAppointmentActivity().handleRequest(getAppointmentRequest, context);
    }

    private ServiceComponent getServiceComponent() {
        ServiceComponent dagger = DaggerServiceComponent.create();
        return dagger;
    }
}
