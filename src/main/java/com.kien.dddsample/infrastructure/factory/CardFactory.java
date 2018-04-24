package com.kien.dddsample.infrastructure.factory;

import com.kien.dddsample.domain.card.CardCode;
import com.kien.dddsample.domain.card.CardDomain;
import com.kien.dddsample.infrastructure.dto.CardRegistration;
import com.kien.dddsample.infrastructure.model.Card;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.kien.dddsample.infrastructure.constant.CardConstant.ACTIVE;

@Service
public class CardFactory {

    public CardDomain create(CardRegistration registration) {
        if (!(registration.getMoney() == 100000 || registration.getMoney() == 200000 || registration.getMoney() == 500000)) {
            throw new IllegalArgumentException("Invalid money");
        }
        return new CardDomain(new CardCode(UUID.randomUUID().toString()), registration.getMoney(), ACTIVE);
    }

    public CardDomain build(Card card) {
        if (card == null) {
            throw new RuntimeException("Not found");
        }
        return new CardDomain(new CardCode(card.getSerial()), card.getMoney(), card.getStatus());
    }
}
