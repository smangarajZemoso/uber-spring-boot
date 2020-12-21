package com.app.mycoolapp.service;

import com.app.mycoolapp.dao.DriverRepository;
import com.app.mycoolapp.entity.Driver;
import com.app.mycoolapp.dto.SignupModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DriverServiceImpl implements DriverService {

    @Autowired
    public DriverRepository driverRepository;
    
    @Override
    public List<Driver> findAll() {
        return driverRepository.findAll();
    }

    @Override
    public Driver findById(long theId) {
        Optional<Driver> result = driverRepository.findById(theId);
        Driver theDriver;
        if (result.isPresent()) {
            theDriver = result.get();
        } else {
            throw new RuntimeException("Did not find Driver ID" + theId);
        }
        return theDriver;
    }

    @Override
    public void save(Driver theDriver) {
        driverRepository.save(theDriver);
    }

    @Override
    public void deleteById(long theId) {
        driverRepository.deleteById(theId);
    }

    @Override
    public long getSingleAvailableDriver() {
        List<Driver> drivers = driverRepository.findFirst1ByStatusContainingIgnoreCase("inactive");
//        System.out.println(drivers);
        return drivers.get(0).getId();
    }

    @Override
    public void updateDriver(Driver driver) {
        Optional<Driver> result = driverRepository.findById(driver.getId());
        Driver theDriver;
        if (result.isPresent()) {
            theDriver = result.get();
            theDriver.setStatus(driver.getStatus());
            driverRepository.save(theDriver);
        } else {
            throw new RuntimeException("Did not find Driver ID" + driver.getId());
        }
    }

    @Override
    public List<Driver> findAvailableDrivers() {
        return driverRepository.findByStatusContainingIgnoreCase("inactive");
    }

    @Override
    public Driver signup(SignupModel signupModel) {
        Driver driver = new Driver();
        driver.setId(0);
        driver.setName(signupModel.name);
        driver.setGender(signupModel.gender);
        driver.setPhoneNo(signupModel.phoneNo);
        driver.setCabId(signupModel.cabId);
        driver.setStatus("inactive");
        this.save(driver);
        return driver;
    }
}
