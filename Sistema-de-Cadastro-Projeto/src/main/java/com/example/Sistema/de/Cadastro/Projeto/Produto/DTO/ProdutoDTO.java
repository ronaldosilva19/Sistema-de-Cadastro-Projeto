package com.example.Sistema.de.Cadastro.Projeto.Produto.DTO;

import com.example.Sistema.de.Cadastro.Projeto.Produto.model.GeneroProduto;
import com.example.Sistema.de.Cadastro.Projeto.Produto.model.ProdutoEntity;

import java.time.LocalDate;


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
        Long id,
        String nomeProduto,
        String marca,
        LocalDate dataFabricacao,
        LocalDate dataValidade,
        GeneroProduto genero,
        String lote,
        boolean ativo
) {
    public static ProdutoDTO fromEntity(ProdutoEntity entity){
        return new ProdutoDTO(
                entity.getId(),
                entity.getNomeProduto(),
                entity.getMarca(),
                entity.getDataFabricacao(),
                entity.getDataValidade(),
                entity.getGenero(),
                entity.getLote(),
                entity.isAtivo()
        );
    }

    public static ProdutoEntity toEntity(ProdutoDTO dto) {

        return new ProdutoEntity(
                dto.id(),
                dto.nomeProduto(),
                dto.marca(),
                dto.dataFabricacao(),
                dto.dataValidade(),
                dto.genero(),
                dto.lote(),
                dto.ativo()
        );
    }
}
