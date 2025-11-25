package com.proyecto.reservas.dtos.auth;

import java.time.Instant;

public class RegisterResponse {
    private String id;
    private String nombre;
    private String correo;
    private Instant creadoEn;

    public RegisterResponse() {}

    public RegisterResponse(String id, String nombre, String correo, Instant creadoEn) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.creadoEn = creadoEn;
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

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Instant getCreadoEn() {
        return creadoEn;
    }

    public void setCreadoEn(Instant creadoEn) {
        this.creadoEn = creadoEn;
    }
}
