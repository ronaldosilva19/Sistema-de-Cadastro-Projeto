package com.example.Sistema.de.Cadastro.Projeto.Repositorios.ProdutoRepository;

import com.example.Sistema.de.Cadastro.Projeto.ProdutoEntity.ProdutoEntity;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * Cria o repositório denominado ProdutoRepository que herda de JpaRepository
 * e tem ProdutoEntity como Gererics.
 * @author Jose Ronaldo.
 */
public interface ProdutoRepository  extends JpaRepository<ProdutoEntity, Long> {
}