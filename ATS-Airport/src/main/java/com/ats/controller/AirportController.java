package com.ats.controller;

import com.ats.model.AirportDTO;
import com.ats.service.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ats/airports")
public class AirportController {
    private final AirportService airportService;

    @Autowired
    public AirportController(AirportService airportService) {
        this.airportService = airportService;
    }

    @GetMapping
    public List<AirportDTO> getAllAirports() {
        return airportService.getAllAirports();
    }

    @PostMapping
    public AirportDTO addAirport(@RequestBody AirportDTO airportDTO) {
        validateCountryCode(airportDTO.getCountryCode());
        return airportService.addAirport(airportDTO);
    }

    private void validateCountryCode(String countryCode) {
        if (countryCode == null || countryCode.isEmpty() || countryCode.length() > 5) {
            throw new IllegalArgumentException("Country code must be between 1 and 5 characters");
        }
    }

}
