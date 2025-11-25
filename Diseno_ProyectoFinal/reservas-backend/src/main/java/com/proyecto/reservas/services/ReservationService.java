package com.proyecto.reservas.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.proyecto.reservas.dtos.reservations.ReservationCreateRequest;
import com.proyecto.reservas.dtos.reservations.ReservationResponse;
import com.proyecto.reservas.exceptions.BadRequestException;
import com.proyecto.reservas.exceptions.ConflictException;
import com.proyecto.reservas.exceptions.NotFoundException;
import com.proyecto.reservas.models.Reservation;
import com.proyecto.reservas.models.User;
import com.proyecto.reservas.repositories.ReservationRepository;
import com.proyecto.reservas.repositories.RoomRepository;
import com.proyecto.reservas.repositories.UserRepository;
import com.proyecto.reservas.utils.CodeGenerator;

@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final RoomRepository roomRepository;
    private final UserRepository userRepository;

    public ReservationService(ReservationRepository reservationRepository, RoomRepository roomRepository, UserRepository userRepository) {
        this.reservationRepository = reservationRepository;
        this.roomRepository = roomRepository;
        this.userRepository = userRepository;
    }

    public ReservationResponse createReservation(ReservationCreateRequest request) {
        String salaId = Objects.requireNonNull(request.getSalaId(), "salaId no puede ser nulo");
        if (!roomRepository.existsById(salaId)) {
            throw new NotFoundException("Sala no existente");
        }
        LocalDate date = LocalDate.parse(request.getFecha());
        LocalTime start = LocalTime.parse(request.getHoraInicio());
        LocalTime end = LocalTime.parse(request.getHoraFin());
        if (!start.isBefore(end)) {
            throw new BadRequestException("horaInicio debe ser menor a horaFin");
        }
        List<Reservation> existing = reservationRepository.findByRoomIdAndDate(salaId, date);
        for (Reservation r : existing) {
            boolean overlap = r.getStartTime().isBefore(end) && start.isBefore(r.getEndTime());
            if (overlap) {
                throw new ConflictException("La sala no está disponible en ese horario");
            }
        }
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        User user = userRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("Usuario no encontrado"));
        Reservation newRes = new Reservation();
        newRes.setUserId(user.getId());
        newRes.setRoomId(salaId);
        newRes.setDate(date);
        newRes.setStartTime(start);
        newRes.setEndTime(end);
        newRes.setCreatedAt(Instant.now());
        newRes.setCode(CodeGenerator.generate("RSV"));
        reservationRepository.save(newRes);
        return new ReservationResponse(newRes.getId(), newRes.getCode(), newRes.getUserId(), newRes.getRoomId(), newRes.getDate(), newRes.getStartTime(), newRes.getEndTime(), newRes.getCreatedAt());
    }
}
