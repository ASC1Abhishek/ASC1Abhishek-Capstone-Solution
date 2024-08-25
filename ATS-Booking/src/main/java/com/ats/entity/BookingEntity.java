package com.ats.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "booking")
public class BookingEntity {

    @Id
    @Column(nullable = false, unique = true)
    private String id;  // Format: B0001, to be generated manually

    @Column(nullable = false)
    private String passengerName;

    @Column(nullable = false)
    private LocalDate bookingDate;

    @Column(nullable = false)
    private int seatNumber;

    @Column(nullable = false)
    private int ticketCost;

    @Column(nullable = false)
    private int totalAmount;

    @Column(nullable = false)
    private String departureDate;

    @Column(nullable = false)
    private String departureTime;

    @Column(nullable = false)
    private String arrivalDate;

    @Column(nullable = false)
    private String arrivalTime;
}
