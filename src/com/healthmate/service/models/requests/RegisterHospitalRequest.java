package com.healthmate.service.models.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterHospitalRequest {
    private String hospitalId;
    private String name;
    private String address;
    private String pincode;
    private Map<String, List<String>> doctorsInDept = new HashMap<>();
    private  String contact;

}
