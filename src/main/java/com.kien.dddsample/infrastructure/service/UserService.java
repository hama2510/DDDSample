package com.kien.dddsample.infrastructure.service;

import com.kien.dddsample.domain.tour.ITourRepository;
import com.kien.dddsample.domain.tour.TourDomain;
import com.kien.dddsample.domain.user.IUserRepository;
import com.kien.dddsample.domain.user.UserDomain;
import com.kien.dddsample.infrastructure.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private ITourRepository tourRepository;

    public void bookTour(String userCode, String tourCode) {
        TourDomain tour = tourRepository.get(tourCode);
        UserDomain user = userRepository.get(userCode);
        tour.addMember(user);
        tourRepository.save(tour);
        user.pay(tour.getCost());
        userRepository.save(user);
    }
}
