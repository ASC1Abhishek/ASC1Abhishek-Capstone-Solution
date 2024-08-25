package com.ats.service;

import com.ats.model.BookingDTO;

import java.util.List;

public interface BookingService {
    BookingDTO createBooking(BookingDTO bookingDTO);
    BookingDTO updateBooking(String id, BookingDTO bookingDTO);
    void deleteBooking(String id);
    BookingDTO getBookingById(String id);
    List<BookingDTO> getAllBookings();
}
