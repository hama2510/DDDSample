package com.kien.dddsample.infrastructure.factory;

import com.kien.dddsample.domain.location.LocationCode;
import com.kien.dddsample.domain.location.LocationDomain;
import com.kien.dddsample.infrastructure.model.Location;
import org.springframework.stereotype.Service;

@Service
public class LocationFactory {

    public LocationDomain create(Location location) {
        if (location != null) {
            LocationCode locationCode = new LocationCode(location.getId());
            return new LocationDomain(locationCode, location.getName());
        } else {
            return LocationDomain.UNKNOWN;
        }
    }
}
