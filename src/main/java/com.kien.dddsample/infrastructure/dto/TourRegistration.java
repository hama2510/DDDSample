package com.kien.dddsample.infrastructure.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TourRegistration {
    private String startLocation;
    private String endLocation;
    private String startDate;
    private String endDate;
    private Integer cost;
    private String description;
    private Integer maxMember;
}
