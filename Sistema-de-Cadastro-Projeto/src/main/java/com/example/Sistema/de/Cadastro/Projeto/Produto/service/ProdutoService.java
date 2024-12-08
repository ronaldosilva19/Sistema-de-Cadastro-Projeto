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


    /**
     * Método que lista todos os produtos que estão no sistema e estão
     * com a flag 'ativo' como true.
     */
    public List<ProdutoDTO> getAll(){
        return produtoRepository.findAll().stream().map(ProdutoDTO::fromEntity).filter(ProdutoDTO::ativo).toList();
    }

    /**
     * Método que procura por um produto baseado no seu ID.
     * @return produto - caso ID esteja no sistema e se a flag 'ativo' == true.
     * Imprime Produto nao encontrado. - caso ID não esteja no sistema ou a flag 'ativo' == false.
     */
    public ProdutoDTO getById(Long id){
        ProdutoEntity produto = produtoRepository.findById(id).orElseThrow(()
                -> new RuntimeException("Produto nao encontrado."));
        return ProdutoDTO.fromEntity(produto);
    }

    /**
     * Método que adiciona um produto na tabela produtos do banco de dados.
     * @param dto  ProdutoDTO - dados dos produtos.
     * @return um produto salvo no sistema.
     */
    public ProdutoDTO criaProduto(ProdutoDTO dto){
        ProdutoEntity produto = ProdutoDTO.toEntity(dto);
        return ProdutoDTO.fromEntity(produtoRepository.save(produto));
    }

    /**
     * Método que atualiza os dados de um produto na tabela.
     * @param dto ProdutoDTO - dado atualizado.
     * @return um produto atualizado no sistema.
     */
    public ProdutoDTO atualizaProduto(ProdutoDTO dto){
        ProdutoEntity produto = produtoRepository.findById(dto.id()).orElseThrow(()
                        -> new RuntimeException("Produto nao encontrado"));
        produto.setNomeProduto(dto.nomeProduto());
        produto.setAtivo(dto.ativo());
        return ProdutoDTO.fromEntity(produtoRepository.save(produto));
    }

    /**
     * Método que exclui um produto do sistema baseado no seu ID.
     * @param id Long - ID do produto a ser excluido.
     */
    public void deletaProduto(Long id){
        produtoRepository.deleteById(id);
    }

    /**
     * Método que altera a flag 'ativo' do produto baseado no seu ID
     * para false.
     * Com isso, ele não pode ser listado ou ter os dados atualizados.
     * @param id Long - ID do produto.
     */
    public void deletaLogic(Long id){
        ProdutoEntity produto = produtoRepository.findById(id).orElseThrow(()
                -> new RuntimeException("Produto nao encontrado."));
        produto.setAtivo(false);
        produtoRepository.save(produto);
    }
}
