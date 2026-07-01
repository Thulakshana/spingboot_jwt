package org.example.usersystemjwt.config;

import org.example.usersystemjwt.security.JwtFilter;
import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    private final JwtFilter jwtFilter;

    public SecurityConfig(
            JwtFilter jwtFilter){

        this.jwtFilter = jwtFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http)
            throws Exception {

        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth ->
                        auth
                                .requestMatchers(
                                        "/api/users/signup",
                                        "/api/users/login")
                                .permitAll()
                                .anyRequest()
                                .authenticated()
                );

        return http.build();
    }
}