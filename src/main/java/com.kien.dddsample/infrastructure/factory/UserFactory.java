package com.kien.dddsample.infrastructure.factory;

import com.kien.dddsample.domain.tour.ITourRepository;
import com.kien.dddsample.domain.tour.TourDomain;
import com.kien.dddsample.domain.user.UserCode;
import com.kien.dddsample.domain.user.UserDomain;
import com.kien.dddsample.infrastructure.dto.UserRegistration;
import com.kien.dddsample.infrastructure.model.User;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserFactory {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private TourFactory tourFactory;

    public UserDomain create(UserRegistration registration) {
        return new UserDomain(new UserCode(UUID.randomUUID().toString()), registration.getUsername(), passwordEncoder.encode(registration.getPassword()), registration.getName(), registration.getEmail(), registration.getPhone(), 0, new ArrayList<>());
    }

    public UserDomain build(@NonNull User user) {
        List<TourDomain> tours = new ArrayList<>();
        user.getTours().forEach((item) ->
                tours.add(tourFactory.build(item))
        );
        return new UserDomain(new UserCode(user.getId()), user.getUsername(), user.getPassword(), user.getName(), user.getEmail(), user.getPhone(), user.getBalance(), tours);
    }
}
