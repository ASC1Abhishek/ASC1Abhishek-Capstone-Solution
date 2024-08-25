package com.ats.service;

import com.ats.model.PassengerDTO;

import java.util.List;

public interface PassengerService {
    PassengerDTO createPassenger(PassengerDTO passengerDTO);
    PassengerDTO updatePassenger(String id, PassengerDTO passengerDTO);
    void deletePassenger(String id);
    PassengerDTO getPassengerById(String id);
    List<PassengerDTO> getAllPassengers();
}
