package com.kien.dddsample.domain.card;

import com.kien.dddsample.domain._shared.ValueObject;
import lombok.Getter;
import lombok.NonNull;

@Getter
public class CardCode implements ValueObject<CardCode> {

    private String code;

    public CardCode(@NonNull final String code) {
        this.code = code;
    }

    @Override
    public boolean sameValueAs(CardCode other) {
        return other != null && code.equals(other.code);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CardCode other = (CardCode) o;
        return sameValueAs(other);
    }

    @Override
    public int hashCode() {
        return code.hashCode();
    }

    @Override
    public String toString() {
        return code;
    }
}
