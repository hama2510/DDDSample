package com.kien.dddsample.controller.api;

import com.kien.dddsample.domain.tour.ITourRepository;
import com.kien.dddsample.domain.tour.TourDomain;
import com.kien.dddsample.infrastructure.dto.TourDto;
import com.kien.dddsample.infrastructure.dto.TourRegistration;
import com.kien.dddsample.infrastructure.factory.TourFactory;
import com.kien.dddsample.infrastructure.service.converter.TourConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TourController {

    @Autowired
    private TourFactory tourFactory;
    @Autowired
    private ITourRepository tourRepository;
    @Autowired
    private TourConverter tourConverter;

    @RequestMapping(value = "/api/tours", method = RequestMethod.POST)
    public ResponseEntity createTour(@RequestBody TourRegistration tourRegistration) {
        try {
            TourDomain domain = tourFactory.create(tourRegistration);
            tourRepository.save(domain);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/api/tours", method = RequestMethod.GET)
    public List<TourDto> findAll() {
        List<TourDto> tours = new ArrayList<>();
        tourRepository.findAll().forEach((item) ->
                tours.add(tourConverter.convert(item))
        );
        return tours;
    }
}
