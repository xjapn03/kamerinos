package com.kamerinos.facturacion.controllers;

import com.kamerinos.facturacion.models.Servicio;
import com.kamerinos.facturacion.services.servicios.CategoriaService;
import com.kamerinos.facturacion.services.servicios.ServicioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller

@RequestMapping("/servicios")
public class ServicioController {

    private final ServicioService servicioService;
    private final CategoriaService categoriaService;

    public ServicioController(ServicioService servicioService, CategoriaService categoriaService) {
        this.servicioService = servicioService;
        this.categoriaService = categoriaService;
    }

    @GetMapping
    public String listarServicios(Model model) {
        List<Servicio> servicios = servicioService.listarServicios();
        model.addAttribute("servicios", servicios);
        model.addAttribute("titulo", "Servicios");
        model.addAttribute("contenido", "servicios/lista");
        return "layout";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioNuevoServicio(Model model) {
        model.addAttribute("servicio", new Servicio());
        model.addAttribute("categorias", categoriaService.listarCategorias());
        return "servicios/formulario";
    }

    @PostMapping("/guardar")
    public String guardarServicio(@ModelAttribute Servicio servicio) {
        servicioService.guardarServicio(servicio);
        return "redirect:/servicios";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditarServicio(@PathVariable Long id, Model model) {
        Servicio servicio = servicioService.obtenerServicioPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("ID de servicio inválido: " + id));
        model.addAttribute("servicio", servicio);
        model.addAttribute("categorias", categoriaService.listarCategorias());
        return "servicios/formulario";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarServicio(@PathVariable Long id) {
        servicioService.eliminarServicio(id);
        return "redirect:/servicios";
    }
}