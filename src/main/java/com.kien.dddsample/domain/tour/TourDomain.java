package com.kien.dddsample.domain.tour;

import com.kien.dddsample.domain._shared.Entity;
import com.kien.dddsample.domain.location.LocationDomain;
import com.kien.dddsample.domain.user.UserDomain;
import lombok.Getter;
import lombok.NonNull;

import java.util.Date;
import java.util.List;

import static com.kien.dddsample.infrastructure.constant.TourConstant.STATUS_CLOSE;
import static com.kien.dddsample.infrastructure.constant.TourConstant.STATUS_OPEN;


@Getter
public class TourDomain implements Entity<TourDomain> {

    private TourCode id;
    private Date startDate;
    private Date endDate;
    private LocationDomain startLocation;
    private LocationDomain endLocation;
    private Integer cost;
    private String description;
    private Integer status;
    private Integer maxMember;
    private Date createdAt;
    private List<UserDomain> members;

    public TourDomain(@NonNull TourCode id, @NonNull Date startDate, @NonNull Date endDate,
                      @NonNull LocationDomain startLocation,
                      @NonNull LocationDomain endLocation, @NonNull Integer cost, String description,
                      @NonNull Integer status, @NonNull Integer maxMember, @NonNull Date createdAt,
                      @NonNull List<UserDomain> members) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.startLocation = startLocation;
        this.endLocation = endLocation;
        this.cost = cost;
        this.description = description;
        this.status = status;
        this.maxMember = maxMember;
        this.createdAt = createdAt;
        this.members = members;
    }

    @Override
    public boolean sameIdentityAs(TourDomain other) {
        return false;
    }

    private boolean isExceedMaxMember() {
        return members.size() >= maxMember;
    }

    private boolean isOpen() {
        return status == STATUS_OPEN;
    }

    private boolean isExistedMember(UserDomain userDomain) {
        return members.contains(userDomain);
    }

    public void addMember(UserDomain user) {
        if (isExistedMember(user)) {
            throw new RuntimeException("User with code " + user.getCode().getCode() + " has already take part in this tour");
        }
        if (!isOpen()) {
            throw new RuntimeException("This tour is not open for booking now");
        }
        if (isExceedMaxMember()) {
            throw new RuntimeException("The members of this tour is exceed the maximum number");
        }
        members.add(user);
        if (members.size() == maxMember) {
            status = STATUS_CLOSE;
        }
    }
}
