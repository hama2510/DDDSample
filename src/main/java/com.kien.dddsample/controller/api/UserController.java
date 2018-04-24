package com.kien.dddsample.controller.api;

import com.kien.dddsample.domain.user.IUserRepository;
import com.kien.dddsample.domain.user.UserDomain;
import com.kien.dddsample.infrastructure.dto.DepositDto;
import com.kien.dddsample.infrastructure.dto.UserLoginDto;
import com.kien.dddsample.infrastructure.dto.UserRegistration;
import com.kien.dddsample.infrastructure.factory.UserFactory;
import com.kien.dddsample.infrastructure.service.UserService;
import com.kien.dddsample.infrastructure.service.converter.UserConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private UserFactory userFactory;
    @Autowired
    private UserService userService;
    @Autowired
    private UserConverter userConverter;

    @CrossOrigin
    @RequestMapping(value = "/api/users", method = RequestMethod.POST)
    public ResponseEntity registration(@RequestBody UserRegistration registration) {
        try {
            UserDomain domain = userFactory.create(registration);
            userRepository.save(domain);
            return new ResponseEntity("OK", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @CrossOrigin
    @RequestMapping(value = "/api/users/{id}/deposit", method = RequestMethod.POST)
    public ResponseEntity deposit(@RequestBody DepositDto deposit, @PathVariable("id") String id) {
        try {
            userService.deposit(id, deposit.getSerial());
            return new ResponseEntity("OK", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @CrossOrigin
    @RequestMapping(value = "/api/users/{id}/book/{tour}", method = RequestMethod.POST)
    public ResponseEntity bookTour(@PathVariable("tour") String code, @PathVariable("id") String id) {
        try {
            userService.bookTour(id, code);
            return new ResponseEntity("OK", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @CrossOrigin
    @RequestMapping(value = "/api/users/login", method = RequestMethod.POST)
    public ResponseEntity login(@RequestBody UserLoginDto login) {
        try {
            UserDomain user = userService.login(login);
            return new ResponseEntity(userConverter.convert(user), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
