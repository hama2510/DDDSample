package com.kien.dddsample.infrastructure.repository.hibernate;

import com.kien.dddsample.infrastructure.model.Card;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class CardRepositoryHibernate extends AbstractHibernate<Card> {
    public CardRepositoryHibernate() {
        super(Card.class);
    }
}
