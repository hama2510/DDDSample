package com.kien.dddsample.infrastructure.service.converter;

import com.kien.dddsample.domain.location.LocationDomain;
import com.kien.dddsample.infrastructure.dto.LocationDto;
import org.springframework.stereotype.Service;

@Service
public class LocationConverter {
    public LocationDto convert(LocationDomain domain) {
        LocationDto location = new LocationDto();
        location.setCode(domain.getCode().getCode());
        location.setName(domain.getName());
        return location;
    }
}
