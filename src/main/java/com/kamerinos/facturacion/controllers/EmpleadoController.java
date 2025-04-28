package com.kamerinos.facturacion.controllers;

import com.kamerinos.facturacion.models.Empleado;
import com.kamerinos.facturacion.repositories.RolRepository;
import com.kamerinos.facturacion.services.empleados.EmpleadoService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/empleados")
public class EmpleadoController {

    private final EmpleadoService empleadoService;
    private final RolRepository rolRepository;

    public EmpleadoController(EmpleadoService empleadoService, RolRepository rolRepository) {
        this.empleadoService = empleadoService;
        this.rolRepository = rolRepository;
    }

    // Mostrar lista de empleados
    @GetMapping
    public String listarEmpleados(Model model) {
        List<Empleado> empleados = empleadoService.listarEmpleados();
        model.addAttribute("empleados", empleados);
        model.addAttribute("titulo", "Lista de Empleados");
        model.addAttribute("contenido", "empleados/lista");
        return "layout"; // Usamos layout general
    }

    // Mostrar formulario de nuevo empleado
    @GetMapping("/nuevo")
    public String nuevoEmpleado(Model model) {
        model.addAttribute("empleado", new Empleado());
        model.addAttribute("roles", rolRepository.findAll());
        model.addAttribute("titulo", "Nuevo Empleado");
        model.addAttribute("contenido", "empleados/formulario");
        return "layout";
    }

    // Guardar nuevo empleado
    @PostMapping("/guardar")
    public String guardarEmpleado(@ModelAttribute Empleado empleado) {
        empleadoService.guardarEmpleado(empleado);
        return "redirect:/empleados";
    }

    // Mostrar formulario de editar empleado
    @GetMapping("/editar/{id}")
    public String editarEmpleado(@PathVariable Long id, Model model) {
        Optional<Empleado> empleado = empleadoService.obtenerEmpleadoPorId(id);
        if (empleado.isPresent()) {
            model.addAttribute("empleado", empleado.get());
            model.addAttribute("roles", rolRepository.findAll());
            model.addAttribute("titulo", "Editar Empleado");
            model.addAttribute("contenido", "empleados/formulario");
            return "layout";
        } else {
            return "redirect:/empleados";
        }
    }

    // Eliminar empleado
    @GetMapping("/eliminar/{id}")
    public String eliminarEmpleado(@PathVariable Long id) {
        empleadoService.eliminarEmpleado(id);
        return "redirect:/empleados";
    }
}
