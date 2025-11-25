package com.proyecto.reservas.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.reservas.dtos.rooms.RoomCreateRequest;
import com.proyecto.reservas.dtos.rooms.RoomResponse;
import com.proyecto.reservas.services.RoomService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/reservations/rooms")
@Validated
public class RoomController {
    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<RoomResponse> create(@Valid @RequestBody RoomCreateRequest request) {
        return ResponseEntity.ok(roomService.create(request));
    }

    @GetMapping("/all")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<RoomResponse>> all() {
        return ResponseEntity.ok(roomService.findAll());
    }
}
