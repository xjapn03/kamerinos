package com.kamerinos.facturacion.aspects;

import com.kamerinos.facturacion.models.Empleado;
import com.kamerinos.facturacion.services.notificaciones.NotificacionService;
import com.kamerinos.facturacion.models.Servicio;
import com.kamerinos.facturacion.models.Cliente;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class NotificacionAspect {

    private final NotificacionService notificacionService;

    public NotificacionAspect(NotificacionService notificacionService) {
        this.notificacionService = notificacionService;
    }

    // Interceptar guardados de Servicio
    @AfterReturning(pointcut = "execution(* com.kamerinos.facturacion.services.servicios.ServicioService.guardarServicio(..))", returning = "resultado")
    public void notificarGuardadoServicio(JoinPoint joinPoint, Object resultado) {
        Empleado empleado = obtenerEmpleadoActual();
        if (empleado != null && resultado instanceof Servicio) {
            Servicio servicio = (Servicio) resultado;
            String mensaje = "El servicio '" + servicio.getNombre() + "' fue guardado por " + empleado.getNombre();
            notificacionService.registrarAccion(mensaje, empleado);
        }
    }

    // Interceptar eliminaciones de Servicio
    @After("execution(* com.kamerinos.facturacion.services.servicios.ServicioService.eliminarServicio(..)) && args(id)")
    public void notificarEliminadoServicio(JoinPoint joinPoint, Long id) {
        Empleado empleado = obtenerEmpleadoActual();
        if (empleado != null) {
            String mensaje = "Se eliminó un servicio con ID: " + id + " por " + empleado.getNombre();
            notificacionService.registrarAccion(mensaje, empleado);
        }
    }

    // Interceptar guardados de Cliente (otro ejemplo)
    @AfterReturning(pointcut = "execution(* com.kamerinos.facturacion.services.clientes.ClienteService.guardarCliente(..))", returning = "resultado")
    public void notificarGuardadoCliente(JoinPoint joinPoint, Object resultado) {
        Empleado empleado = obtenerEmpleadoActual();
        if (empleado != null && resultado instanceof Cliente) {
            Cliente cliente = (Cliente) resultado;
            String mensaje = "Se guardó el cliente '" + cliente.getNombre() + "' por " + empleado.getNombre();
            notificacionService.registrarAccion(mensaje, empleado);
        }
    }

    private Empleado obtenerEmpleadoActual() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof Empleado) {
            return (Empleado) authentication.getPrincipal();
        }
        return null;
    }
}
