package com.healthmate.service.lambda;


import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.healthmate.service.dependency.DaggerServiceComponent;
import com.healthmate.service.dependency.ServiceComponent;
import com.healthmate.service.models.requests.CancelAppointmentRequest;
import com.healthmate.service.models.response.CancelAppointmentResponse;

public class CancelAppointmentActivityProvider implements RequestHandler<CancelAppointmentRequest, CancelAppointmentResponse> {
    public CancelAppointmentActivityProvider() {
    }

    @Override
    public CancelAppointmentResponse handleRequest(final CancelAppointmentRequest cancelAppointmentRequest, Context context) {
        return getServiceComponent().provideCancelAppointmentActivity().handleRequest(cancelAppointmentRequest, context);
    }

    private ServiceComponent getServiceComponent() {
        ServiceComponent dagger = DaggerServiceComponent.create();
        return dagger;
    }
}

