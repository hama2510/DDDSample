package com.kien.dddsample.infrastructure.repository;

import com.kien.dddsample.domain.location.ILocationRepository;
import com.kien.dddsample.domain.location.LocationDomain;
import com.kien.dddsample.infrastructure.factory.LocationFactory;
import com.kien.dddsample.infrastructure.model.Location;
import com.kien.dddsample.infrastructure.repository.hibernate.LocationRepositoryHibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class LocationRepository implements ILocationRepository {
    @Autowired
    private LocationRepositoryHibernate locationRepositoryHibernate;
    @Autowired
    private LocationFactory locationFactory;

    @Override
    public List<LocationDomain> findAll() {
        List<Location> locations = locationRepositoryHibernate.findAll();
        List<LocationDomain> domains = new ArrayList<>();
        locations.forEach((item) -> {
            domains.add(locationFactory.create(item));
        });
        return domains;
    }

    @Override
    public LocationDomain get(String code) {
        return locationFactory.create(locationRepositoryHibernate.get(code));
    }
}
