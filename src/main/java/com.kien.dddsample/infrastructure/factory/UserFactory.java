package com.kien.dddsample.infrastructure.factory;

import com.kien.dddsample.domain.user.UserCode;
import com.kien.dddsample.domain.user.UserDomain;
import com.kien.dddsample.infrastructure.dto.UserRegistration;
import com.kien.dddsample.infrastructure.model.User;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserFactory {
    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserDomain create(UserRegistration registration) {
        return new UserDomain(new UserCode(UUID.randomUUID().toString()), registration.getUsername(), registration.getPassword(), registration.getName(), registration.getEmail(), registration.getPhone(), 0);
    }

    public UserDomain build(@NonNull User user) {
        return new UserDomain(new UserCode(user.getId()), user.getUsername(), passwordEncoder.encode(user.getPassword()), user.getName(), user.getEmail(), user.getPhone(), user.getBalance());
    }
}
