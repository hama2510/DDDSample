package com.kien.dddsample.infrastructure.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "tour", schema = "public")
@Getter
@Setter
public class Tour {
    @Id
    private String id;
    @Column(name = "start_time")
    private Date startTime;
    @Column(name = "end_time")
    private Date endTime;
    private Integer cost;
    private String description;
    private Integer status;
    @Column(name = "created_at")
    private Date createdAt;
    @Column(name = "max_member")
    private Integer maxMember;
    @JoinColumn(name = "start_location_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Location startLocation;
    @JoinColumn(name = "end_location_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Location endLocation;
    @JoinTable(name = "tour_member", joinColumns = {
            @JoinColumn(name = "tour_id", referencedColumnName = "id")}, inverseJoinColumns = {
            @JoinColumn(name = "user_id", referencedColumnName = "id")})
    @ManyToMany(fetch = FetchType.LAZY)
    private List<User> users = new ArrayList<>();
}
