package com.example.Sistema.de.Cadastro.Projeto.Controllers.ProdutoController;

import com.example.Sistema.de.Cadastro.Projeto.DTOS.ProdutoDTO.ProdutoDTO;
import com.example.Sistema.de.Cadastro.Projeto.ProdutoEntity.ProdutoEntity;
import com.example.Sistema.de.Cadastro.Projeto.Repositorios.ProdutoRepository.ProdutoRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
    private final ProdutoRepository produtoRepository;

    public ProdutoController(ProdutoRepository produtoRepository){
        this.produtoRepository = produtoRepository;
    }

    @GetMapping
    public List<ProdutoEntity> getAll(){
        return produtoRepository.findAll().stream().filter(ProdutoEntity::isAtivo).toList();
    }

    @GetMapping("/{id}")
    public ProdutoEntity getById(@PathVariable Long id){
        return produtoRepository.findById(id).orElseThrow();
    }

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

    @PutMapping
    public ProdutoEntity putProduto(@RequestBody ProdutoEntity produto){
        return produtoRepository.save(produto);
    }

    @DeleteMapping("/{id}")
    public void deleteProduto(@PathVariable Long id){
        produtoRepository.deleteById(id);
    }

}
