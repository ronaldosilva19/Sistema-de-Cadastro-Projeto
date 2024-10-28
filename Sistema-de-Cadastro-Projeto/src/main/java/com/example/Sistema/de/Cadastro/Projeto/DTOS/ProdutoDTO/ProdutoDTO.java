package com.example.Sistema.de.Cadastro.Projeto.DTOS.ProdutoDTO;

import java.time.LocalDate;
import com.example.Sistema.de.Cadastro.Projeto.ProdutoEntity.GeneroProduto;


/** Cria o Record DTO chamdo ClienteDTO para armazenar os dados
 * do request de forma temporária..
 * @author Jose Ronaldo
 *
 * @param nomeProduto String - nome do produto.
 * @param marca String =  marca do produto.
 * @param dataFabricacao LocalDate =  deta de fabricacao do produto.
 * @param dataValidade LocalDate =  deta de validade do produto.
 * @param genero GereroProduto = tipo de produto escolhido dentre as opcoes.
 * @param lote String = lote do produto.
 */

public record ProdutoDTO(
        String nomeProduto,
        String marca,
        LocalDate dataFabricacao,
        LocalDate dataValidade,
        GeneroProduto genero,
        String lote
) {}