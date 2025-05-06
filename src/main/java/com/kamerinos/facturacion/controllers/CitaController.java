package com.kamerinos.facturacion.controllers;

import com.kamerinos.facturacion.models.Cita;
import com.kamerinos.facturacion.models.Empleado;
import com.kamerinos.facturacion.services.Citas.CitaService;
import com.kamerinos.facturacion.services.clientes.ClienteService;
import com.kamerinos.facturacion.services.servicios.ServicioService;
import com.kamerinos.facturacion.services.empleados.EmpleadoService;
import com.kamerinos.facturacion.dto.EventoDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;


@Controller
@RequestMapping("/citas")
public class CitaController {

    private final CitaService citaService;
    private final EmpleadoService empleadoService;
    private final ServicioService servicioService;
    private final ClienteService clienteService;

    public CitaController(CitaService citaService, EmpleadoService empleadoService, ServicioService servicioService, ClienteService clienteService) {
        this.citaService = citaService;
        this.clienteService = clienteService;
        this.empleadoService = empleadoService;
        this.servicioService = servicioService;
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
    public String mostrarFormularioNuevaCita(@RequestParam(required = false) String fecha, Model model) {
        Cita cita = new Cita();

        if (fecha != null && !fecha.isEmpty()) {
            try {
                // Establece la hora por defecto al mediodía para facilitar cambios
                cita.setInicio(java.time.LocalDateTime.parse(fecha + "T12:00:00"));
            } catch (Exception e) {
                System.out.println("Fecha inválida en parámetro: " + fecha);
            }
        }

        model.addAttribute("cita", cita);
        model.addAttribute("empleados", empleadoService.listarEmpleados());
        model.addAttribute("servicios", servicioService.listarServicios());
        model.addAttribute("clientes", clienteService.listarClientes());
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
        model.addAttribute("servicios", servicioService.listarServicios());
        model.addAttribute("clientes", clienteService.listarClientes()); // Aseguramos que la lista de clientes esté en el modelo
        model.addAttribute("titulo", "Editar Cita");
        model.addAttribute("contenido", "citas/formulario");
        return "layout";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarCita(@PathVariable Long id) {
        citaService.eliminarCita(id);
        return "redirect:/citas";
    }

    // ✅ Endpoint que envía citas al calendario
    @GetMapping("/api/citas")
    @ResponseBody
    public List<EventoDTO> obtenerCitas() {
        List<Cita> citas = citaService.listarCitas();
        List<EventoDTO> eventos = new ArrayList<>();

        for (Cita cita : citas) {
            EventoDTO evento = new EventoDTO();
            evento.setId(cita.getId());

            // Verificamos que cliente y servicio no sean null para evitar errores
            String clienteNombre = (cita.getCliente() != null) 
                ? cita.getCliente().getNombre() + " " + cita.getCliente().getApellido()
                : "Cliente desconocido";

            String nombreServicio = (cita.getServicio() != null)
                ? cita.getServicio().getNombre()
                : "Servicio no asignado";

            evento.setTitle(clienteNombre + " - " + nombreServicio);
            evento.setStart(cita.getInicio().toString());
            evento.setEnd(cita.getFin().toString());
            evento.setDescription(cita.getNota());
            evento.setStatus(cita.getEstado());
            eventos.add(evento);
        }

        return eventos;
    }


    // ✅ Endpoint que recibe actualizaciones desde drag & drop
    @PutMapping("/api/citas/{id}")
    @ResponseBody
    public Cita actualizarCita(@PathVariable Long id, @RequestBody Map<String, String> datos) {
        return citaService.actualizarHorarioCita(id, datos.get("inicio"), datos.get("fin"));
    }
}
