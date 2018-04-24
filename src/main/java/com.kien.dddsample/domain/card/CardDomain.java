package com.kien.dddsample.domain.card;

import com.kien.dddsample.domain._shared.Entity;
import lombok.Getter;

import static com.kien.dddsample.infrastructure.constant.CardConstant.USED;

@Getter
public class CardDomain implements Entity<CardDomain> {
    private CardCode code;
    private Integer money;
    private Integer status;

    public CardDomain(CardCode code, Integer money, Integer status) {
        this.code = code;
        this.money = money;
        this.status = status;
    }

    public void use() {
        if (this.status == USED) {
            throw new IllegalStateException("This card has been used");
        }
        this.status = USED;
    }

    @Override
    public boolean sameIdentityAs(CardDomain other) {
        return code.sameValueAs(other.code);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CardDomain that = (CardDomain) o;
        return this.sameIdentityAs(that);
    }

    @Override
    public int hashCode() {
        int result = code.hashCode();
        result = 31 * result + money.hashCode();
        result = 31 * result + status.hashCode();
        return result;
    }
}
