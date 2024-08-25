package com.ats.service;

import com.ats.entity.PassengerEntity;
import com.ats.model.PassengerDTO;
import com.ats.repository.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PassengerServiceImpl implements PassengerService {

    private final PassengerRepository passengerRepository;

    @Autowired
    public PassengerServiceImpl(PassengerRepository passengerRepository) {
        this.passengerRepository = passengerRepository;
    }

    @Override
    public PassengerDTO createPassenger(PassengerDTO passengerDTO) {
        PassengerEntity passengerEntity = convertDTOToEntity(passengerDTO);
        passengerEntity.setId(generatePassengerId());

        PassengerEntity savedEntity = passengerRepository.save(passengerEntity);
        return convertEntityToDTO(savedEntity);
    }

    @Override
    public PassengerDTO updatePassenger(String id, PassengerDTO passengerDTO) {
        Optional<PassengerEntity> optionalPassenger = passengerRepository.findById(id);
        if (optionalPassenger.isPresent()) {
            PassengerEntity passengerEntity = optionalPassenger.get();
            updateEntityFromDTO(passengerEntity, passengerDTO);
            PassengerEntity updatedEntity = passengerRepository.save(passengerEntity);
            return convertEntityToDTO(updatedEntity);
        } else {
            throw new IllegalArgumentException("Passenger ID not found");
        }
    }

    @Override
    public void deletePassenger(String id) {
        passengerRepository.deleteById(id);
    }

    @Override
    public PassengerDTO getPassengerById(String id) {
        Optional<PassengerEntity> optionalPassenger = passengerRepository.findById(id);
        if (optionalPassenger.isPresent()) {
            return convertEntityToDTO(optionalPassenger.get());
        } else {
            throw new IllegalArgumentException("Passenger ID not found");
        }
    }

    @Override
    public List<PassengerDTO> getAllPassengers() {
        List<PassengerEntity> passengerEntities = passengerRepository.findAll();
        return passengerEntities.stream()
                .map(this::convertEntityToDTO)
                .collect(Collectors.toList());
    }

    private String generatePassengerId() {
        long count = passengerRepository.count();
        return String.format("P%04d", count + 1);
    }

    private PassengerEntity convertDTOToEntity(PassengerDTO passengerDTO) {
        PassengerEntity passengerEntity = new PassengerEntity();
        dtoToEntity(passengerDTO, passengerEntity);
        return passengerEntity;
    }

    private static void dtoToEntity(PassengerDTO passengerDTO, PassengerEntity passengerEntity) {
        passengerEntity.setFirstName(passengerDTO.getFirstName());
        passengerEntity.setLastName(passengerDTO.getLastName());
        passengerEntity.setEmail(passengerDTO.getEmail());
        passengerEntity.setPhoneNumber(passengerDTO.getPhoneNumber());
    }

    private PassengerDTO convertEntityToDTO(PassengerEntity passengerEntity) {
        PassengerDTO passengerDTO = new PassengerDTO();
        passengerDTO.setId(passengerEntity.getId());
        passengerDTO.setFirstName(passengerEntity.getFirstName());
        passengerDTO.setLastName(passengerEntity.getLastName());
        passengerDTO.setEmail(passengerEntity.getEmail());
        passengerDTO.setPhoneNumber(passengerEntity.getPhoneNumber());
        return passengerDTO;
    }

    private void updateEntityFromDTO(PassengerEntity passengerEntity, PassengerDTO passengerDTO) {
        dtoToEntity(passengerDTO, passengerEntity);
    }
}
