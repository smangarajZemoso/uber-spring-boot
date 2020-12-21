package com.app.mycoolapp.rest;

import com.app.mycoolapp.entity.Driver;
import com.app.mycoolapp.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class DriverRestController {

    @Autowired
    public DriverService driverService;

    @GetMapping("/drivers")
    @ResponseBody
    public ResponseEntity<Object> findAll() {
        return ResponseEntity.ok(driverService.findAll());
//        return ResponseEntity.noContent().build();
    }

    @GetMapping("/availabledrivers")
    @ResponseBody
    public List<Driver> findAvailableDrivers() {
        return driverService.findAvailableDrivers();
    }
}
