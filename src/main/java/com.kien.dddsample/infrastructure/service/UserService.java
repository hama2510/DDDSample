package com.kien.dddsample.infrastructure.service;

import com.kien.dddsample.domain.card.CardDomain;
import com.kien.dddsample.domain.card.ICardRepository;
import com.kien.dddsample.domain.tour.ITourRepository;
import com.kien.dddsample.domain.tour.TourDomain;
import com.kien.dddsample.domain.user.IUserRepository;
import com.kien.dddsample.domain.user.UserDomain;
import com.kien.dddsample.infrastructure.dto.UserLoginDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private ITourRepository tourRepository;
    @Autowired
    private ICardRepository cardRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Transactional
    public void bookTour(String userCode, String tourCode) {
        TourDomain tour = tourRepository.get(tourCode);
        UserDomain user = userRepository.get(userCode);
        user.bookTour(tour);
        userRepository.save(user);
        tourRepository.save(tour);
    }

    @Transactional
    public void deposit(String userCode, String cardSerial) {
        UserDomain user = userRepository.get(userCode);
        if (user == null) {
            throw new RuntimeException("Wrong user id");
        }
        CardDomain card = cardRepository.get(cardSerial);
        user.deposit(card.getMoney());
        card.use();
        userRepository.save(user);
        cardRepository.save(card);
    }

    public UserDomain login(UserLoginDto login) {
        UserDomain userDomain = userRepository.findByUsername(login.getUsername());
        if (passwordEncoder.matches(login.getPassword(), userDomain.getPassword())) {
            return userDomain;
        } else {
            throw new IllegalArgumentException("Password is incorrect");
        }
    }
}
