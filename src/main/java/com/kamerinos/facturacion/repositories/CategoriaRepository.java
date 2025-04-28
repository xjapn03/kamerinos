package com.kamerinos.facturacion.repositories;

import com.kamerinos.facturacion.models.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    // Aquí puedes agregar métodos personalizados si los necesitas más adelante
}
