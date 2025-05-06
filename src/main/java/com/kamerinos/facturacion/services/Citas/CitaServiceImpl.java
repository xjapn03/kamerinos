package com.kamerinos.facturacion.services.Citas;

import com.kamerinos.facturacion.models.Cita;
import com.kamerinos.facturacion.repositories.CitaRepository;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;


@Service
public class CitaServiceImpl implements CitaService {

    private final CitaRepository citaRepository;

    public CitaServiceImpl(CitaRepository citaRepository) {
        this.citaRepository = citaRepository;
    }

    @Override
    public List<Cita> listarCitas() {
        return citaRepository.findAll();
    }

    @Override
    public Optional<Cita> obtenerCitaPorId(Long id) {
        return citaRepository.findById(id);
    }

    @Override
    public Cita guardarCita(Cita cita) {
        return citaRepository.save(cita);
    }

    @Override
    public void eliminarCita(Long id) {
        citaRepository.deleteById(id);
    }

    @Override
    public Cita actualizarHorarioCita(Long id, String inicioStr, String finStr) {
        Cita cita = citaRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Cita no encontrada con ID: " + id));

        try {
            OffsetDateTime inicioOffset = OffsetDateTime.parse(inicioStr);
            OffsetDateTime finOffset = OffsetDateTime.parse(finStr);

            cita.setInicio(inicioOffset.toLocalDateTime());
            cita.setFin(finOffset.toLocalDateTime());
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Formato de fecha inválido: " + e.getMessage());
        }

        return citaRepository.save(cita);
    }
}

