package com.kien.dddsample.domain.location;

import com.kien.dddsample.domain._shared.Entity;
import lombok.Getter;
import lombok.NonNull;

@Getter
public class LocationDomain implements Entity<LocationDomain> {

    private LocationCode code;
    private String name;

    LocationDomain() {
    }

    public static final LocationDomain UNKNOWN = new LocationDomain(
            new LocationCode("XXXXX"), "Unknown location"
    );

    public LocationDomain(@NonNull LocationCode code, @NonNull String name) {
        this.code = code;
        this.name = name;
    }

    @Override
    public boolean sameIdentityAs(LocationDomain other) {
        return code.sameValueAs(other.code);
    }
}
