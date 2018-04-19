package com.kien.dddsample.domain.user;

import com.kien.dddsample.domain._shared.ValueObject;
import lombok.Getter;
import lombok.NonNull;

@Getter
public class UserCode implements ValueObject<UserCode> {
    private String code;

    public UserCode(@NonNull final String code) {
        this.code = code;
    }

    @Override
    public boolean sameValueAs(UserCode other) {
        return other != null && code.equals(other.code);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserCode other = (UserCode) o;
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
