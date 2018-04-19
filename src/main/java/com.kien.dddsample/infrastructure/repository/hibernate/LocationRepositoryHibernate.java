package com.kien.dddsample.infrastructure.repository.hibernate;

import com.kien.dddsample.infrastructure.model.Location;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class LocationRepositoryHibernate extends AbstractHibernate<Location> {
    public LocationRepositoryHibernate() {
        super(Location.class);
    }

}
