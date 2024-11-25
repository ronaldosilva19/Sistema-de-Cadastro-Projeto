package com.example.Sistema.de.Cadastro.Projeto.Controllers.PedidoController;


import com.example.Sistema.de.Cadastro.Projeto.PedidoEntity.PedidoEntity;
import com.example.Sistema.de.Cadastro.Projeto.Repositorios.PedidosRepository.PedidoRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        return pedidoRepository.findAll().stream().toList();
    }

    /**
     * Método que procura por um pedido baseado no seu ID.
     * @return pedido - caso ID esteja no sistema.
     */

    @GetMapping("/{id}")
    public PedidoEntity getById(@PathVariable Long id){
        return pedidoRepository.findById(id).orElse(null);
    }
}
