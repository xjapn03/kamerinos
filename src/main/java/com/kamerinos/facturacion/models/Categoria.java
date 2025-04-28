package com.kamerinos.facturacion.models;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "categorias")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre; // Nombre de la categoría

    @ManyToOne
    @JoinColumn(name = "categoria_padre_id")
    private Categoria categoriaPadre; // Relación recursiva para subcategorías

    @OneToMany(mappedBy = "categoriaPadre")
    private List<Categoria> subcategorias; // Subcategorías de esta categoría

    @OneToMany(mappedBy = "categoria")
    private List<Servicio> servicios; // Servicios asociados a esta categoría

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

    public Categoria getCategoriaPadre() {
        return categoriaPadre;
    }

    public void setCategoriaPadre(Categoria categoriaPadre) {
        this.categoriaPadre = categoriaPadre;
    }

    public List<Categoria> getSubcategorias() {
        return subcategorias;
    }

    public void setSubcategorias(List<Categoria> subcategorias) {
        this.subcategorias = subcategorias;
    }

    public List<Servicio> getServicios() {
        return servicios;
    }

    public void setServicios(List<Servicio> servicios) {
        this.servicios = servicios;
    }
}
