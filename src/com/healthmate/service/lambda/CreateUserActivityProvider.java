package com.healthmate.service.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.healthmate.service.dependency.DaggerServiceComponent;
import com.healthmate.service.dependency.ServiceComponent;
import com.healthmate.service.dynamodb.models.User;
import com.healthmate.service.models.requests.CreateUserRequest;

public class CreateUserActivityProvider implements RequestHandler<CreateUserRequest, User> {

    public CreateUserActivityProvider() {

    }

    @Override
    public User handleRequest(final CreateUserRequest createUserRequest, Context context) {
        return getServiceComponent().provideCreateUserActivity().handleRequest(createUserRequest, context);
    }

    private ServiceComponent getServiceComponent() {
        ServiceComponent dagger = DaggerServiceComponent.create();
        return dagger;
    }
}
