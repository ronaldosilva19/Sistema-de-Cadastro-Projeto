package com.example.Sistema.de.Cadastro.Projeto.Produto.service;

import com.example.Sistema.de.Cadastro.Projeto.Produto.DTO.ProdutoDTO;
import com.example.Sistema.de.Cadastro.Projeto.Produto.model.ProdutoEntity;
import com.example.Sistema.de.Cadastro.Projeto.Produto.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProdutoService {
    private final ProdutoRepository produtoRepository;

    public List<ProdutoDTO> getAll(){
        return produtoRepository.findAll().stream().map(ProdutoDTO::fromEntity).toList();
    }

    public ProdutoDTO getById(Long id){
        ProdutoEntity produto = produtoRepository.findById(id).orElseThrow(()
                -> new RuntimeException("Produto nao encontrado."));
        return ProdutoDTO.fromEntity(produto);
    }

    public ProdutoDTO createProduto(ProdutoDTO dto){
        ProdutoEntity produto = ProdutoDTO.toEntity(dto);
        return ProdutoDTO.fromEntity(produtoRepository.save(produto));
    }

    public ProdutoDTO atualizaProduto(ProdutoDTO dto){
        ProdutoEntity produto = produtoRepository.findById(dto.id()).orElseThrow(()
                        -> new RuntimeException("Produto nao encontrado"));
        produto.setNomeProduto(dto.nomeProduto());
        produto.setAtivo(dto.ativo());
        return ProdutoDTO.fromEntity(produtoRepository.save(produto));
    }

    public void deletaProduto(Long id){
        produtoRepository.deleteById(id);
    }

    public void deletaLogic(Long id){
        ProdutoEntity produto = produtoRepository.findById(id).orElseThrow(()
                -> new RuntimeException("Produto nao encontrado."));
        produto.setAtivo(false);
        produtoRepository.save(produto);
    }
}
