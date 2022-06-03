package com.wesco.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDetail {
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String jwtToken;
}
