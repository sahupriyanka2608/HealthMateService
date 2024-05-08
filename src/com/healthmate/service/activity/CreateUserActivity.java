package com.healthmate.service.activity;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.healthmate.service.dynamodb.UserDao;
import com.healthmate.service.dynamodb.models.User;
import com.healthmate.service.exceptions.EmailAlreadyRegisteredException;
import com.healthmate.service.exceptions.InvalidAttributeValueException;
import com.healthmate.service.models.requests.CreateUserRequest;
import com.healthmate.service.util.UserServiceUtils;

import javax.inject.Inject;

public class CreateUserActivity implements RequestHandler<CreateUserRequest, User> {
    private final UserDao userDao;
    @Inject
    public CreateUserActivity(UserDao userDao) {
        this.userDao = userDao;
    }
    @Override
    public User handleRequest(final CreateUserRequest createUserRequest, Context context) {
        if (!UserServiceUtils.isValidRequest(createUserRequest)) {
            throw new InvalidAttributeValueException();
        }
        User user = userDao.getUser(createUserRequest.getEmail());
        if (user != null) {
            throw new EmailAlreadyRegisteredException();
        }
        if (! UserServiceUtils.isValidPassword(createUserRequest.getPassword())) {
            throw new InvalidAttributeValueException("Please give a strong password of minimum 8 letter which contains atleast a number and letter and special character.");
        }
        String id = UserServiceUtils.generateUserId(createUserRequest.getEmail());
        User newUser = new User();
        newUser.setUserId(id);
        newUser.setEmail(createUserRequest.getEmail());
        newUser.setContact(createUserRequest.getContact());
        newUser.setFirstName(createUserRequest.getFirstName());
        newUser.setLastName(createUserRequest.getLastName());
        newUser.setPassword(createUserRequest.getPassword());
        return userDao.save(newUser);
    }
}
