package com.school.config;

import com.school.security.JwtFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;

import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

    	http
        .csrf(csrf -> csrf.disable())

        .headers(headers -> headers.frameOptions(frame -> frame.disable())) // ✅ FIX H2

        .sessionManagement(session ->
            session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        )

        .authorizeHttpRequests(auth -> auth
            .requestMatchers("/auth/**").permitAll()
            .requestMatchers("/teachers/**").permitAll()
            .requestMatchers("/h2-console/**").permitAll() // ✅ IMPORTANT
            .anyRequest().authenticated()
        )

        .addFilterBefore(new JwtFilter(),
            UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}