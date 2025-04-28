package com.kamerinos.facturacion.services.empleados;

import com.kamerinos.facturacion.models.Empleado;
import com.kamerinos.facturacion.repositories.EmpleadoRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class EmpleadosDetailsService implements UserDetailsService {

    private final EmpleadoRepository empleadoRepository;

    public EmpleadosDetailsService(EmpleadoRepository empleadoRepository) {
        this.empleadoRepository = empleadoRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String correo) throws UsernameNotFoundException {
        return empleadoRepository.findByCorreo(correo)
                .orElseThrow(() -> new UsernameNotFoundException("Empleado no encontrado con correo: " + correo));
    }
}
