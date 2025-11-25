package com.proyecto.reservas.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.proyecto.reservas.dtos.rooms.RoomCreateRequest;
import com.proyecto.reservas.dtos.rooms.RoomResponse;
import com.proyecto.reservas.exceptions.BadRequestException;
import com.proyecto.reservas.exceptions.ConflictException;
import com.proyecto.reservas.models.Room;
import com.proyecto.reservas.models.User;
import com.proyecto.reservas.repositories.RoomRepository;
import com.proyecto.reservas.repositories.UserRepository;

@Service
public class RoomService {
    private final RoomRepository roomRepository;
    private final UserRepository userRepository;

    public RoomService(RoomRepository roomRepository, UserRepository userRepository) {
        this.roomRepository = roomRepository;
        this.userRepository = userRepository;
    }

    public RoomResponse create(RoomCreateRequest request) {
        if (request.getNombre() == null || request.getNombre().isBlank()) {
            throw new BadRequestException("El nombre es obligatorio");
        }
        if (request.getCapacidad() == null || request.getCapacidad() <= 0) {
            throw new BadRequestException("La capacidad debe ser mayor a cero");
        }
        Room existing = roomRepository.findByNombre(request.getNombre());
        if (existing != null) {
            throw new ConflictException("Ya existe una sala con ese nombre");
        }
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        User admin = userRepository.findByEmail(email).orElseThrow(() -> new BadRequestException("Usuario no válido"));
        Room room = new Room();
        room.setNombre(request.getNombre());
        room.setName(request.getNombre());
        room.setCapacidad(request.getCapacidad());
        room.setUbicacion(request.getUbicacion());
        room.setDescripcion(request.getDescripcion());
        room.setFechaCreacion(LocalDateTime.now());
        room.setCreadoPor(admin.getId());
        roomRepository.save(room);
        return new RoomResponse(room.getId(), room.getNombre(), room.getCapacidad(), room.getUbicacion(), room.getDescripcion(), room.getFechaCreacion(), room.getCreadoPor());
    }

    public List<RoomResponse> findAll() {
        return roomRepository.findAll().stream()
                .map(r -> new RoomResponse(r.getId(), r.getNombre(), r.getCapacidad(), r.getUbicacion(), r.getDescripcion(), r.getFechaCreacion(), r.getCreadoPor()))
                .toList();
    }
}
