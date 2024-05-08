package com.healthmate.service.models.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetDoctorResponse {
    private List<DoctorDetails> doctorDetails;
}
