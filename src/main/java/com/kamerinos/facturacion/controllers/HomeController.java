package com.kamerinos.facturacion.controllers;

import com.kamerinos.facturacion.models.Empleado;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import jakarta.servlet.http.HttpServletRequest;


@Controller
public class HomeController {

    @GetMapping("/")
    public String home(HttpServletRequest request, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Si el usuario está autenticado y es una instancia de Empleado
        if (authentication != null && authentication.getPrincipal() instanceof Empleado empleado) {
            model.addAttribute("empleado", empleado);
            model.addAttribute("rol", empleado.getRol().getNombreRol());
        }

        model.addAttribute("requestURI", request.getRequestURI());
        model.addAttribute("contenido", "home.html");
        model.addAttribute("titulo", "Kamerinos - Facturación");
        return "layout";
    }

    @GetMapping("/admin/dashboard")
    public String adminDashboard(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof Empleado empleado) {
            model.addAttribute("empleado", empleado);
            model.addAttribute("rol", empleado.getRol().getNombreRol());
        }

        model.addAttribute("contenido", "admin/dashboard.html");
        model.addAttribute("titulo", "Panel de Administración");
        return "layout";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login"; // Aquí debe existir un login.html
    }
}

