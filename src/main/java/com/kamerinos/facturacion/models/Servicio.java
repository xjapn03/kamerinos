package com.kamerinos.facturacion.models;

import jakarta.persistence.*;

@Entity
@Table(name = "servicios")
public class Servicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre; // Nombre del servicio
    private String descripcion; // Descripción del servicio
    private Double precio; // Precio del servicio
    private Integer duracion; // Duración del servicio en minutos
    private Double porcentajeEmpleado; // Porcentaje del servicio para el empleado

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria; // Relación con la categoría a la que pertenece el servicio

    // ====== Métodos automáticos de fecha ======

    @PrePersist
    protected void onCreate() {
        // Aquí podrías poner lógica para establecer fechas automáticas si fuera necesario
    }

    @PreUpdate
    protected void onUpdate() {
        // Aquí podrías actualizar la fecha de modificación si fuera necesario
    }

    // ====== Getters y Setters ======

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Integer getDuracion() {
        return duracion;
    }

    public void setDuracion(Integer duracion) {
        this.duracion = duracion;
    }

    public Double getPorcentajeEmpleado() {
        return porcentajeEmpleado;
    }

    public void setPorcentajeEmpleado(Double porcentajeEmpleado) {
        this.porcentajeEmpleado = porcentajeEmpleado;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}
