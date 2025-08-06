package com.example.app1.domain.entities;

import java.time.LocalDateTime;

/**
 * Entidad que representa una reserva dentro de la comunidad.
 */
public class Reserva {
    private int id;
    private LocalDateTime fecha;
    private String horario;
    private int estado;
    private int id_usuario;

    public Reserva(int id, LocalDateTime fecha, String horario, int estado, int id_usuario) {
        this.id = id;
        this.fecha = fecha;
        this.horario = horario;
        this.estado = estado;
        this.id_usuario = id_usuario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    @Override
    public String toString() {
        return "Reserva{" +
                "id=" + id +
                ", fecha=" + fecha +
                ", horario='" + horario + '\'' +
                ", estado=" + estado +
                ", id_usuario=" + id_usuario +
                '}';
    }
}
