package com.app.mycoolapp.rest;

import com.app.mycoolapp.entity.Driver;
import com.app.mycoolapp.entity.Passenger;
import com.app.mycoolapp.entity.Trip;
import com.app.mycoolapp.dto.BookTripModel;
import com.app.mycoolapp.dto.ResponseModel;
import com.app.mycoolapp.service.DriverService;
import com.app.mycoolapp.service.PassengerService;
import com.app.mycoolapp.service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/api/v1")
public class TripRestController {

    @Autowired
    public TripService tripService;

    @Autowired
    public DriverService driverService;

    @Autowired
    public PassengerService passengerService;

    @PostMapping("/booktrip")
    public ResponseModel bookTrip(@RequestBody BookTripModel bookTripModel) {
        ResponseModel responseModel = new ResponseModel();
        responseModel.setStatus(500);
        // PassengerID Check (status = "inactive")
        if (!passengerService.checkPassengerStatus(bookTripModel.passengerId)) {
            responseModel.setMessage("Booking can't be Possible as Passenger is already on a ride");
            return responseModel;
        }
        // DriverID Retrieval from all available drivers (status = "inactive") && Select First Available Driver
        long driver_id = driverService.getSingleAvailableDriver();
        Driver driver = new Driver();
        driver.setId(driver_id);
        Passenger passenger = new Passenger();
        passenger.setId(bookTripModel.passengerId);
        Trip trip = tripService.bookTrip(bookTripModel, driver, passenger);
        // Update Driver Table  status = "active"
        String status = "active";
        driver.setStatus(status);
        driverService.updateDriver(driver);
        // Update Passenger Table status = "active"
        passenger.setStatus(status);
        passengerService.updatePassenger(passenger);
        responseModel.setMessage("Trip Booked Successfully");
        responseModel.setDataObject(trip);
        responseModel.setStatus(200);
        return responseModel;
    }

    @PutMapping("/completetrip/{tripId}")
    public ResponseModel completeTrip(@PathVariable long tripId) {
        ResponseModel responseModel = new ResponseModel();
        responseModel.setStatus(500);
        // Update Trip Table (trip_end_time, status = 'complete')
        Trip trip = new Trip();
        trip.setId(tripId);
        trip.setStatus("complete");
        trip.setEndTime(new Date().toString());
        Trip updatedTrip = tripService.updateTrip(trip);
        long driver_id = updatedTrip.getDriver().getId();
        long passenger_id = updatedTrip.getPassenger().getId();
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
        responseModel.setStatus(200);
        responseModel.setMessage("Successfully Cancelled the Trip" + tripId);
        responseModel.setDataObject(trip);
        return responseModel;
    }

    @PutMapping("/canceltrip/{tripId}")
    public ResponseModel cancelTrip(@PathVariable long tripId) {
        ResponseModel responseModel = new ResponseModel();
        responseModel.setStatus(500);
        // Update Trip Table (trip_end_time, status = 'cancel')
        Trip trip = new Trip();
        trip.setId(tripId);
        trip.setStatus("cancel");
        trip.setEndTime(new Date().toString());
        Trip updatedTrip = tripService.updateTrip(trip);
        long driver_id = updatedTrip.getDriver().getId();
        long passenger_id = updatedTrip.getPassenger().getId();
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
        responseModel.setStatus(200);
        responseModel.setMessage("Successfully Cancelled the Trip" + tripId);
        responseModel.setDataObject(trip);
        return responseModel;
    }

    @GetMapping("/trips/{type}/{id}")
    public ResponseModel getTripDetails(@PathVariable String type, @PathVariable String id) {
        ResponseModel responseModel = new ResponseModel();
        responseModel.setStatus(500);
        if (type.equals("driver")) {
            // Filter Trip Table based on driver_id
        } else if (type.equals("passenger")) {
            // Filter Trip Table based on passenger_id
        } else {
            responseModel.setMessage("Type is provided wrongly");
            return responseModel;
        }
        responseModel.setStatus(200);
        responseModel.setMessage("Successfully Get the Trip Details");
        return responseModel;
    }

    @GetMapping("/activetrip/{type}/{id}")
    public ResponseModel getActiveTripDetails(@PathVariable String type, @PathVariable long id) {
        ResponseModel responseModel = new ResponseModel();
        responseModel.setStatus(500);
        Trip trip = new Trip();
        trip.setStatus("active");
        if (type.equals("driver")) {
            // Filter Trip Table based on driver_id and status = 'active'
            Driver driver = new Driver();
            driver.setId(id);
            trip.setDriver(driver);
            tripService.findAllByTripObject(trip);
        } else if (type.equals("passenger")) {
            // Filter Trip Table based on passenger_id and status = 'active'
            Passenger passenger = new Passenger();
            passenger.setId(id);
            trip.setPassenger(passenger);
            tripService.findAllByTripObject(trip);
        } else {
            responseModel.setMessage("Type is provided wrongly");
            return responseModel;
        }
        responseModel.setStatus(200);
        responseModel.setMessage("Successfully Get the Active Trip Details");
        return responseModel;
    }
}
