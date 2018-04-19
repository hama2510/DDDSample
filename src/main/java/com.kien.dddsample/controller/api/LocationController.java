package com.kien.dddsample.controller.api;

import com.kien.dddsample.domain.location.LocationDomain;
import com.kien.dddsample.domain.location.ILocationRepository;
import com.kien.dddsample.infrastructure.dto.LocationDto;
import com.kien.dddsample.infrastructure.service.converter.LocationConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class LocationController {
    @Autowired
    private ILocationRepository ILocationRepository;
    @Autowired
    private LocationConverter locationConverter;

    @RequestMapping(value = "/api/locations", method = RequestMethod.GET)
    public List<LocationDto> getLocations() {
        List<LocationDto> locations = new ArrayList<>();
        List<LocationDomain> domains = ILocationRepository.findAll();
        domains.forEach(
                (item) -> locations.add(locationConverter.convert(item))
        );
        return locations;
    }
}