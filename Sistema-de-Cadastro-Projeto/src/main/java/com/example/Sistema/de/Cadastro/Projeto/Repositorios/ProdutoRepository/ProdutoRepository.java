package com.example.Sistema.de.Cadastro.Projeto.Repositorios.ProdutoRepository;

import com.example.Sistema.de.Cadastro.Projeto.ProdutoEntity.ProdutoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository  extends JpaRepository<ProdutoEntity, Long> {
}
