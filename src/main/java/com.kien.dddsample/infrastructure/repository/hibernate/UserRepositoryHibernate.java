package com.kien.dddsample.infrastructure.repository.hibernate;

import com.kien.dddsample.infrastructure.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
@Transactional
public class UserRepositoryHibernate extends AbstractHibernate<User> {
    public UserRepositoryHibernate() {
        super(User.class);
    }

    public User findByUsername(String username) {
        List<User> users = super.query((builder, root) -> builder.equal(root.get("username"), username));
        if (users.size() > 0) {
            return users.get(0);
        }
        return null;
    }
}
