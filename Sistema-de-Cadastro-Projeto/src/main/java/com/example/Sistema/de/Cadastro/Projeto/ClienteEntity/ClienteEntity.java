package com.example.Sistema.de.Cadastro.Projeto.ClienteEntity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

/** Classe que cria uma tabela no banco de dados imd_market denominada 'clientes'.
 * @author Jose Ronaldo
 */
@Entity
@Table(name = "clientes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClienteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String cpf;

    @Enumerated(EnumType.STRING)
    private GeneroCliente genero;
    private LocalDate dataNascimento;
    private boolean ativo = true; // Para exclusao logica.

}

