package com.healthmate.service.util;

import com.healthmate.service.models.requests.CancelAppointmentRequest;
import org.apache.commons.lang3.StringUtils;

public class AppointmentServiceUtils {
    public static boolean isValidRequest(final CancelAppointmentRequest cancelAppointmentRequest) {
        if (StringUtils.isBlank(String.valueOf(cancelAppointmentRequest.getAppointmentTime()))) {
            return false;
        }
        return true;

    }
}
