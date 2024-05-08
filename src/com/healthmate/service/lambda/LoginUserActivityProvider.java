package com.healthmate.service.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.healthmate.service.dependency.DaggerServiceComponent;
import com.healthmate.service.dependency.ServiceComponent;
import com.healthmate.service.models.requests.LoginUserRequest;
import com.healthmate.service.models.response.LoginUserResponse;

public class LoginUserActivityProvider implements RequestHandler<LoginUserRequest, LoginUserResponse> {

    public LoginUserActivityProvider() {

    }

    @Override
    public LoginUserResponse handleRequest(final LoginUserRequest loginUserRequest, Context context) {
        return getServiceComponent().provideLoginUserActivity().handleRequest(loginUserRequest, context);
    }

    private ServiceComponent getServiceComponent() {
        ServiceComponent dagger = DaggerServiceComponent.create();
        return dagger;
    }
}
