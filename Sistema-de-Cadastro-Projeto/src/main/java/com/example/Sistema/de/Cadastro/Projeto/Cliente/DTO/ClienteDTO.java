package com.example.Sistema.de.Cadastro.Projeto.Cliente.DTO;

import com.example.Sistema.de.Cadastro.Projeto.Cliente.model.ClienteEntity;
import com.example.Sistema.de.Cadastro.Projeto.Cliente.model.GeneroCliente;

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
        Long id,
        String nome,
        String cpf,
        GeneroCliente genero,
        LocalDate dataNascimento,
        boolean ativo
) {
    public static ClienteDTO fromEntity(ClienteEntity entity){
        return new ClienteDTO(
                entity.getId(),
                entity.getNome(),
                entity.getCpf(),
                entity.getGenero(),
                entity.getDataNascimento(),
                entity.isAtivo()
        );
    }

    public static ClienteEntity toEntity(ClienteDTO dto){
        return new ClienteEntity(
                dto.id(),
                dto.nome(),
                dto.cpf(),
                dto.genero(),
                dto.dataNascimento(),
                dto.ativo()
        );
    }
}
