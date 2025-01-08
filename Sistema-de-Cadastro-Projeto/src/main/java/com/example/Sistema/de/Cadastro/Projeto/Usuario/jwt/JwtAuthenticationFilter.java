package com.example.Sistema.de.Cadastro.Projeto.Usuario.jwt;

import com.example.Sistema.de.Cadastro.Projeto.Usuario.service.UsuarioService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.lang.NonNullApi;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final UsuarioService usuarioService;

    public JwtAuthenticationFilter(JwtUtil jwtUtil, UsuarioService usuarioService){
        this.jwtUtil = jwtUtil;
        this.usuarioService = usuarioService;
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");
        if(authHeader != null && authHeader.startsWith("Bearer")){
            String token  = authHeader.substring(7);
            String username = jwtUtil.extrairUsername(token);

            if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){
                UserDetails userDetails = usuarioService.loadUserByUsername(username);

                if(jwtUtil.validarToken(token, userDetails)) {
                    UsernamePasswordAuthenticationToken authenticationToken =
                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                }
            }
        }

        filterChain.doFilter(request, response);
    }
}
