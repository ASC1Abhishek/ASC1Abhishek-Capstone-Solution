package com.ats.controller;

import com.ats.model.FlightDTO;
import com.ats.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ats/flights")
public class FlightController {

    private final FlightService flightService;

    @Autowired
    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @PostMapping("/create")
    public FlightDTO createFlight(@RequestBody FlightDTO flightDTO) {
        return flightService.createFlight(flightDTO);
    }

    @PutMapping("/{id}")
    public FlightDTO updateFlight(@PathVariable String id, @RequestBody FlightDTO flightDTO) {
        return flightService.updateFlight(id, flightDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteFlight(@PathVariable String id) {
        flightService.deleteFlight(id);
    }

    @GetMapping("/{id}")
    public FlightDTO getFlightById(@PathVariable String id) {
        return flightService.getFlightById(id);
    }

    @GetMapping
    public List<FlightDTO> getAllFlights() {
        return flightService.getAllFlights();
    }
}
