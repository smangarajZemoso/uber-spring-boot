package com.app.mycoolapp.rest;

import com.app.mycoolapp.entity.Driver;
import com.app.mycoolapp.entity.Passenger;
import com.app.mycoolapp.dto.ResponseModel;
import com.app.mycoolapp.dto.SignupModel;
import com.app.mycoolapp.service.DriverService;
import com.app.mycoolapp.service.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/signup")
public class SignupRestController {

    @Autowired
    public DriverService driverService;

    @Autowired
    public PassengerService passengerService;

    @PostMapping
    @ResponseBody
    public ResponseModel signup(@RequestBody SignupModel signupModel) {
        ResponseModel responseModel = new ResponseModel();
        if (signupModel.type.equals("driver")) {
            Driver driver = driverService.signup(signupModel);
            responseModel.setStatus(200);
            responseModel.setMessage("Driver Signup Successful");
            responseModel.setDataObject(driver);
            return responseModel;
        } else if (signupModel.type.equals("passenger")) {
            Passenger passenger = passengerService.signup(signupModel);
            responseModel.setStatus(200);
            responseModel.setMessage("Passenger Signup Successful");
            responseModel.setDataObject(passenger);
            return responseModel;
        } else {
            responseModel.setStatus(500);
            responseModel.setMessage("Signup Failed Due to invalid type of user");
            throw new RuntimeException("This is not a valid type of User");
        }
    }
}
