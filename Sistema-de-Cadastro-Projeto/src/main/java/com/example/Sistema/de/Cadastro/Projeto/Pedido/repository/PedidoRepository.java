package com.example.Sistema.de.Cadastro.Projeto.Pedido.repository;

import com.example.Sistema.de.Cadastro.Projeto.Pedido.model.PedidoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Cria o repositório denominado PedidoRepository que herda de JpaRepository
 * e tem PedidoEntity como Gererics.
 * @author Jose Ronaldo.
 */
public interface PedidoRepository extends JpaRepository<PedidoEntity, Long> {
}
