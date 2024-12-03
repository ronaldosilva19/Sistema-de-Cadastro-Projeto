package com.example.Sistema.de.Cadastro.Projeto.Pedido.controller;


import com.example.Sistema.de.Cadastro.Projeto.Pedido.DTO.PedidoDTO;
import com.example.Sistema.de.Cadastro.Projeto.Pedido.model.PedidoEntity;
import com.example.Sistema.de.Cadastro.Projeto.Pedido.repository.PedidoRepository;
import com.example.Sistema.de.Cadastro.Projeto.Produto.model.ProdutoEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Cria a classe PedidoController para fazer acesso e manipulaçôes na tabela pedidos
 * do banco de dados imd_market.
 * @author Jose Ronaldo.
 */
@RestController
@RequestMapping("/pedidos")
public class PedidoController {
    private final PedidoRepository pedidoRepository;

    /**
     * Método que cria um pedido.
     * @param pedidoRepository ProdutoRepository-
     */
    public PedidoController(PedidoRepository pedidoRepository){
        this.pedidoRepository = pedidoRepository;
    }

    /**
     * Método que lista todos os pedidos que estão no sistema.
     */
    @GetMapping
    public List<PedidoEntity> getAll(){
        return pedidoRepository.findAll().stream().filter(PedidoEntity::isAtivo).toList();
    }

    /**
     * Método que procura por um pedido baseado no seu ID.
     * @return pedido - caso ID esteja no sistema.
     */

    @GetMapping("/{id}")
    public PedidoEntity getById(@PathVariable Long id){
        return pedidoRepository.findById(id).orElse(null);
    }

    @PostMapping
    public PedidoEntity postPedido(@RequestBody PedidoDTO dto){
        PedidoEntity pedido = PedidoEntity.builder()
                .codigo(dto.codigo())
                .produtos(dto.produtos())
                .cliente(dto.cliente())
                .ativo(true)
                .build();
        return pedidoRepository.save(pedido);
    }

    @PutMapping
    public PedidoEntity putPedido(@RequestBody PedidoEntity pedido){
        return pedidoRepository.save(pedido);
    }

    @DeleteMapping("{/id}")
    public void deletaPedido(@PathVariable Long id){
        pedidoRepository.deleteById(id);
    }

    @PatchMapping("/delete-logic/{id}")
    public void deleteLogic(@PathVariable Long id){
        PedidoEntity pedido = pedidoRepository.findById(id).orElse(null);
        if(pedido != null){
            pedido.setAtivo(false);
            pedidoRepository.save(pedido);
        }
    }

    @PostMapping
    public void adicionarProduto(@RequestBody ProdutoEntity produto){
        PedidoEntity pedido = new PedidoEntity();
        pedido.setAdicionaProduto(produto);
    }

    @DeleteMapping
    public void removeProduto(@RequestBody ProdutoEntity produto){
        PedidoEntity pedido = new PedidoEntity();
        pedido.setRemoveProduto(produto);
    }
}
