package com.kien.dddsample.infrastructure.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "card", schema = "public")
@Getter
@Setter
public class Card {
    @Id
    private String serial;
    private Integer money;
    private Integer status;
}