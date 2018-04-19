package com.kien.dddsample.infrastructure.service.converter;

import com.kien.dddsample.domain.tour.TourDomain;
import com.kien.dddsample.infrastructure.dto.TourDto;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;

import static com.kien.dddsample.infrastructure.constant.TourConstant.*;

@Service
public class TourConverter {
    public TourDto convert(TourDomain domain) {
        TourDto tour = new TourDto();
        tour.setId(domain.getId().getCode());
        tour.setCost(domain.getCost());
        tour.setDescription(domain.getDescription());
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        tour.setEndTime(format.format(domain.getEndDate()));
        tour.setStartTime(format.format(domain.getStartDate()));
        tour.setStartLocation(domain.getStartLocation().getName());
        tour.setEndLocation(domain.getEndLocation().getName());
        tour.setMaxMember(domain.getMaxMember());
        if (domain.getStatus().equals(STATUS_OPEN)) {
            tour.setStatus("Mở");
        } else if (domain.getStatus().equals(STATUS_CLOSE)) {
            tour.setStatus("Đóng");
        } else if (domain.getStatus().equals(STATUS_STARTED)) {
            tour.setStatus("Đã bắt đầu");
        } else if (domain.getStatus().equals(STATUS_FINISHED)) {
            tour.setStatus("Đã kết thúc");
        }
        return tour;
    }
}
