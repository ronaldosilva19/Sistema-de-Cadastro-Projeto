package com.example.Sistema.de.Cadastro.Projeto.Produto.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;


/** Classe que cria uma tabela no banco de dados imd_market denominada 'produtos'.
 * @author Jose Ronaldo
 */
@Entity
@Table(name = "produtos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProdutoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomeProduto;
    private String marca;
    private LocalDate dataFabricacao;
    private  LocalDate dataValidade;

    @Enumerated(EnumType.STRING)
    private GeneroProduto genero;
    private String lote;
    private boolean ativo = true; // Para exclusao logica
}

