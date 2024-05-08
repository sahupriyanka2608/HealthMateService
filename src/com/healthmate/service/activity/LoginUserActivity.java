package com.healthmate.service.activity;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.healthmate.service.dynamodb.UserDao;
import com.healthmate.service.dynamodb.models.User;
import com.healthmate.service.exceptions.IncorrectPasswordException;
import com.healthmate.service.exceptions.InvalidInputException;
import com.healthmate.service.exceptions.UserNotFoundException;
import com.healthmate.service.models.requests.LoginUserRequest;
import com.healthmate.service.models.response.LoginUserResponse;
import com.healthmate.service.util.JwtUtils;
import com.healthmate.service.util.UserServiceUtils;

import javax.inject.Inject;

public class LoginUserActivity implements RequestHandler<LoginUserRequest, LoginUserResponse> {
    private UserDao userDao;
    @Inject
    public LoginUserActivity(UserDao userDao) {
        this.userDao = userDao;
    }
    @Override
    public LoginUserResponse handleRequest(final LoginUserRequest loginUserRequest, Context context) {
        if (!UserServiceUtils.isValidLoginRequest(loginUserRequest)) {
            throw new InvalidInputException("Both email and password should be given");
        }
        User user = userDao.getUser(loginUserRequest.getEmail());
        if (user == null) {
            throw new UserNotFoundException("User is not Registered");
        }
        if (!user.getPassword().equals(loginUserRequest.getPassword())) {
            throw new IncorrectPasswordException("Password is Incorrect");
        }
        String token = JwtUtils.generateToken(user.getEmail());
        return new LoginUserResponse(token);
    }
}
