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

    public List<PedidoDTO> getAll(){
        return pedidoRepository.findAll().stream().map(PedidoDTO::fromEntity).filter(PedidoDTO::ativo).toList();
    }

    public PedidoDTO getById(Long id){
        PedidoEntity pedido = pedidoRepository.findById(id).orElseThrow(()
        -> new RuntimeException("Pedido nao encontrado"));
        return PedidoDTO.fromEntity(pedido);
    }

    public PedidoDTO createPedido(PedidoDTO dto){
        ClienteEntity cliente = clienteRepository.findById(dto.clienteId()).orElseThrow(()
        -> new RuntimeException("Cliente nao encontrado."));

        List<ProdutoEntity> produtos = produtoRepository.findAllById(dto.produtosIds());

        PedidoEntity pedido = new PedidoEntity();
        pedido.setCodigo(dto.codigo());
        pedido.setCliente(cliente);
        pedido.setProdutos(produtos);
        pedido.setAtivo(true);

        return PedidoDTO.fromEntity(pedidoRepository.save(pedido));
    }

    public PedidoDTO updatePedido(PedidoDTO dto){
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


    public void deletePedido(Long id){
        pedidoRepository.deleteById(id);
    }

    public void deleteLogic(Long id){
        PedidoEntity pedido = pedidoRepository.findById(id).orElseThrow(()
        -> new RuntimeException("Pedido nao encontrado"));
        pedido.setAtivo(false);
        pedidoRepository.save(pedido);
    }

    public PedidoDTO adicionarPedido(Long pedidoId, Long produtoId){
        PedidoEntity pedido = pedidoRepository.findById(pedidoId).orElseThrow(()
        -> new RuntimeException("Pedido nao encontrado"));

        ProdutoEntity produto = produtoRepository.findById(produtoId).orElseThrow(()
        -> new RuntimeException("Produto nao encontrado"));

        pedido.getProdutos().add(produto);
        return PedidoDTO.fromEntity(pedidoRepository.save(pedido));
    }

    public PedidoDTO removerProduto(Long pedidoId, Long produtoId){
        PedidoEntity pedido = pedidoRepository.findById(pedidoId).orElseThrow(()
        -> new RuntimeException("Pedido nao encontrado"));

        ProdutoEntity produto = produtoRepository.findById(produtoId).orElseThrow(()
        -> new RuntimeException("Produto nao encontrado."));

        pedido.getProdutos().remove(produto);
        return PedidoDTO.fromEntity(pedidoRepository.save(pedido));
    }
}
