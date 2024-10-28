package com.example.Sistema.de.Cadastro.Projeto.DTOS.ClienteDTO;

import java.time.LocalDate;
import com.example.Sistema.de.Cadastro.Projeto.ClienteEntity.GeneroCliente;

public record ClienteDTO(
       String nome,
       String cpf,
       GeneroCliente genero,
       LocalDate dataNascimento
) {}
