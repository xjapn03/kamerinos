package com.kamerinos.facturacion.services.notificaciones;

import com.kamerinos.facturacion.models.Notificacion;
import com.kamerinos.facturacion.models.Empleado;
import com.kamerinos.facturacion.repositories.NotificacionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NotificacionServiceImpl implements NotificacionService {

    private final NotificacionRepository notificacionRepository;

    public NotificacionServiceImpl(NotificacionRepository notificacionRepository) {
        this.notificacionRepository = notificacionRepository;
    }

    @Override
    public void registrarAccion(String mensaje, Empleado empleado) {
        Notificacion notificacion = new Notificacion();
        notificacion.setMensaje(mensaje);
        notificacion.setFechaHora(LocalDateTime.now());
        notificacion.setEmpleado(empleado);

        notificacionRepository.save(notificacion);
    }

    @Override
    public List<Notificacion> obtenerNotificacionesPorEmpleado(Long empleadoId) {
        return notificacionRepository.findByEmpleadoId(empleadoId);
    }

    @Override
    public List<Notificacion> listarTodasLasNotificaciones() {
        return notificacionRepository.findAll();
    }
}
