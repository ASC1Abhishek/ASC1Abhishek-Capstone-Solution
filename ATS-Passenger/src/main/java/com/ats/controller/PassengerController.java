package com.ats.controller;

import com.ats.model.PassengerDTO;
import com.ats.service.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ats/passengers")
public class PassengerController {

    private final PassengerService passengerService;

    @Autowired
    public PassengerController(PassengerService passengerService) {
        this.passengerService = passengerService;
    }

    @PostMapping("/add-passenger")
    public ResponseEntity<Object> createPassenger(@RequestBody PassengerDTO passengerDTO) {
        passengerService.createPassenger(passengerDTO);
        return ResponseEntity.status(HttpStatus.FOUND).header(HttpHeaders.LOCATION, "http://localhost:8042/ats/bookings").build();
    }

    @PutMapping("/{id}")
    public PassengerDTO updatePassenger(@PathVariable String id, @RequestBody PassengerDTO passengerDTO) {
        return passengerService.updatePassenger(id, passengerDTO);
    }

    @DeleteMapping("/{id}")
    public void deletePassenger(@PathVariable String id) {
        passengerService.deletePassenger(id);
    }

    @GetMapping("/{id}")
    public PassengerDTO getPassengerById(@PathVariable String id) {
        return passengerService.getPassengerById(id);
    }

    @GetMapping("/all")
    public List<PassengerDTO> getAllPassengers() {
        return passengerService.getAllPassengers();
    }
}
