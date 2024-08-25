package com.ats.controller;

import com.ats.model.PlaneDTO;
import com.ats.service.PlaneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ats/planes")
public class PlaneController {

    private final PlaneService planeService;

    @Autowired
    public PlaneController(PlaneService planeService) {
        this.planeService = planeService;
    }

    @GetMapping
    public List<PlaneDTO> getAllPlanes() {
        return planeService.getAllPlanes();
    }

    @PostMapping
    public PlaneDTO addPlane(@RequestBody PlaneDTO planeDTO) {
        if (!isValidSeatingCapacity(planeDTO.getSeatingCapacity())) {
            throw new IllegalArgumentException("Invalid seating capacity");
        }
        return planeService.addPlane(planeDTO);
    }

    private boolean isValidSeatingCapacity(int seatingCapacity) {
        return seatingCapacity == 50 || seatingCapacity == 100 || seatingCapacity == 150 || seatingCapacity == 500;
    }
}