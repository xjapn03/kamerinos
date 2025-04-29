package com.kamerinos.facturacion.dto;
import com.kamerinos.facturacion.models.Notificacion;

// NotificacionDTO.java
public record NotificacionDTO(Long id, String mensaje, String fechaHora) {
    public NotificacionDTO(Notificacion notificacion) {
        this(
            notificacion.getId(),
            notificacion.getMensaje(),
            notificacion.getFechaHora().toString() // <-- formato ISO
        );
    }
}

