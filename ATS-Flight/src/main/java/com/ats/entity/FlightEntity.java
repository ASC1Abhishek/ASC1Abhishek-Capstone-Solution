package com.ats.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "flight")
public class FlightEntity {
    @Id
    @Column(unique = true)
    private String id;

    @Column(nullable = false)
    private String flightNumber;

    @Column(nullable = false)
    private String departureAirport;

    @Column(nullable = false)
    private String arrivalAirport;

    @Column(nullable = false)
    private String departureTime;

    @Column(nullable = false)
    private String arrivalTime;
}