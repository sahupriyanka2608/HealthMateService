package com.healthmate.service.models.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateAppointmentRequest {
    private String doctorId;
    private String hospitalId;
    private String pincode;
    private String appointmentTime;
    private String apppointmentDate;

    private String token;


    
}
