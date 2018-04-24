package com.kien.dddsample.domain.card;

public interface ICardRepository {
    void save(CardDomain cardDomain);

    CardDomain get(String serial);
}
