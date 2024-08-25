package com.ats.feign_client;

import com.ats.model.BookingDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "booking-service")
public interface BookingFeignClient {


    @PostMapping("/api/bookings")
    BookingDTO createBooking(@RequestBody BookingDTO bookingDTO);

    @GetMapping("/api/bookings")
    List<BookingDTO> getAllBookings();


}

