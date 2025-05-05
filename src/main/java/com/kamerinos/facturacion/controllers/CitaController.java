package com.kamerinos.facturacion.controllers;

import com.kamerinos.facturacion.models.Cita;
import com.kamerinos.facturacion.models.Empleado;
import com.kamerinos.facturacion.services.Citas.CitaService;
import com.kamerinos.facturacion.services.empleados.EmpleadoService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/citas")
public class CitaController {

    private final CitaService citaService;
    private final EmpleadoService empleadoService;

    public CitaController(CitaService citaService, EmpleadoService empleadoService) {
        this.citaService = citaService;
        this.empleadoService = empleadoService;
    }

    @GetMapping
    public String listarCitas(Model model) {
        List<Cita> citas = citaService.listarCitas();
        model.addAttribute("citas", citas);
        model.addAttribute("titulo", "Citas");
        model.addAttribute("contenido", "citas/lista");
        return "layout";
    }

    @GetMapping("/nueva")
    public String mostrarFormularioNuevaCita(Model model) {
        model.addAttribute("cita", new Cita());
        model.addAttribute("empleados", empleadoService.listarEmpleados());
        model.addAttribute("titulo", "Nueva Cita");
        model.addAttribute("contenido", "citas/formulario");
        return "layout";
    }

    @PostMapping("/guardar")
    public String guardarCita(@ModelAttribute Cita cita) {
        citaService.guardarCita(cita);
        return "redirect:/citas";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditarCita(@PathVariable Long id, Model model) {
        Cita cita = citaService.obtenerCitaPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("ID de cita inválido: " + id));
        model.addAttribute("cita", cita);
        model.addAttribute("empleados", empleadoService.listarEmpleados());
        model.addAttribute("titulo", "Editar Cita");
        model.addAttribute("contenido", "citas/formulario");
        return "layout";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarCita(@PathVariable Long id) {
        citaService.eliminarCita(id);
        return "redirect:/citas";
    }
}
