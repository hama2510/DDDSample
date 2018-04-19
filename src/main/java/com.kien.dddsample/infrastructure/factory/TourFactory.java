package com.kien.dddsample.infrastructure.factory;

import com.kien.dddsample.domain.location.ILocationRepository;
import com.kien.dddsample.domain.location.LocationDomain;
import com.kien.dddsample.domain.tour.TourCode;
import com.kien.dddsample.domain.tour.TourDomain;
import com.kien.dddsample.domain.user.IUserRepository;
import com.kien.dddsample.domain.user.UserDomain;
import com.kien.dddsample.infrastructure.dto.TourRegistration;
import com.kien.dddsample.infrastructure.model.Tour;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static com.kien.dddsample.infrastructure.constant.TourConstant.STATUS_OPEN;

@Service
public class TourFactory {

    private SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
    @Autowired
    private ILocationRepository ILocationRepository;
    @Autowired
    private IUserRepository userRepository;

    public TourDomain create(TourRegistration registration) {
        try {
            Date startDate = format.parse(registration.getStartDate());
            Date endDate = format.parse(registration.getEndDate());
            if (startDate.compareTo(endDate) >= 0) {
                throw new IllegalArgumentException("End date must after start date");
            }
            LocationDomain startLocation = ILocationRepository.get(registration.getStartLocation());
            LocationDomain endLocation = ILocationRepository.get(registration.getEndLocation());
            if (startLocation.sameIdentityAs(LocationDomain.UNKNOWN) || endLocation.sameIdentityAs(LocationDomain.UNKNOWN)) {
                throw new IllegalArgumentException("Invalid location");
            }
            return new TourDomain(new TourCode(UUID.randomUUID().toString()), startDate, endDate, startLocation,
                    endLocation, registration.getCost(), registration.getDescription(), STATUS_OPEN,
                    registration.getMaxMember(), new Date(), new ArrayList<>());
        } catch (ParseException e) {
            throw new IllegalArgumentException("Invalid date format");
        }
    }

    public TourDomain build(@NonNull Tour tour) {
        LocationDomain start = ILocationRepository.get(tour.getStartLocation().getId());
        LocationDomain end = ILocationRepository.get(tour.getEndLocation().getId());
        List<UserDomain> users = new ArrayList<>();
        tour.getUsers().forEach((item) ->
                users.add(userRepository.get(item.getId()))
        );
        return new TourDomain(new TourCode(tour.getId()), tour.getStartTime(), tour.getEndTime(), start, end,
                tour.getCost(), tour.getDescription(), tour.getStatus(), tour.getMaxMember(), tour.getCreatedAt(), users);
    }
}
