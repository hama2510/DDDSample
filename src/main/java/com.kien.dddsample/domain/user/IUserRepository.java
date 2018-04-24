package com.kien.dddsample.domain.user;

public interface IUserRepository {
    UserDomain get(String code);

    UserDomain findByUsername(String username);

    void save(UserDomain domain);


}
