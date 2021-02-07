package com.udacity.jdnd.course3.critter.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Schedule {

    @Id
    @GeneratedValue
    private Long id;
}

