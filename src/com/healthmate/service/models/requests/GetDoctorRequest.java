package com.healthmate.service.models.requests;

import com.healthmate.service.dynamodb.models.LocalDateMapper;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetDoctorRequest {
    private String pincode;
    private  String department;
    private LocalDateMapper date;
    private String token;

}
