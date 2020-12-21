package com.app.mycoolapp.service;

import com.app.mycoolapp.dao.TripRepository;
import com.app.mycoolapp.dto.BookTripModel;
import com.app.mycoolapp.entity.Driver;
import com.app.mycoolapp.entity.Passenger;
import com.app.mycoolapp.entity.Trip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TripServiceImpl implements TripService {

    @Autowired
    public TripRepository tripRepository;

    @Override
    public List<Trip> findAll() {
        return tripRepository.findAll();
    }

    @Override
    public Trip findById(long theId) {
        return null;
    }

    @Override
    public void save(Trip theTrip) {
        tripRepository.save(theTrip);
    }

    @Override
    public void deleteById(long theId) {

    }

    @Override
    public Trip updateTrip(Trip trip) {
        Optional<Trip> result = tripRepository.findById(trip.getId());
        Trip theTrip;
        if (result.isPresent()) {
            theTrip = result.get();
            theTrip.setStatus(trip.getStatus());
            theTrip.setEndTime(trip.getEndTime());
            tripRepository.save(theTrip);
            return theTrip;
        } else {
            throw new RuntimeException("Did not find Passenger ID" + trip.getId());
        }
    }

    @Override
    public List<Trip> findAllByTripObject(Trip theTrip) {
        return null;
    }

    @Override
    public Trip bookTrip(BookTripModel bookTripModel, Driver driver, Passenger passenger) {
        // Insert into Trip Table (driver_id,passenger_id,start_loc_id,end_loc_id,start_time,status="active") , Foreign Key Reference Check
        Trip trip = new Trip();
        trip.setStatus("active");
        trip.setStartLocId(bookTripModel.startLocId);
        trip.setEndLocId(bookTripModel.endLocId);
        trip.setStartTime(new Date().toString());
        trip.setDriver(driver);
        trip.setPassenger(passenger);
        this.save(trip);
        return trip;
    }
}
