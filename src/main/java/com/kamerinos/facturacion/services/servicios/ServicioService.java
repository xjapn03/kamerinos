package com.kamerinos.facturacion.services.servicios;

import com.kamerinos.facturacion.models.Servicio;
import java.util.List;
import java.util.Optional;

public interface ServicioService {
    List<Servicio> listarServicios();
    Optional<Servicio> obtenerServicioPorId(Long id);
    Servicio guardarServicio(Servicio servicio);
    void eliminarServicio(Long id);
}
