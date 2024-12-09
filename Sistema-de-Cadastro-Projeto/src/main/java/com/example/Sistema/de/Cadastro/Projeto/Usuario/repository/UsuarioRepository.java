package com.example.Sistema.de.Cadastro.Projeto.Usuario.repository;

import com.example.Sistema.de.Cadastro.Projeto.Usuario.model.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {
    Optional<UsuarioEntity> findByUsername(String username);
}
