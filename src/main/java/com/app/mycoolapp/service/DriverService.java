package com.app.mycoolapp.service;

import com.app.mycoolapp.entity.Driver;
import com.app.mycoolapp.dto.SignupModel;

import java.util.List;

public interface DriverService {

    List<Driver> findAll();

    Driver findById(long theId);

    void save(Driver theDriver);

    void deleteById(long theId);

    long getSingleAvailableDriver();

    void updateDriver(Driver theDriver);

    List<Driver> findAvailableDrivers();

    Driver signup(SignupModel signupModel);
}
