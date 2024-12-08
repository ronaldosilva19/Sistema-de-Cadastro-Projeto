package com.example.Sistema.de.Cadastro.Projeto.Cliente.model;


import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
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
    @NotNull(message = "O CPF nao pode ser nulo")
    @Valid
    private String cpf;

    @Enumerated(EnumType.STRING)
    private GeneroCliente genero;
    private LocalDate dataNascimento;
    private boolean ativo = true; // Para exclusao logica.

}

