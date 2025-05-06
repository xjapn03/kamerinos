package com.kamerinos.facturacion.services.Citas;

import com.kamerinos.facturacion.models.Cita;

import java.util.List;
import java.util.Optional;

public interface CitaService {
    List<Cita> listarCitas();
    Optional<Cita> obtenerCitaPorId(Long id);
    Cita guardarCita(Cita cita);
    void eliminarCita(Long id);

    // Nuevo método para soporte de drag & drop
    Cita actualizarHorarioCita(Long id, String inicioStr, String finStr);
}
