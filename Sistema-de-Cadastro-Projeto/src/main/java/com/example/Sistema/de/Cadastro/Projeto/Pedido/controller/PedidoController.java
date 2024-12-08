package com.example.Sistema.de.Cadastro.Projeto.Pedido.controller;


import com.example.Sistema.de.Cadastro.Projeto.Pedido.DTO.PedidoDTO;
import com.example.Sistema.de.Cadastro.Projeto.Pedido.model.PedidoEntity;
import com.example.Sistema.de.Cadastro.Projeto.Pedido.repository.PedidoRepository;
import com.example.Sistema.de.Cadastro.Projeto.Pedido.service.PedidoService;
import com.example.Sistema.de.Cadastro.Projeto.Produto.model.ProdutoEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Cria a classe PedidoController para fazer acesso e manipulaçôes na tabela pedidos
 * do banco de dados imd_market.
 * @author Jose Ronaldo.
 */
@RestController
@RequestMapping("/pedidos")
@RequiredArgsConstructor
public class PedidoController {
    private final PedidoService pedidoService;


    /**
     * Método que lista todos os pedidos que estão no sistema.
     */
    @GetMapping
    public List<PedidoDTO> getAll(){
        return pedidoService.getAll();
    }

    /**
     * Método que procura por um pedido baseado no seu ID.
     * @return pedido - caso ID esteja no sistema.
     */

    @GetMapping("/{id}")
    public PedidoDTO getById(@PathVariable Long id){
        return pedidoService.getById(id);
    }

    @PostMapping
    public PedidoDTO postPedido(@RequestBody @Validated PedidoDTO pedidoDTO){
        return pedidoService.criaPedido(pedidoDTO);
    }

    @PutMapping
    public PedidoDTO putPedido(@RequestBody @Validated PedidoDTO pedidoDTO){
        return pedidoService.atualizaPedido(pedidoDTO);
    }

    @DeleteMapping("/{id}")
    public void deletePedido(@PathVariable Long id){
       pedidoService.deletaPedido(id);
    }

    @PatchMapping("/deleteLogic/{id}")
    public void deleteLogic(@PathVariable Long id){
        pedidoService.deleteLogic(id);
    }

    @PutMapping("/{pedidoId}/adicionarProduto/{produtoId}")
    public PedidoDTO adicionaProduto(@PathVariable Long pedidoId, @PathVariable Long produtoId){
        return pedidoService.adicionarProduto(pedidoId, produtoId);
    }

    @PutMapping("/{pedidoId}/removerProduto/{produtoId}")
    public PedidoDTO removerProduto(@PathVariable Long pedidoId, @PathVariable Long produtoId){
        return pedidoService.removerProduto(pedidoId, produtoId);
    }
}
