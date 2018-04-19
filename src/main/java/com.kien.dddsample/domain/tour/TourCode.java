package com.kien.dddsample.domain.tour;

import com.kien.dddsample.domain._shared.ValueObject;
import lombok.Getter;
import lombok.NonNull;

@Getter
public class TourCode implements ValueObject<TourCode> {
    private String code;

    public TourCode(@NonNull final String code) {
        this.code = code;
    }

    @Override
    public boolean sameValueAs(TourCode other) {
        return other != null && code.equals(other.code);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TourCode other = (TourCode) o;
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
