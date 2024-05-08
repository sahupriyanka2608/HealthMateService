package com.healthmate.service.models.requests;

import com.healthmate.service.dynamodb.models.Day;
import com.healthmate.service.dynamodb.models.TimeRange;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class RegisterDoctorRequest {
    private String licenseNumber;
    private String firstName;
    private String lastName;
    private String email;
    private String department;
    private String about;
    private  String contact;
    private Map<Day, List<TimeRange>> schedule;
}
