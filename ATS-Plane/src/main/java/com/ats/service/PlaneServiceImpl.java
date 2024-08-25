package com.ats.service;

import com.ats.entity.PlaneEntity;
import com.ats.model.PlaneDTO;
import com.ats.repository.PlaneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlaneServiceImpl implements PlaneService{

    private final PlaneRepository planeRepository;

    @Autowired
    private PlaneServiceImpl(PlaneRepository planeRepository){
        this.planeRepository = planeRepository;
    }

    @Override
    public List<PlaneDTO> getAllPlanes() {
        List<PlaneEntity> planes = planeRepository.findAll();
        List<PlaneDTO> list = new ArrayList<>();
        for (PlaneEntity plane : planes) {
            PlaneDTO planeDTO = convertToDTO(plane);
            list.add(planeDTO);
        }
        return list;
    }

    @Override
    public PlaneDTO addPlane(PlaneDTO planeDTO) {
        PlaneEntity planeEntity = convertToEntity(planeDTO);
        planeEntity.setRegistrationNumber(generateRegistrationNumber());
        PlaneEntity savedPlane = planeRepository.save(planeEntity);
        return convertToDTO(savedPlane);
    }

    private String generateRegistrationNumber() {
        PlaneEntity lastPlane = planeRepository.findTopByOrderByRegistrationNumberDesc();
        int nextNumber = (lastPlane == null) ? 1 : Integer.parseInt(lastPlane.getRegistrationNumber().substring(1)) + 1;
        return String.format("R%04d", nextNumber);
    }

    private PlaneDTO convertToDTO(PlaneEntity planeEntity) {
        PlaneDTO planeDTO = new PlaneDTO();
        planeDTO.setRegistrationNumber(planeEntity.getRegistrationNumber());
        planeDTO.setPlaneMaker(planeEntity.getPlaneMaker());
        planeDTO.setModel(planeEntity.getModel());
        planeDTO.setImagePath(planeEntity.getImagePath());
        planeDTO.setSeatingCapacity(planeEntity.getSeatingCapacity());
        return planeDTO;
    }

    private PlaneEntity convertToEntity(PlaneDTO planeDTO) {
        PlaneEntity planeEntity = new PlaneEntity();
        planeEntity.setRegistrationNumber(planeDTO.getRegistrationNumber());
        planeEntity.setPlaneMaker(planeDTO.getPlaneMaker());
        planeEntity.setModel(planeDTO.getModel());
        planeEntity.setImagePath(planeDTO.getImagePath());
        planeEntity.setSeatingCapacity(planeDTO.getSeatingCapacity());
        return planeEntity;
    }
}
