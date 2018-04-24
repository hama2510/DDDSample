package com.kien.dddsample.infrastructure.repository;

import com.kien.dddsample.domain.card.CardDomain;
import com.kien.dddsample.domain.card.ICardRepository;
import com.kien.dddsample.infrastructure.factory.CardFactory;
import com.kien.dddsample.infrastructure.model.Card;
import com.kien.dddsample.infrastructure.repository.hibernate.CardRepositoryHibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardRepository implements ICardRepository {
    @Autowired
    private CardRepositoryHibernate cardRepositoryHibernate;
    @Autowired
    private CardFactory cardFactory;

    @Override
    public void save(CardDomain cardDomain) {
        Card card = new Card();
        card.setSerial(cardDomain.getCode().getCode());
        card.setMoney(cardDomain.getMoney());
        card.setStatus(cardDomain.getStatus());
        cardRepositoryHibernate.save(card);
    }

    @Override
    public CardDomain get(String serial) {
        return cardFactory.build(cardRepositoryHibernate.get(serial));
    }
}
