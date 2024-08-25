package com.ats.service;

import com.ats.model.FlightDTO;

import java.util.List;

public interface FlightService {
    FlightDTO createFlight(FlightDTO flightDTO);
    FlightDTO updateFlight(String id, FlightDTO flightDTO);
    void deleteFlight(String id);
    FlightDTO getFlightById(String id);
    List<FlightDTO> getAllFlights();
}