package com.app.mycoolapp.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "PassengerT")
@Data
public class Passenger extends Actor implements Serializable {

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "passenger_id")
    private Set<Trip> trip;

}
