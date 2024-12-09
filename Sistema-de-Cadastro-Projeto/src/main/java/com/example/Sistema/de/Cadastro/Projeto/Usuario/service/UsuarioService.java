package com.example.Sistema.de.Cadastro.Projeto.Usuario.service;

import com.example.Sistema.de.Cadastro.Projeto.Usuario.model.UsuarioEntity;
import com.example.Sistema.de.Cadastro.Projeto.Usuario.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService implements UserDetailsService {
    private final UsuarioRepository usuarioRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usuarioRepository.findByUsername(username).orElseThrow(() ->
        new UsernameNotFoundException("Usuario nao encontrado."));
    }

    public UsuarioEntity criarUsuario(UsuarioEntity usuario){
        usuario.setPassword(new BCryptPasswordEncoder().encode(usuario.getPassword()));
        return usuarioRepository.save(usuario);
    }
}
