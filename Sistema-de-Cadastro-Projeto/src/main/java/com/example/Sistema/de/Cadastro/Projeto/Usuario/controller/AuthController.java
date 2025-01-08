package com.example.Sistema.de.Cadastro.Projeto.Usuario.controller;

import com.example.Sistema.de.Cadastro.Projeto.Usuario.DTO.LoginRequest;
import com.example.Sistema.de.Cadastro.Projeto.Usuario.jwt.JwtUtil;
import com.example.Sistema.de.Cadastro.Projeto.Usuario.model.UsuarioEntity;
import com.example.Sistema.de.Cadastro.Projeto.Usuario.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final UsuarioService usuarioService;
    private final JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.username(), loginRequest.password())
        );

        UserDetails user = (UserDetails) authentication.getPrincipal();
        String token = jwtUtil.gerarToken(user.getUsername(), user.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList());
        return ResponseEntity.ok(token);
    }

    @PostMapping("/register")
    public ResponseEntity<UsuarioEntity> register(@RequestBody UsuarioEntity usuario){
        return ResponseEntity.ok(usuarioService.criarUsuario(usuario));
    }

}
