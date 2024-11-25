package com.example.Sistema.de.Cadastro.Projeto.Repositorios.PedidosRepository;

import com.example.Sistema.de.Cadastro.Projeto.PedidoEntity.PedidoEntity;
import com.example.Sistema.de.Cadastro.Projeto.PedidoEntity.PedidoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Cria o repositório denominado PedidoRepository que herda de JpaRepository
 * e tem PedidoEntity como Gererics.
 * @author Jose Ronaldo.
 */
public interface PedidoRepository extends JpaRepository<PedidoEntity, Long> {
}
