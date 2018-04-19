package com.kien.dddsample.infrastructure.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "location", schema = "public")
@Getter
@Setter
public class Location {
    @Id
    private String id;
    private String name;
    @OneToMany(mappedBy = "startLocation")
    private List<Tour> tours;
    @OneToMany(mappedBy = "endLocation")
    private List<Tour> toursEndAt;
}
