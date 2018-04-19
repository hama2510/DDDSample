package com.kien.dddsample.domain.location;

import com.kien.dddsample.domain._shared.ValueObject;
import lombok.Getter;
import lombok.NonNull;

@Getter
public class LocationCode implements ValueObject<LocationCode> {
    private String code;

    public LocationCode(@NonNull final String code) {
        this.code = code;
    }

    @Override
    public boolean sameValueAs(LocationCode other) {
        return other != null && code.equals(other.code);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LocationCode other = (LocationCode) o;
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
