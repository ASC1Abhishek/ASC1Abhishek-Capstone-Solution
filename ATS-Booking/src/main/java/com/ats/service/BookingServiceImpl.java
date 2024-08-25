package com.ats.service;

import com.ats.entity.BookingEntity;
import com.ats.model.BookingDTO;
import com.ats.repository.BookingRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookingServiceImpl implements BookingService {


    private final BookingRepository bookingRepository;

    @Autowired
    public BookingServiceImpl(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @Override
    public BookingDTO createBooking(BookingDTO bookingDTO) {
        BookingEntity bookingEntity = convertDTOToEntity(bookingDTO);
        bookingEntity.setId(generateBookingId());

        BookingEntity savedEntity = bookingRepository.save(bookingEntity);
        return convertEntityToDTO(savedEntity);
    }

    @Override
    public BookingDTO updateBooking(String id, BookingDTO bookingDTO) {
        Optional<BookingEntity> optionalBooking = bookingRepository.findById(id);
        if (optionalBooking.isPresent()) {
            BookingEntity bookingEntity = optionalBooking.get();
            updateEntityFromDTO(bookingEntity, bookingDTO);
            BookingEntity updatedEntity = bookingRepository.save(bookingEntity);
            return convertEntityToDTO(updatedEntity);
        } else {
            throw new IllegalArgumentException("Booking ID not found");
        }
    }

    @Override
    public void deleteBooking(String id) {
        bookingRepository.deleteById(id);
    }

    @Override
    public BookingDTO getBookingById(String id) {
        Optional<BookingEntity> optionalBooking = bookingRepository.findById(id);
        if (optionalBooking.isPresent()) {
            return convertEntityToDTO(optionalBooking.get());
        } else {
            throw new IllegalArgumentException("Booking ID not found");
        }
    }

    @Override
    public List<BookingDTO> getAllBookings() {
        List<BookingEntity> bookingEntities = bookingRepository.findAll();
        return bookingEntities.stream()
                .map(this::convertEntityToDTO)
                .collect(Collectors.toList());
    }

    private String generateBookingId() {
        long count = bookingRepository.count();
        return String.format("B%04d", count + 1);
    }

    private BookingEntity convertDTOToEntity(BookingDTO bookingDTO) {
        BookingEntity bookingEntity = new BookingEntity();
        dtoToEntity(bookingDTO, bookingEntity);
        return bookingEntity;
    }

    private static void dtoToEntity(BookingDTO bookingDTO, BookingEntity bookingEntity) {
        bookingEntity.setPassengerName(bookingDTO.getPassengerName());
        bookingEntity.setBookingDate(bookingDTO.getBookingDate());
        bookingEntity.setSeatNumber(bookingDTO.getSeatNumber());
        bookingEntity.setTicketCost(bookingDTO.getTicketCost());
        bookingEntity.setTotalAmount(bookingDTO.getTotalAmount());
        bookingEntity.setDepartureDate(bookingDTO.getDepartureDate());
        bookingEntity.setDepartureTime(bookingDTO.getDepartureTime());
        bookingEntity.setArrivalDate(bookingDTO.getArrivalDate());
        bookingEntity.setArrivalTime(bookingDTO.getArrivalTime());
    }

    private BookingDTO convertEntityToDTO(BookingEntity bookingEntity) {
        BookingDTO bookingDTO = new BookingDTO();
        bookingDTO.setId(bookingEntity.getId());
        bookingDTO.setPassengerName(bookingEntity.getPassengerName());
        bookingDTO.setBookingDate(bookingEntity.getBookingDate());
        bookingDTO.setSeatNumber(bookingEntity.getSeatNumber());
        bookingDTO.setTicketCost(bookingEntity.getTicketCost());
        bookingDTO.setTotalAmount(bookingEntity.getTotalAmount());
        bookingDTO.setDepartureDate(bookingEntity.getDepartureDate());
        bookingDTO.setDepartureTime(bookingEntity.getDepartureTime());
        bookingDTO.setArrivalDate(bookingEntity.getArrivalDate());
        bookingDTO.setArrivalTime(bookingEntity.getArrivalTime());
        return bookingDTO;
    }

    private void updateEntityFromDTO(BookingEntity bookingEntity, BookingDTO bookingDTO) {
        dtoToEntity(bookingDTO, bookingEntity);
    }
}
