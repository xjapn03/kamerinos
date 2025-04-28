package com.kamerinos.facturacion.controllers;

import com.kamerinos.facturacion.models.Notificacion;
import com.kamerinos.facturacion.services.notificaciones.NotificacionService;
import com.kamerinos.facturacion.models.Empleado;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/notificaciones")
public class NotificacionController {

    private final NotificacionService notificacionService;

    public NotificacionController(NotificacionService notificacionService) {
        this.notificacionService = notificacionService;
    }

    // Mostrar lista de notificaciones del empleado actual (admin)
    @GetMapping
    public String listarNotificaciones(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof Empleado empleado) {
            List<Notificacion> notificaciones = notificacionService.obtenerNotificacionesPorEmpleado(empleado.getId());
            model.addAttribute("notificaciones", notificaciones);
            model.addAttribute("empleado", empleado);
            model.addAttribute("rol", empleado.getRol().getNombreRol());
        }

        model.addAttribute("titulo", "Notificaciones");
        model.addAttribute("contenido", "notificaciones/lista"); // Vista notificaciones/lista.html
        return "layout"; // Siempre usando tu layout principal
    }
}
