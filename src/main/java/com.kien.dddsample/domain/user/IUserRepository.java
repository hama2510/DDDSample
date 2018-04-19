package com.kien.dddsample.domain.user;

public interface IUserRepository {
    UserDomain get(String code);

    void save(UserDomain domain);


}
