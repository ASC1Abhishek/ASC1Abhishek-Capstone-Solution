package com.ats.feign_client;

import com.ats.model.PassengerDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "ATS-Passenger")
public interface PassengerClient {
    @GetMapping("/ats/passengers/all")
    List<PassengerDTO> getAllPassengers();
}
