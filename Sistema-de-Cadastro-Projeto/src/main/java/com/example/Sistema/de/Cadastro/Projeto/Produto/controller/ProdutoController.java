package com.example.Sistema.de.Cadastro.Projeto.Produto.controller;

import com.example.Sistema.de.Cadastro.Projeto.Produto.DTO.ProdutoDTO;
import com.example.Sistema.de.Cadastro.Projeto.Produto.model.ProdutoEntity;
import com.example.Sistema.de.Cadastro.Projeto.Produto.repository.ProdutoRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * Cria a classe ProdutoController para fazer acesso e manipulaçôes na tabela produtos
 * do banco de dados imd_market.
 * @author Jose Ronaldo.
 */
@RestController
@RequestMapping("/produtos")
public class ProdutoController {
    private final ProdutoRepository produtoRepository;


    /**
     * Método que cria um produto.
     * @param produtoRepository ProdutoRepository-
     */
    public ProdutoController(ProdutoRepository produtoRepository){
        this.produtoRepository = produtoRepository;
    }

    /**
     * Método que lista todos os produtos que estão no sistema e estão
     * com a flag 'ativo' como true.
     */
    @GetMapping
    public List<ProdutoEntity> getAll(){
        return produtoRepository.findAll().stream().filter(ProdutoEntity::isAtivo).toList();
    }

    /**
     * Método que procura por um produto baseado no seu ID.
     * @return produto - caso ID esteja no sistema e se a flag 'ativo' == true.
     * /@return null - caso ID não esteja no sistema ou a flag 'ativo' == false.
     */
    @GetMapping("/{id}")
    public ProdutoEntity getById(@PathVariable Long id){
        ProdutoEntity produto = produtoRepository.findById(id).orElse(null);
       if(produto != null){
           if(produto.isAtivo()){
               return produto;
           }else{
               return null;
           }
       }else{
           return null;
       }
    }

    /**
     * Método que adiciona um produto na tabela produtos do banco de dados.
     * @param dto ProdutoDTO - dados dos produtos.
     * @return um produto salvo no sistema.
     */
    @PostMapping
    public ProdutoEntity postProduto(@RequestBody ProdutoDTO dto){
        ProdutoEntity produto = ProdutoEntity.builder()
                .nomeProduto(dto.nomeProduto())
                .marca(dto.marca())
                .dataFabricacao(dto.dataFabricacao())
                .dataValidade(dto.dataValidade())
                .genero(dto.genero())
                .lote(dto.lote())
                .ativo(true)
                .build();
        return produtoRepository.save(produto);
    }

    /**
     * Método que atualiza os dados de um produto na tabela.
     * @param produto ProdutoEntity - dado atualizado.
     * @return um produto atualizado no sistema.
     */
    @PutMapping
    public ProdutoEntity putProduto(@RequestBody ProdutoEntity produto){
        return produtoRepository.save(produto);
    }

    /**
     * Método que exclui um produto do sistema baseado no seu ID.
     * @param id Long - ID do produto a ser excluido.
     */
    @DeleteMapping("/{id}")
    public void deleteProduto(@PathVariable Long id){
        produtoRepository.deleteById(id);
    }

    /**
     * Método que altera a flag 'ativo' do produto baseado no seu ID
     * para false.
     * Com isso, ele não pode ser listado ou ter os dados atualizados.
     * @param id Long - ID do produto.
     */
    @PatchMapping("/delete-logic/{id}")
    public void deleteLogic(@PathVariable Long id){
        ProdutoEntity produto = produtoRepository.findById(id).orElse(null);
        if(produto != null){
            produto.setAtivo(false);
            produtoRepository.save(produto);
        }
    }

}
