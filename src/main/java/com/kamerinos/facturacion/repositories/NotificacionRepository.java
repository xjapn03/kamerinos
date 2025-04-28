package com.kamerinos.facturacion.repositories;

import com.kamerinos.facturacion.models.Notificacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface NotificacionRepository extends JpaRepository<Notificacion, Long> {
    List<Notificacion> findByEmpleadoId(Long empleadoId);
}
