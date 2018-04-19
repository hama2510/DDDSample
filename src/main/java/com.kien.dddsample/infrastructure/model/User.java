package com.kien.dddsample.infrastructure.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users", schema = "public")
@Getter
@Setter
public class User {
    @Id
    private String id;
    private String name;
    private String email;
    private String phone;
    private String username;
    private String password;
    private Integer balance;
    @JoinTable(name = "tour_member", joinColumns = {
            @JoinColumn(name = "user_id", referencedColumnName = "id")}, inverseJoinColumns = {
            @JoinColumn(name = "tour_id", referencedColumnName = "id")})
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Tour> tours = new ArrayList<>();

    public User() {
    }

    public User(String id) {
        this.id = id;
    }
}
