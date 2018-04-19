package com.kien.dddsample.infrastructure.repository;

import com.kien.dddsample.domain.tour.ITourRepository;
import com.kien.dddsample.domain.tour.TourDomain;
import com.kien.dddsample.infrastructure.factory.TourFactory;
import com.kien.dddsample.infrastructure.model.Tour;
import com.kien.dddsample.infrastructure.model.User;
import com.kien.dddsample.infrastructure.repository.hibernate.LocationRepositoryHibernate;
import com.kien.dddsample.infrastructure.repository.hibernate.TourRepositoryHibernate;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class TourRepository implements ITourRepository {
    @Autowired
    private TourRepositoryHibernate tourRepositoryHibernate;
    @Autowired
    private LocationRepositoryHibernate locationRepositoryHibernate;
    @Autowired
    private TourFactory tourFactory;

    @Override
    public void save(TourDomain domain) {
        Tour tour = new Tour();
        tour.setId(domain.getId().getCode());
        tour.setCost(domain.getCost());
        tour.setDescription(domain.getDescription());
        tour.setEndTime(domain.getEndDate());
        tour.setStartTime(domain.getStartDate());
        tour.setStatus(domain.getStatus());
        tour.setStartLocation(locationRepositoryHibernate.get(domain.getStartLocation().getCode().getCode()));
        tour.setEndLocation(locationRepositoryHibernate.get(domain.getEndLocation().getCode().getCode()));
        tour.setCreatedAt(domain.getCreatedAt());
        tour.setMaxMember(domain.getMaxMember());
        domain.getMembers().forEach((item) ->
                tour.getUsers().add(new User(item.getCode()))
        );
        tourRepositoryHibernate.save(tour);
    }

    @Override
    public List<TourDomain> findAll() {
        List<TourDomain> domains = new ArrayList<>();
        List<Tour> tours = tourRepositoryHibernate.findAll();
        if (tours != null)
            tours.forEach((item) -> {
                domains.add(tourFactory.build(item));
            });
        return domains;
    }

    @Override
    @NonNull
    public TourDomain get(String code) {
        return tourFactory.build(tourRepositoryHibernate.get(code));
    }
}
