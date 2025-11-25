package com.proyecto.reservas.dtos.reservations;

import jakarta.validation.constraints.NotBlank;

public class ReservationCreateRequest {
    @NotBlank
    private String salaId;
    @NotBlank
    private String fecha;
    @NotBlank
    private String horaInicio;
    @NotBlank
    private String horaFin;

    public ReservationCreateRequest() {}

    public String getSalaId() {
        return salaId;
    }

    public void setSalaId(String salaId) {
        this.salaId = salaId;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(String horaFin) {
        this.horaFin = horaFin;
    }
}
