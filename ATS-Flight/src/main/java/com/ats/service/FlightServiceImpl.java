package com.ats.service;

import com.ats.entity.FlightEntity;
import com.ats.model.FlightDTO;
import com.ats.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FlightServiceImpl implements FlightService {

    private final FlightRepository flightRepository;

    @Autowired
    public FlightServiceImpl(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    @Override
    public FlightDTO createFlight(FlightDTO flightDTO) {
        FlightEntity flightEntity = convertDTOToEntity(flightDTO);
        flightEntity.setId(generateFlightId());

        FlightEntity savedEntity = flightRepository.save(flightEntity);
        return convertEntityToDTO(savedEntity);
    }

    @Override
    public FlightDTO updateFlight(String id, FlightDTO flightDTO) {
        Optional<FlightEntity> optionalFlight = flightRepository.findById(id);
        if (optionalFlight.isPresent()) {
            FlightEntity flightEntity = optionalFlight.get();
            updateEntityFromDTO(flightEntity, flightDTO);
            FlightEntity updatedEntity = flightRepository.save(flightEntity);
            return convertEntityToDTO(updatedEntity);
        } else {
            throw new IllegalArgumentException("Flight ID not found");
        }
    }

    @Override
    public void deleteFlight(String id) {
        flightRepository.deleteById(id);
    }

    @Override
    public FlightDTO getFlightById(String id) {
        Optional<FlightEntity> optionalFlight = flightRepository.findById(id);
        if (optionalFlight.isPresent()) {
            return convertEntityToDTO(optionalFlight.get());
        } else {
            throw new IllegalArgumentException("Flight ID not found");
        }
    }

    @Override
    public List<FlightDTO> getAllFlights() {
        List<FlightEntity> flightEntities = flightRepository.findAll();
        return flightEntities.stream()
                .map(this::convertEntityToDTO)
                .collect(Collectors.toList());
    }

    private String generateFlightId() {
        long count = flightRepository.count();
        return String.format("F%04d", count + 1);
    }

    private FlightEntity convertDTOToEntity(FlightDTO flightDTO) {
        FlightEntity flightEntity = new FlightEntity();
        dtoToEntity(flightDTO, flightEntity);
        return flightEntity;
    }

    private static void dtoToEntity(FlightDTO flightDTO, FlightEntity flightEntity) {
        flightEntity.setFlightNumber(flightDTO.getFlightNumber());
        flightEntity.setDepartureAirport(flightDTO.getDepartureAirport());
        flightEntity.setArrivalAirport(flightDTO.getArrivalAirport());
        flightEntity.setDepartureTime(flightDTO.getDepartureTime());
        flightEntity.setArrivalTime(flightDTO.getArrivalTime());
    }

    private FlightDTO convertEntityToDTO(FlightEntity flightEntity) {
        FlightDTO flightDTO = new FlightDTO();
        flightDTO.setId(flightEntity.getId());
        flightDTO.setFlightNumber(flightEntity.getFlightNumber());
        flightDTO.setDepartureAirport(flightEntity.getDepartureAirport());
        flightDTO.setArrivalAirport(flightEntity.getArrivalAirport());
        flightDTO.setDepartureTime(flightEntity.getDepartureTime());
        flightDTO.setArrivalTime(flightEntity.getArrivalTime());
        return flightDTO;
    }

    private void updateEntityFromDTO(FlightEntity flightEntity, FlightDTO flightDTO) {
        dtoToEntity(flightDTO, flightEntity);
    }
}
