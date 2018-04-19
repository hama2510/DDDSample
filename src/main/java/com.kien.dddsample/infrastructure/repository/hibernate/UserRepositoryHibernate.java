package com.kien.dddsample.infrastructure.repository.hibernate;

import com.kien.dddsample.infrastructure.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class UserRepositoryHibernate extends AbstractHibernate<User> {
    public UserRepositoryHibernate() {
        super(User.class);
    }

}
