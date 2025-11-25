package com.proyecto.reservas.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.proyecto.reservas.models.Reservation;

public interface ReservationRepository extends MongoRepository<Reservation, String> {
    List<Reservation> findByRoomIdAndDate(String roomId, LocalDate date);
}
