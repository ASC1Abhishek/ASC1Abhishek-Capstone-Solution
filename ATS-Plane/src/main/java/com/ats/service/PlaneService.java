package com.ats.service;

import com.ats.model.PlaneDTO;
import java.util.List;

public interface PlaneService {
    List<PlaneDTO> getAllPlanes();

    PlaneDTO addPlane(PlaneDTO planeDTO);
}
