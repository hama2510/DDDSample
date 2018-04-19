package com.kien.dddsample.domain._shared;

public interface Entity<T> {
    boolean sameIdentityAs(T other);
}
