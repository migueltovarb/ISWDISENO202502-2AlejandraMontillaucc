package com.proyecto.reservas.dtos.rooms;

import java.time.LocalDateTime;

public class RoomResponse {
    private String id;
    private String nombre;
    private Integer capacidad;
    private String ubicacion;
    private String descripcion;
    private LocalDateTime fechaCreacion;
    private String creadoPor;

    public RoomResponse() {}

    public RoomResponse(String id, String nombre, Integer capacidad, String ubicacion, String descripcion, LocalDateTime fechaCreacion, String creadoPor) {
        this.id = id;
        this.nombre = nombre;
        this.capacidad = capacidad;
        this.ubicacion = ubicacion;
        this.descripcion = descripcion;
        this.fechaCreacion = fechaCreacion;
        this.creadoPor = creadoPor;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getCreadoPor() {
        return creadoPor;
    }

    public void setCreadoPor(String creadoPor) {
        this.creadoPor = creadoPor;
    }
}
