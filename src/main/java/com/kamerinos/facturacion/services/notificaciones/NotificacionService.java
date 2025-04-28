package com.kamerinos.facturacion.services.notificaciones;

import com.kamerinos.facturacion.models.Notificacion;
import com.kamerinos.facturacion.models.Empleado;

import java.util.List;

public interface NotificacionService {

    void registrarAccion(String mensaje, Empleado empleado);

    List<Notificacion> obtenerNotificacionesPorEmpleado(Long empleadoId);

    List<Notificacion> listarTodasLasNotificaciones(); // Opcional, si quieres ver todas
}
