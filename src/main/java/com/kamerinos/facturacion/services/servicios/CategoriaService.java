package com.kamerinos.facturacion.services.servicios;

import com.kamerinos.facturacion.models.Categoria;

import java.util.List;
import java.util.Optional;

public interface CategoriaService {

    List<Categoria> listarCategorias();
    Optional<Categoria> obtenerCategoriaPorId(Long id);
    Categoria guardarCategoria(Categoria categoria);
    void eliminarCategoria(Long id);
}

