package com.kien.dddsample.domain.tour;

import lombok.NonNull;

import java.util.List;

public interface ITourRepository {
    void save(TourDomain domain);

    List<TourDomain> findAll();

    @NonNull
    TourDomain get(String code);
}
