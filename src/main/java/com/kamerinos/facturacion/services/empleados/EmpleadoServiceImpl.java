package com.kamerinos.facturacion.services.empleados;

import com.kamerinos.facturacion.models.Empleado;
import com.kamerinos.facturacion.repositories.EmpleadoRepository;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpleadoServiceImpl implements EmpleadoService {

    private final EmpleadoRepository empleadoRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public EmpleadoServiceImpl(EmpleadoRepository empleadoRepository) {
        this.empleadoRepository = empleadoRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public List<Empleado> listarEmpleados() {
        return empleadoRepository.findAll();
    }

    @Override
    public Optional<Empleado> obtenerEmpleadoPorId(Long id) {
        return empleadoRepository.findById(id);
    }

    @Override
    public Optional<Empleado> buscarEmpleadoPorCorreo(String correo) {
        return empleadoRepository.findByCorreo(correo);
    }

    @Override
    public Empleado guardarEmpleado(Empleado empleado) {
        if (empleado.getPassword() != null) {
            empleado.setPassword(passwordEncoder.encode(empleado.getPassword()));
        }
        return empleadoRepository.save(empleado);
    }

    @Override
    public void eliminarEmpleado(Long id) {
        empleadoRepository.deleteById(id);
    }
}
