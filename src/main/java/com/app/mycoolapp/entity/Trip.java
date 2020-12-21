package com.app.mycoolapp.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Trip")
@Getter
@Setter
public class Trip implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private long id;

    @Column(name = "start_loc_id")
    private String startLocId;

    @Column(name = "end_loc_id")
    private String endLocId;

    @Column(name = "start_time")
    private String startTime;

    @Column(name = "end_time")
    private String endTime;

    @Column(name = "status")
    private String status;

    @ManyToOne
    private Driver driver;

    @ManyToOne
    private Passenger passenger;

}
