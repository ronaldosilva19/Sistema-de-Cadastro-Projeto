package com.example.Sistema.de.Cadastro.Projeto.Pedido.service;

import com.example.Sistema.de.Cadastro.Projeto.Cliente.model.ClienteEntity;
import com.example.Sistema.de.Cadastro.Projeto.Cliente.repository.ClienteRepository;
import com.example.Sistema.de.Cadastro.Projeto.Pedido.DTO.PedidoDTO;
import com.example.Sistema.de.Cadastro.Projeto.Pedido.model.PedidoEntity;
import com.example.Sistema.de.Cadastro.Projeto.Pedido.repository.PedidoRepository;
import com.example.Sistema.de.Cadastro.Projeto.Produto.model.ProdutoEntity;
import com.example.Sistema.de.Cadastro.Projeto.Produto.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PedidoService {
    private final PedidoRepository pedidoRepository;
    private final ProdutoRepository produtoRepository;
    private final ClienteRepository clienteRepository;

    /**
     * Método que lista todos os pedidos que estão no sistema e estão
     * com a flag 'ativo' como true.
     */
    public List<PedidoDTO> getAll(){
        return pedidoRepository.findAll().stream().map(PedidoDTO::fromEntity).filter(PedidoDTO::ativo).toList();
    }

    /**
     * Método que procura por um pedido baseado no seu ID.
     * @return pedido - caso ID esteja no sistema e se a flag 'ativo' == true.
     * /imprime Pedido nao encontrado - caso ID não esteja no sistema ou a flag 'ativo' == false.
     */
    public PedidoDTO getById(Long id){
        PedidoEntity pedido = pedidoRepository.findById(id).orElseThrow(()
        -> new RuntimeException("Pedido nao encontrado"));
        return PedidoDTO.fromEntity(pedido);
    }

    /**
     * Método que adiciona um pedido na tabela pedidos do banco de dados.
     * @param dto  PedidoDTO - dados dos produtos.
     * @return um pedido salvo no sistema.
     */
    public PedidoDTO criaPedido(PedidoDTO dto){
        ClienteEntity cliente = clienteRepository.findById(dto.clienteId()).orElseThrow(()
        -> new RuntimeException("Cliente nao encontrado."));

        List<ProdutoEntity> produtos = produtoRepository.findAllById(dto.produtosIds());

        PedidoEntity pedido = PedidoDTO.toEntity(dto, cliente, produtos);

        return PedidoDTO.fromEntity(pedidoRepository.save(pedido));
    }

    /**
     * Método que atualiza os dados de um pedido na tabela.
     * @param dto PedidoDTO - dado atualizado.
     * @return um pedido atualizado no sistema.
     */
    public PedidoDTO atualizaPedido(PedidoDTO dto){
        PedidoEntity pedido = pedidoRepository.findById(dto.id()).orElseThrow(()
        -> new RuntimeException("Pedido nao encontrado."));

        if(dto.clienteId() != null){
            ClienteEntity cliente = clienteRepository.findById(dto.clienteId()).orElseThrow(()
            -> new RuntimeException("Cliente nao encontrado."));
            pedido.setCliente(cliente);
        }

        if(dto.produtosIds() != null){
            List<ProdutoEntity> produtos = produtoRepository.findAllById(dto.produtosIds());
            pedido.setProdutos(produtos);
        }

        pedido.setCodigo(dto.codigo());
        pedido.setAtivo(dto.ativo());
        return PedidoDTO.fromEntity(pedidoRepository.save(pedido));
    }

    /**
     * Método que exclui um pedido do sistema baseado no seu ID.
     * @param id Long - ID do pedido a ser excluido.
     */
    public void deletaPedido(Long id){
        pedidoRepository.deleteById(id);
    }

    /**
     * Método que altera a flag 'ativo' do pedido baseado no seu ID
     * para false.
     * Com isso, ele não pode ser listado ou ter os dados atualizados.
     * @param id Long - ID do pedido.
     */
    public void deleteLogic(Long id){
        PedidoEntity pedido = pedidoRepository.findById(id).orElseThrow(()
        -> new RuntimeException("Pedido nao encontrado"));
        pedido.setAtivo(false);
        pedidoRepository.save(pedido);
    }

    /**
     * Metodo que adiciona um pedido a lista de pedidos do cliente.
     * @param pedidoId Long - id do pedido.
     * @param produtoId Long - ido do produto.
     */

    public PedidoDTO adicionarPedido(Long pedidoId, Long produtoId){
        PedidoEntity pedido = pedidoRepository.findById(pedidoId).orElseThrow(()
        -> new RuntimeException("Pedido nao encontrado"));

        ProdutoEntity produto = produtoRepository.findById(produtoId).orElseThrow(()
        -> new RuntimeException("Produto nao encontrado"));

        pedido.getProdutos().add(produto);
        return PedidoDTO.fromEntity(pedidoRepository.save(pedido));
    }

    /**
     * Metodo que remove um pedido a lista de pedidos do cliente.
     * @param pedidoId Long - id do pedido.
     * @param produtoId Long - ido do produto.
     */
    public PedidoDTO removerProduto(Long pedidoId, Long produtoId){
        PedidoEntity pedido = pedidoRepository.findById(pedidoId).orElseThrow(()
        -> new RuntimeException("Pedido nao encontrado"));

        ProdutoEntity produto = produtoRepository.findById(produtoId).orElseThrow(()
        -> new RuntimeException("Produto nao encontrado."));

        pedido.getProdutos().remove(produto);
        return PedidoDTO.fromEntity(pedidoRepository.save(pedido));
    }
}
