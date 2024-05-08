package com.healthmate.service.util;

import com.healthmate.service.models.requests.CreateUserRequest;
import com.healthmate.service.models.requests.LoginUserRequest;
import org.apache.commons.lang3.StringUtils;

import java.util.regex.Pattern;

public class UserServiceUtils {
    private static final Pattern VALID_PASSWORD_PATTERN = Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$");
    static final int USER_ID_LENGTH = 5;
    public static boolean isValidRequest(final CreateUserRequest createUserRequest) {
        if (StringUtils.isBlank(createUserRequest.getContact()) || StringUtils.isBlank(createUserRequest.getEmail())
        || StringUtils.isBlank(createUserRequest.getFirstName()) || StringUtils.isBlank(createUserRequest.getLastName())
        || StringUtils.isBlank(createUserRequest.getPassword())) {
            return false;
        }
        return true;

    }
    public static boolean isValidLoginRequest(final LoginUserRequest loginUserRequest) {
        if (StringUtils.isBlank(loginUserRequest.getEmail())
                || StringUtils.isBlank(loginUserRequest.getPassword())) {
            return false;
        }
        return true;

    }
    public static boolean isValidPassword(final String stringToValidate) {
        if (StringUtils.isBlank(stringToValidate)) {
            return false;
        }
        return VALID_PASSWORD_PATTERN.matcher(stringToValidate).find();
    }
    public static String generateUserId(String email) {

        return String.valueOf(email.hashCode());
    }
}
