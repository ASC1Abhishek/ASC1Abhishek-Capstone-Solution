package com.ats.service;

import com.ats.entity.AirportEntity;
import com.ats.model.AirportDTO;
import com.ats.repository.AirportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AirportServiceImpl implements AirportService {

    private final AirportRepository airportRepository;

    @Autowired
    public AirportServiceImpl(AirportRepository airportRepository) {
        this.airportRepository = airportRepository;
    }

    @Override
    public List<AirportDTO> getAllAirports() {
        List<AirportEntity> airports = airportRepository.findAll();
        return airports.stream().map(this::convertEntityToDTO).collect(Collectors.toList());
    }

    @Override
    public AirportDTO addAirport(AirportDTO airportDTO) {
        AirportEntity airportEntity = convertDTOToEntity(airportDTO);
        airportEntity.setAirportCode(generateAirportCode());
        AirportEntity savedAirport = airportRepository.save(airportEntity);
        return convertEntityToDTO(savedAirport);
    }


    private String generateAirportCode() {
        AirportEntity lastAirport = airportRepository.findTopByOrderByAirportCodeDesc();
        int nextNumber = (lastAirport == null) ? 1 : Integer.parseInt(lastAirport.getAirportCode().substring(1)) + 1;
        return String.format("A%04d", nextNumber);
    }


    private AirportDTO convertEntityToDTO(AirportEntity airportEntity) {
        AirportDTO airportDTO = new AirportDTO();
        airportDTO.setId(airportEntity.getId());
        airportDTO.setAirportName(airportEntity.getAirportName());
        airportDTO.setCountryCode(airportEntity.getCountryCode());
        airportDTO.setAirportCode(airportEntity.getAirportCode());
        return airportDTO;
    }

    private AirportEntity convertDTOToEntity(AirportDTO airportDTO) {
        AirportEntity airportEntity = new AirportEntity();
        airportEntity.setId(airportDTO.getId());
        airportEntity.setAirportName(airportDTO.getAirportName());
        airportEntity.setCountryCode(airportDTO.getCountryCode());
        airportEntity.setAirportCode(airportDTO.getAirportCode());
        return airportEntity;
    }


}
