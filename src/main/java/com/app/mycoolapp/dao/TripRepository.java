package com.app.mycoolapp.dao;

import com.app.mycoolapp.entity.Driver;
import com.app.mycoolapp.entity.Trip;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TripRepository extends JpaRepository<Trip, Long> {
//    List<Trip> findByDriver
//    List<Driver> findByStatusContainingIgnoreCase(String status);
}
