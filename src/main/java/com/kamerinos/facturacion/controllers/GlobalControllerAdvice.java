package com.kamerinos.facturacion.controllers;

import com.kamerinos.facturacion.models.Empleado;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.ui.Model;

@ControllerAdvice
public class GlobalControllerAdvice {

    @ModelAttribute
    public void agregarEmpleadoAlModelo(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        
        if (authentication != null && authentication.getPrincipal() instanceof Empleado empleado) {
            model.addAttribute("empleado", empleado);
            model.addAttribute("rol", empleado.getRol().getNombreRol());
        }
    }
}
