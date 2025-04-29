package com.kamerinos.facturacion.config;

import com.kamerinos.facturacion.services.empleados.EmpleadosDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final EmpleadosDetailsService empleadoDetailsService;

    public SecurityConfig(EmpleadosDetailsService empleadoDetailsService) {
        this.empleadoDetailsService = empleadoDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authorizeRequests ->
                authorizeRequests
                    .requestMatchers("/login", "/register", "/resources/**", "/assets/**", "/static/**").permitAll() // Permitir login, registro, recursos estáticos
                    .requestMatchers("/admin/**").hasRole("ADMIN") // Solo admin puede entrar a /admin/**
                    .requestMatchers("/empleado/**").hasRole("EMPLEADO") // Solo empleados pueden acceder a /empleado/**
                    .anyRequest().authenticated() // Lo demás requiere login
            )
            .formLogin(formLogin -> formLogin
                    .loginPage("/login") // Página personalizada de login
                    .defaultSuccessUrl("/", true) // Usaremos una URL de éxito predeterminada
                    .permitAll()
            )
            .logout(logout -> logout
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/login?logout=true")
                    .permitAll()
            )
            .sessionManagement(session ->
                session.maximumSessions(1).expiredUrl("/login?expired=true")
            )
            .csrf(csrf -> csrf.disable()); // CSRF desactivado para desarrollo, recuerda activarlo después

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return empleadoDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService());
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }
}
