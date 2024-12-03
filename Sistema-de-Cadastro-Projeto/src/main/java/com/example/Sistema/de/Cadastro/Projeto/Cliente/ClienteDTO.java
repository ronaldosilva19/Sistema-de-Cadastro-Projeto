package com.example.Sistema.de.Cadastro.Projeto.Cliente;

import java.time.LocalDate;


/** Cria o Record DTO chamdo ClienteDTO para armazenar os dados
 * do request de forma temporária..
 * @author Jose Ronaldo
 *
 * @param nome String - nome do cliente.
 * @param cpf String - cpf do cliente.
 * @param genero GeneroCliente - genero do cliente.
 * @param dataNascimento LocalDate - data de nascimento do cliente.
 */
public record ClienteDTO(
       String nome,
       String cpf,
       GeneroCliente genero,
       LocalDate dataNascimento
) {}
