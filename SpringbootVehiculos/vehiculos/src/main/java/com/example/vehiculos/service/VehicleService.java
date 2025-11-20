package com.example.vehiculos.service;

import com.example.vehiculos.model.Vehicle;
import com.example.vehiculos.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Objects;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository repository;

    public List<Vehicle> findAll() {
        return repository.findAll();
    }

    public Optional<Vehicle> findById(String id) {
        return repository.findById(Objects.requireNonNull(id));
    }

    public Vehicle save(Vehicle vehicle) {
        return repository.save(Objects.requireNonNull(vehicle));
    }

    public void deleteById(String id) {
        repository.deleteById(Objects.requireNonNull(id));
    }
}