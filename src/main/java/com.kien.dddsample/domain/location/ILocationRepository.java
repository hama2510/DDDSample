package com.kien.dddsample.domain.location;

import java.util.List;

public interface ILocationRepository {
    List<LocationDomain> findAll();

    LocationDomain get(String code);
}
