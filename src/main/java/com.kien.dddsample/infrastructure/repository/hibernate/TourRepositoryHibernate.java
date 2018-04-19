package com.kien.dddsample.infrastructure.repository.hibernate;

import com.kien.dddsample.infrastructure.model.Tour;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class TourRepositoryHibernate extends AbstractHibernate<Tour> {
    public TourRepositoryHibernate() {
        super(Tour.class);
    }

}
