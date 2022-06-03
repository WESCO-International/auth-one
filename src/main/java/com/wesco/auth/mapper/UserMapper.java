package com.wesco.auth.mapper;

import lombok.Data;

@Data
public class UserMapper {

    public String username;
    public boolean enabled;
    public String firstName;
    public String lastName;
    public String email;
}
