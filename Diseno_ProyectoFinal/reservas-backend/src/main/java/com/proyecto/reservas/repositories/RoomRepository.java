package com.proyecto.reservas.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.proyecto.reservas.models.Room;

public interface RoomRepository extends MongoRepository<Room, String> {
    Room findByNombre(String nombre);
}
