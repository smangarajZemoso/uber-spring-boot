package com.app.mycoolapp.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "DriverT")
@Data
public class Driver extends Actor implements Serializable {

    @Column(name = "cab_id")
    private String cabId;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "driver_id")
    private Set<Trip> trip;

}
