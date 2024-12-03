package com.example.Sistema.de.Cadastro.Projeto.Pedido;

import com.example.Sistema.de.Cadastro.Projeto.Cliente.ClienteEntity;
import com.example.Sistema.de.Cadastro.Projeto.Produto.ProdutoEntity;

import java.util.List;


/** Cria o Record DTO chamdo ClienteDTO para armazenar os dados
 * do request de forma temporária..
 * @author Jose Ronaldo
 *
 * @param codigo String - codigo do produto.
 * @param produtos List<ProdutoEntity> = lista de produtos.
 * @param cliente ClienteEntity = um cliente.
 */
public record PedidoDTO(
        String codigo,
        List<ProdutoEntity> produtos,
        ClienteEntity cliente
) { }
