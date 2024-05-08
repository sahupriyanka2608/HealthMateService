package com.healthmate.service.models.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppointmentDetails {
    private String doctorName;
    private String hospitalName;
    private String hospitalAddress;
    private String appointmentDate;
    private String appointmentTime;
    private String bookingTime;
    private Boolean isCancellable;
    private String status;
}
