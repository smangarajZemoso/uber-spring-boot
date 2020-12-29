package com.app.mycoolapp.service;

import com.app.mycoolapp.dto.BookTripModel;
import com.app.mycoolapp.dto.TripResponse;
import com.app.mycoolapp.entity.Driver;
import com.app.mycoolapp.entity.Passenger;
import com.app.mycoolapp.entity.Trip;
import com.app.mycoolapp.mapper.EntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TripOperationServiceImpl implements TripOperationService {

    @Autowired
    public PassengerService passengerService;

    @Autowired
    public DriverService driverService;

    @Autowired
    public TripService tripService;

    @Autowired
    public EntityMapper entityMapper;

    @Override
    public TripResponse canceltrip(long tripId) {
        // Update Trip Table (trip_end_time, status = 'cancel')
        Trip trip = new Trip();
        trip.setId(tripId);
        trip.setStatus("cancel");
        trip.setEndTime(new Date().toString());
        TripResponse tripResponse = entityMapper.tripToTripResponse(tripService.updateTrip(trip));
        long driver_id = tripResponse.getDriverResponse().getId();
        long passenger_id = tripResponse.getPassengerResponse().getId();
        // Update Driver Table (status='inactive') where driver_id is given
        Driver driver = new Driver();
        driver.setStatus("inactive");
        driver.setId(driver_id);
        driverService.updateDriver(driver);
        // Update Passenger Table (status='inactive') where passenger_id is given
        Passenger passenger = new Passenger();
        passenger.setStatus("inactive");
        passenger.setId(passenger_id);
        passengerService.updatePassenger(passenger);
        return tripResponse;
    }

    @Override
    public TripResponse completetrip(long tripId) {
        // Update Trip Table (trip_end_time, status = 'complete')
        Trip trip = new Trip();
        trip.setId(tripId);
        trip.setStatus("complete");
        trip.setEndTime(new Date().toString());
        TripResponse tripResponse = entityMapper.tripToTripResponse(tripService.updateTrip(trip));
        long driver_id = tripResponse.getDriverResponse().getId();
        long passenger_id = tripResponse.getPassengerResponse().getId();
        // Update Driver Table (status='inactive') where driver_id is given
        Driver driver = new Driver();
        driver.setStatus("inactive");
        driver.setId(driver_id);
        driverService.updateDriver(driver);
        // Update Passenger Table (status='inactive') where passenger_id is given
        Passenger passenger = new Passenger();
        passenger.setStatus("inactive");
        passenger.setId(passenger_id);
        passengerService.updatePassenger(passenger);
        return tripResponse;
    }


    @Override
    public TripResponse booktrip(BookTripModel bookTripModel) {

        // PassengerID Check (status = "inactive")
        if (!passengerService.checkPassengerStatus(bookTripModel.getPassengerId())) {
            throw new RuntimeException("Booking can't be Possible as Passenger is already on a ride");
        }

        // DriverID Retrieval from all available drivers (status = "inactive") && Select First Available Driver
        long driver_id = driverService.getSingleAvailableDriver();

        Driver driver = new Driver();
        driver.setId(driver_id);

        Passenger passenger = new Passenger();
        passenger.setId(bookTripModel.getPassengerId());

        // Insert into Trip Table (driver_id,passenger_id,start_loc_id,end_loc_id,start_time,status="active") , Foreign Key Reference Check
        Trip trip = new Trip();
        trip.setStatus("active");
        trip.setStartLocId(bookTripModel.getStartLocId());
        trip.setEndLocId(bookTripModel.getEndLocId());
        trip.setStartTime(new Date().toString());
        trip.setDriver(driver);
        trip.setPassenger(passenger);
        TripResponse tripResponse = entityMapper.tripToTripResponse(tripService.save(trip));

        String status = "active";
        // Update Driver Table  status = "active"
        driver.setStatus(status);
        driverService.updateDriver(driver);

        // Update Passenger Table status = "active"
        passenger.setStatus(status);
        passengerService.updatePassenger(passenger);


        return tripResponse;
    }
}
