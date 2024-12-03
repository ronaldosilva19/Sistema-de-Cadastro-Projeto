package com.example.Sistema.de.Cadastro.Projeto.Pedido.model;

import com.example.Sistema.de.Cadastro.Projeto.Cliente.model.ClienteEntity;
import com.example.Sistema.de.Cadastro.Projeto.Produto.model.ProdutoEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


/** Classe que cria uma tabela no banco de dados imd_market denominada 'pedidos'.
 * @author Jose Ronaldo
 */
@Entity
@Table(name = "pedidos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PedidoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String codigo;

    @ManyToMany
    @JoinTable(
            name = "pedido_produtos",
            joinColumns = @JoinColumn(name = "pedido_id"),
            inverseJoinColumns = @JoinColumn(name = "produto_id")
    )
    private List<ProdutoEntity> produtos = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private ClienteEntity cliente;
    private boolean ativo = true; // Para exclusao logica.


    // Para remover depois.
    public void setAdicionaProduto(ProdutoEntity produto){
        this.produtos.add(produto);
    }

    public void setRemoveProduto(ProdutoEntity produto){
        this.produtos.remove(produto);
    }
}
