package com.kien.dddsample.infrastructure.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class UserDto {
    private String id;
    private String username;
    private String name;
    private String email;
    private String phone;
    private String balance;
    private List<TourDto> tours = new ArrayList<>();
}
