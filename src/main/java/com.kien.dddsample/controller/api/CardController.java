package com.kien.dddsample.controller.api;

import com.kien.dddsample.domain.card.CardDomain;
import com.kien.dddsample.domain.card.ICardRepository;
import com.kien.dddsample.infrastructure.dto.CardRegistration;
import com.kien.dddsample.infrastructure.factory.CardFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CardController {

    @Autowired
    private ICardRepository cardRepository;
    @Autowired
    private CardFactory cardFactory;

    @RequestMapping("/api/cards")
    public ResponseEntity add(@RequestBody CardRegistration registration) {
        try {
            CardDomain domain = cardFactory.create(registration);
            cardRepository.save(domain);
            return new ResponseEntity("OK", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
