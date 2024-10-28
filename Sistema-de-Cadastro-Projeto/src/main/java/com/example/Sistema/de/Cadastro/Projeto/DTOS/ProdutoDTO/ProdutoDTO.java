package com.example.Sistema.de.Cadastro.Projeto.DTOS.ProdutoDTO;

import java.time.LocalDate;
import com.example.Sistema.de.Cadastro.Projeto.ProdutoEntity.GeneroProduto;

public record ProdutoDTO(
        String nomeProduto,
        String marca,
        LocalDate dataFabricacao,
        LocalDate dataValidade,
        GeneroProduto genero,
        String lote
) {}
