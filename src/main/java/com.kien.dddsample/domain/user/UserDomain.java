package com.kien.dddsample.domain.user;

import com.kien.dddsample.domain._shared.Entity;
import com.kien.dddsample.domain.tour.TourDomain;
import lombok.Getter;
import lombok.NonNull;

import java.util.List;

import static com.kien.dddsample.infrastructure.constant.TourConstant.STATUS_FINISHED;

@Getter
public class UserDomain implements Entity<UserDomain> {
    private UserCode code;
    private String username;
    private String password;
    private String name;
    private String email;
    private String phone;
    private Integer balance;
    private List<TourDomain> tours;


    public UserDomain(@NonNull UserCode code, @NonNull String username, @NonNull String password, @NonNull String name,
                      @NonNull String email, @NonNull String phone, @NonNull Integer balance,
                      @NonNull List<TourDomain> tours) {
        this.code = code;
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.balance = balance;
        this.tours = tours;
    }

    public void bookTour(TourDomain tour) {
        for (TourDomain item : tours) {
            if (item.getStatus() != STATUS_FINISHED) {
                if (item.getEndDate().after(tour.getStartDate()) || item.getStartDate().before(tour.getEndDate())) {
                    throw new IllegalStateException("Conflict time with tour " + item.getId().getCode());
                }
            }
        }
        pay(tour.getCost());
        tour.addMember(this);
        tours.add(tour);
    }

    public void deposit(@NonNull Integer amount) {
        if (amount <= 0)
            throw new IllegalArgumentException("Invalid deposit amount");
        balance += amount;
    }



    private void pay(@NonNull Integer amount) {
        if (amount <= 0)
            throw new IllegalArgumentException("Invalid deposit amount");
        if (balance < amount)
            throw new RuntimeException("Your account has not enough money");
        balance -= amount;
    }

    @Override
    public boolean sameIdentityAs(UserDomain other) {
        return code.sameValueAs(other.code);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDomain domain = (UserDomain) o;
        return domain.sameIdentityAs(domain);
    }

    @Override
    public int hashCode() {
        int result = code != null ? code.hashCode() : 0;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        return result;
    }
}
