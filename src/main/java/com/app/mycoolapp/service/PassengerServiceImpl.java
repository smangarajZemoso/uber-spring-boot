package com.app.mycoolapp.service;

import com.app.mycoolapp.dao.PassengerRepository;
import com.app.mycoolapp.entity.Passenger;
import com.app.mycoolapp.dto.SignupModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PassengerServiceImpl implements PassengerService {

    @Autowired
    public PassengerRepository passengerRepository;

    @Override
    public List<Passenger> findAll() {
        return passengerRepository.findAll();
    }

    @Override
    public Passenger findById(long theId) {
        Optional<Passenger> result = passengerRepository.findById(theId);
        Passenger thePassenger;
        if (result.isPresent()) {
            thePassenger = result.get();
        } else {
            throw new RuntimeException("Did not find Passenger ID" + theId);
        }
        return thePassenger;
    }

    @Override
    public Passenger save(Passenger thePassenger) {
        return passengerRepository.save(thePassenger);
    }

    @Override
    public void deleteById(long theId) {
        passengerRepository.deleteById(theId);
    }

    @Override
    public boolean checkPassengerStatus(long theId) {
        Optional<Passenger> result = passengerRepository.findById(theId);
        Passenger thePassenger;
        if (result.isPresent()) {
            thePassenger = result.get();
            return thePassenger.getStatus().equals("inactive");
        } else {
            throw new RuntimeException("Did not find Passenger ID" + theId);
        }
    }

    @Override
    public void updatePassenger(Passenger passenger) {
        Optional<Passenger> result = passengerRepository.findById(passenger.getId());
        Passenger thePassenger;
        if (result.isPresent()) {
            thePassenger = result.get();
            thePassenger.setStatus(passenger.getStatus());
            passengerRepository.save(thePassenger);
        } else {
            throw new RuntimeException("Did not find Passenger ID" + passenger.getId());
        }
    }

    @Override
    public List<Passenger> findAvailablePassengers() {
        return passengerRepository.findByStatusContainingIgnoreCase("inactive");
    }

    @Override
    public Passenger signup(SignupModel signupModel) {
        Passenger passenger = new Passenger();
        passenger.setId(0);
        passenger.setName(signupModel.getName());
        passenger.setGender(signupModel.getGender());
        passenger.setPhoneNo(signupModel.getPhoneNo());
        passenger.setStatus("inactive");
        return this.save(passenger);
    }
}
