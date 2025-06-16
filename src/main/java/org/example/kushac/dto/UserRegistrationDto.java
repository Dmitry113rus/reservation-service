package org.example.kushac.dto;

import lombok.Data;

@Data
public class UserRegistrationDto {
    public String firstName;
    public String lastName;
    public String phone;
    public String email;
    public String username;
    public String password;
}

