package com.kien.dddsample.infrastructure.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TourDto {
    private String id;
    private String startTime;
    private String endTime;
    private String startLocation;
    private String endLocation;
    private String cost;
    private String description;
    private String status;
    private Integer maxMember;
    private Integer currentMember;
}
