package com.backend.cartapp;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // 1. DESHABILITAR CSRF: Es necesario para peticiones POST sin tokens.
                .csrf(AbstractHttpConfigurer::disable)
                // 2. CONFIGURAR LA AUTORIZACIÓN:
                .authorizeHttpRequests(auth -> auth
                        // ➡️ Estos endpoints deben ser totalmente públicos ⬅️
                        .requestMatchers("/login", "/signup", "/user", "/products").permitAll()
                        // 3. El resto de peticiones requieren autenticación
                        .anyRequest().authenticated()
                );

        return http.build();
    }
}