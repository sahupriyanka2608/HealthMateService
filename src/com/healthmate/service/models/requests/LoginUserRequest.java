package com.healthmate.service.models.requests;

import dagger.Component;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class LoginUserRequest {
    private String email;
    private String password;

}
