package com.kien.dddsample.infrastructure.repository;

import com.kien.dddsample.domain.user.IUserRepository;
import com.kien.dddsample.domain.user.UserDomain;
import com.kien.dddsample.infrastructure.factory.UserFactory;
import com.kien.dddsample.infrastructure.model.User;
import com.kien.dddsample.infrastructure.repository.hibernate.UserRepositoryHibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserRepository implements IUserRepository {

    @Autowired
    private UserRepositoryHibernate userRepositoryHibernate;
    @Autowired
    private UserFactory userFactory;

    @Override
    public UserDomain get(String code) {
        User user = userRepositoryHibernate.get(code);
        if (user == null) {
            throw new RuntimeException("Not found user with code: " + code);
        }
        return userFactory.build(user);
    }

    @Override
    public void save(UserDomain domain) {
        User user = new User();
        user.setId(domain.getCode().getCode());
        user.setEmail(domain.getEmail());
        user.setName(domain.getName());
        user.setPassword(domain.getPassword());
        user.setPhone(domain.getPhone());
        user.setUsername(domain.getUsername());
        user.setBalance(domain.getBalance());
        userRepositoryHibernate.insert(user);
    }
}
