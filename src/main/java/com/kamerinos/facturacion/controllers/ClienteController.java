package com.kamerinos.facturacion.controllers;

import com.kamerinos.facturacion.models.Cliente;
import com.kamerinos.facturacion.services.clientes.ClienteService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    // Mostrar lista de clientes
    @GetMapping
    public String listarClientes(Model model) {
        // Obtén la lista de clientes del servicio
        model.addAttribute("clientes", clienteService.listarClientes());
        // Título que se mostrará en el layout
        model.addAttribute("titulo", "Lista de Clientes");
        // El contenido se insertará en el layout
        model.addAttribute("contenido", "clientes/lista");
        return "layout"; // El layout principal se utiliza aquí
    }

    // Mostrar formulario de nuevo cliente
    @GetMapping("/nuevo")
    public String mostrarFormularioNuevoCliente(Model model) {
        // Crear un nuevo objeto Cliente vacío para el formulario
        model.addAttribute("cliente", new Cliente());
        model.addAttribute("titulo", "Nuevo Cliente");
        model.addAttribute("contenido", "clientes/formulario");
        return "layout"; // Se carga el layout
    }

    // Guardar nuevo cliente
    @PostMapping("/guardar")
    public String guardarCliente(@ModelAttribute Cliente cliente) {
        // Guardar el cliente usando el servicio
        clienteService.guardarCliente(cliente);
        return "redirect:/clientes"; // Redirige a la lista de clientes
    }

    // Mostrar formulario para editar cliente
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditarCliente(@PathVariable Long id, Model model) {
        // Obtener el cliente por ID
        Cliente cliente = clienteService.obtenerClientePorId(id)
                            .orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado"));
        model.addAttribute("cliente", cliente);
        model.addAttribute("titulo", "Editar Cliente");
        model.addAttribute("contenido", "clientes/formulario");
        return "layout"; // Se carga el layout con el formulario
    }

    // Eliminar cliente
    @GetMapping("/eliminar/{id}")
    public String eliminarCliente(@PathVariable Long id) {
        clienteService.eliminarCliente(id);
        return "redirect:/clientes"; // Redirige a la lista de clientes
    }
}
