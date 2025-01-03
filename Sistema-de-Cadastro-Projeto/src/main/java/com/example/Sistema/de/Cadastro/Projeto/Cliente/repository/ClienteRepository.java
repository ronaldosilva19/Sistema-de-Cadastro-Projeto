package com.example.Sistema.de.Cadastro.Projeto.Cliente.repository;

import com.example.Sistema.de.Cadastro.Projeto.Cliente.model.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Cria o repositório denominado ClienteRepository que herda de JpaRepository
 * e tem ClienteEntity como Gererics.
 * @author Jose Ronaldo.
 */
public interface ClienteRepository extends JpaRepository<ClienteEntity, Long> {
}
