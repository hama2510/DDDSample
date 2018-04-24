package com.kien.dddsample.infrastructure.service.converter;

import com.kien.dddsample.domain.user.UserDomain;
import com.kien.dddsample.infrastructure.dto.UserDto;
import com.kien.dddsample.infrastructure.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserConverter {

    @Autowired
    private TourConverter tourConverter;

    public UserDto convert(UserDomain domain) {
        UserDto user = new UserDto();
        user.setId(domain.getCode().getCode());
        user.setEmail(domain.getEmail());
        user.setPhone(domain.getPhone());
        user.setName(domain.getName());
        user.setUsername(domain.getUsername());
        user.setBalance(StringUtil.moneyFormat(domain.getBalance()));
        domain.getTours().forEach((item) ->
                user.getTours().add(tourConverter.convert(item)));
        return user;
    }
}
