package com.kien.dddsample.infrastructure.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegistration {
    private String username;
    private String password;
    private String name;
    private String email;
    private String phone;
}
