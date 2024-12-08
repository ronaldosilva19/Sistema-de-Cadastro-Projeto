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

    @GetMapping
    public List<ProdutoDTO> getAll(){
        return produtoService.getAll();
    }

    @GetMapping("/{id}")
    public ProdutoDTO getById(@PathVariable Long id){
        return produtoService.getById(id);
    }


    @PostMapping
    public ProdutoDTO postProduto(@RequestBody @Validated ProdutoDTO produtoDTO){
        return produtoService.criaProduto(produtoDTO);
    }

    @PutMapping
    public ProdutoDTO putProduto(@RequestBody @Validated ProdutoDTO produtoDTO){
        return produtoService.atualizaProduto(produtoDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteProduto(@PathVariable Long id){
        produtoService.deletaProduto(id);
    }

    @PatchMapping("/delete-logic/{id}")
    public void deleteLogic(@PathVariable Long id){
        produtoService.deletaLogic(id);
    }

}
