package com.kamerinos.facturacion.controllers;

import com.kamerinos.facturacion.models.Categoria;
import com.kamerinos.facturacion.services.servicios.CategoriaService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/categorias")
public class CategoriaController {

    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping
    public String listarCategorias(Model model) {
        List<Categoria> categorias = categoriaService.listarCategorias();
        model.addAttribute("categorias", categorias);
        model.addAttribute("titulo", "Categorías");
        model.addAttribute("contenido", "categorias/lista"); // <-- Fragmento de contenido
        return "layout"; // <-- Se usa layout general
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioNuevaCategoria(Model model) {
        model.addAttribute("categoria", new Categoria());
        model.addAttribute("categorias", categoriaService.listarCategorias()); // Para el select de categoría padre
        return "categorias/formulario";
    }

    @PostMapping("/guardar")
    public String guardarCategoria(@ModelAttribute Categoria categoria) {
        categoriaService.guardarCategoria(categoria);
        return "redirect:/categorias";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditarCategoria(@PathVariable Long id, Model model) {
        Categoria categoria = categoriaService.obtenerCategoriaPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("ID de categoría inválido: " + id));
        model.addAttribute("categoria", categoria);
        model.addAttribute("categorias", categoriaService.listarCategorias()); // Para el select de categoría padre
        return "categorias/formulario";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarCategoria(@PathVariable Long id) {
        categoriaService.eliminarCategoria(id);
        return "redirect:/categorias";
    }
}
