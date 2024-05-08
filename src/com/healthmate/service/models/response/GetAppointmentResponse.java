package com.healthmate.service.models.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAppointmentResponse {
    private String email;
    private List<AppointmentDetails> appointmentList;
}
