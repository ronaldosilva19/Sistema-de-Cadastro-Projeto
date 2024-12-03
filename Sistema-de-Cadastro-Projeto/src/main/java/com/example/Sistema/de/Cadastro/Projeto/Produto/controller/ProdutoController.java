package com.example.Sistema.de.Cadastro.Projeto.Produto.controller;

import com.example.Sistema.de.Cadastro.Projeto.Produto.DTO.ProdutoDTO;
import com.example.Sistema.de.Cadastro.Projeto.Produto.model.ProdutoEntity;
import com.example.Sistema.de.Cadastro.Projeto.Produto.service.ProdutoService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * Cria a classe ProdutoController para fazer acesso e manipulaçôes na tabela produtos
 * do banco de dados imd_market.
 * @author Jose Ronaldo.
 */
@RestController
@RequestMapping("/produtos")
@RequiredArgsConstructor
public class ProdutoController {
    private final ProdutoService produtoService;

    /**
     * Método que lista todos os produtos que estão no sistema e estão
     * com a flag 'ativo' como true.
     */
    @GetMapping
    public List<ProdutoDTO> getAll(){
        return produtoService.getAll();
    }

    /**
     * Método que procura por um produto baseado no seu ID.
     * @return produto - caso ID esteja no sistema e se a flag 'ativo' == true.
     * /@return null - caso ID não esteja no sistema ou a flag 'ativo' == false.
     */
    @GetMapping("/{id}")
    public ProdutoDTO getById(@PathVariable Long id){
        return produtoService.getById(id);
    }

    /**
     * Método que adiciona um produto na tabela produtos do banco de dados.
     * @param produtoDTO  ProdutoDTO - dados dos produtos.
     * @return um produto salvo no sistema.
     */
    @PostMapping
    public ProdutoDTO postProduto(@RequestBody @Validated ProdutoDTO produtoDTO){
        return produtoService.createProduto(produtoDTO);
    }

    /**
     * Método que atualiza os dados de um produto na tabela.
     * @param produtoDTO ProdutoEntity - dado atualizado.
     * @return um produto atualizado no sistema.
     */
    @PutMapping
    public ProdutoDTO putProduto(@RequestBody @Validated ProdutoDTO produtoDTO){
        return produtoService.atualizaProduto(produtoDTO);
    }

    /**
     * Método que exclui um produto do sistema baseado no seu ID.
     * @param id Long - ID do produto a ser excluido.
     */
    @DeleteMapping("/{id}")
    public void deleteProduto(@PathVariable Long id){
        produtoService.deletaProduto(id);
    }

    /**
     * Método que altera a flag 'ativo' do produto baseado no seu ID
     * para false.
     * Com isso, ele não pode ser listado ou ter os dados atualizados.
     * @param id Long - ID do produto.
     */
    @PatchMapping("/delete-logic/{id}")
    public void deleteLogic(@PathVariable Long id){
        produtoService.deletaLogic(id);
    }

}
