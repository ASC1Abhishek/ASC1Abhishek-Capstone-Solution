package com.ats.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FlightDTO {
    private String id;
    private String flightNumber;
    private String departureAirport;
    private String arrivalAirport;
    private String departureTime;
    private String arrivalTime;
}