package com.example.Sistema.de.Cadastro.Projeto.Pedido.DTO;

import com.example.Sistema.de.Cadastro.Projeto.Cliente.model.ClienteEntity;
import com.example.Sistema.de.Cadastro.Projeto.Pedido.model.PedidoEntity;
import com.example.Sistema.de.Cadastro.Projeto.Produto.model.ProdutoEntity;

import java.util.List;


/** Cria o Record DTO chamdo ClienteDTO para armazenar os dados
 * do request de forma temporária..
 * @author Jose Ronaldo
 *
 * @param codigo String - codigo do produto.
 * @param produtosIds List<ProdutoEntity> = lista de produtos.
 * @param clienteId ClienteEntity = um cliente.
 */
public record PedidoDTO(
        Long id,
        String codigo,
        Long clienteId,
        List<Long> produtosIds,
        boolean ativo
) {
    public static PedidoDTO fromEntity(PedidoEntity entity){
        return new PedidoDTO(
                entity.getId(),
                entity.getCodigo(),
                entity.getCliente().getId(),
                entity.getProdutos().stream().map(ProdutoEntity::getId).toList(),
                entity.isAtivo()
        );
    }

    public static PedidoEntity toEntity(PedidoDTO dto, ClienteEntity cliente, List<ProdutoEntity> produtos){
        PedidoEntity pedido = new PedidoEntity();
        pedido.setId(dto.id);
        pedido.setCodigo(dto.codigo);
        pedido.setCliente(cliente);
        pedido.setProdutos(produtos);
        pedido.setAtivo(dto.ativo());

        return pedido;
    }
}
