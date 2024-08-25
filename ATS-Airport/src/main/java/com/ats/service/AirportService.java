package com.ats.service;

import com.ats.model.AirportDTO;

import java.util.List;

public interface AirportService {
    List<AirportDTO> getAllAirports();
    AirportDTO addAirport(AirportDTO airportDTO);
}
