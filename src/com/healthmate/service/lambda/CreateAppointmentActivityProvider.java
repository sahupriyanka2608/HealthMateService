package com.healthmate.service.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.healthmate.service.dependency.DaggerServiceComponent;
import com.healthmate.service.dependency.ServiceComponent;
import com.healthmate.service.models.requests.CreateAppointmentRequest;
import com.healthmate.service.models.response.CreateAppointmentResponse;

public class CreateAppointmentActivityProvider implements RequestHandler<CreateAppointmentRequest, CreateAppointmentResponse> {
    public CreateAppointmentActivityProvider() {
    }

    @Override
    public CreateAppointmentResponse handleRequest(final CreateAppointmentRequest createAppointmentRequest, Context context) {
        return getServiceComponent().provideCreateAppointmentActivity().handleRequest(createAppointmentRequest, context);
    }

    private ServiceComponent getServiceComponent() {
        ServiceComponent dagger = DaggerServiceComponent.create();
        return dagger;
    }
}
