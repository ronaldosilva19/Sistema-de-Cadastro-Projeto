package com.example.Sistema.de.Cadastro.Projeto.PedidoEntity;

import com.example.Sistema.de.Cadastro.Projeto.ClienteEntity.ClienteEntity;
import com.example.Sistema.de.Cadastro.Projeto.ProdutoEntity.ProdutoEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private List<ProdutoEntity> produtos;
    private ClienteEntity cliente;
}
