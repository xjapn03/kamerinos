package com.kamerinos.facturacion.repositories;

import com.kamerinos.facturacion.models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    // Aquí podrías agregar métodos personalizados si necesitas, por ahora nada.

}
