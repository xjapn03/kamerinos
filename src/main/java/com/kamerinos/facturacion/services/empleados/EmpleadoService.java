package com.kamerinos.facturacion.services.empleados;

import com.kamerinos.facturacion.models.Empleado;
import java.util.List;
import java.util.Optional;

public interface EmpleadoService {
    
    List<Empleado> listarEmpleados();
    Optional<Empleado> obtenerEmpleadoPorId(Long id);
    Optional<Empleado> buscarEmpleadoPorCorreo(String correo);
    Empleado guardarEmpleado(Empleado empleado);
    void eliminarEmpleado(Long id);

}
