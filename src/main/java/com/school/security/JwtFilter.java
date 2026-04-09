package com.school.security;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;
import java.util.List;

public class JwtFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;

        // ✅ GET PATH
        String path = req.getRequestURI();

        // 🔓 Skip JWT for public APIs
        if (path.startsWith("/auth") ||
            path.startsWith("/teachers") ||
            path.startsWith("/h2-console")) {

            chain.doFilter(request, response);
            return;
        }

        // 🔹 Get Authorization Header
        String header = req.getHeader("Authorization");

        if (header != null && header.startsWith("Bearer ")) {

            String token = header.substring(7);

            try {
                Claims claims = JwtUtil.getClaims(token);

                String username = claims.getSubject();
                String role = claims.get("role", String.class);

                var authorities = List.of(
                        new SimpleGrantedAuthority("ROLE_" + role)
                );

                UsernamePasswordAuthenticationToken auth =
                        new UsernamePasswordAuthenticationToken(
                                username,
                                null,
                                authorities
                        );

                SecurityContextHolder.getContext().setAuthentication(auth);

            } catch (Exception e) {
                System.out.println("Invalid JWT Token: " + e.getMessage());
            }
        }

        // ✅ Continue request
        chain.doFilter(request, response);
    }
}