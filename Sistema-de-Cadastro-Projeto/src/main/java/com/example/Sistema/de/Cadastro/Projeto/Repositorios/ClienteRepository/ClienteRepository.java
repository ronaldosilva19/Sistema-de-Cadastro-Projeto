package com.example.Sistema.de.Cadastro.Projeto.Repositorios.ClienteRepository;

import com.example.Sistema.de.Cadastro.Projeto.ClienteEntity.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<ClienteEntity, Long> {
}
