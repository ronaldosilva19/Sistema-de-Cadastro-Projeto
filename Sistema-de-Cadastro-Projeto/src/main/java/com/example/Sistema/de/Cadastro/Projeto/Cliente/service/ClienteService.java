package com.example.Sistema.de.Cadastro.Projeto.Cliente.service;

import com.example.Sistema.de.Cadastro.Projeto.Cliente.DTO.ClienteDTO;
import com.example.Sistema.de.Cadastro.Projeto.Cliente.errors.VerificadorNaoEncontrado;
import com.example.Sistema.de.Cadastro.Projeto.Cliente.model.ClienteEntity;
import com.example.Sistema.de.Cadastro.Projeto.Cliente.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClienteService {
    private final ClienteRepository clienteRepository;

    /**
     * Método que lista todos os clientes que estão no sistema e estão
     * com a flag 'ativo' como true.
     */
    public List<ClienteDTO> getAll(){
        return clienteRepository.findAll().stream().map(ClienteDTO::fromEntity).filter(ClienteDTO::ativo).toList();
    }

    /**
     * Método que procura por um cliente baseado no seu ID.
     * @return cliente - caso ID esteja no sistema e se a flag 'ativo' == true.
     * /imprime Cliente nao encontrado caso a flag 'ativo' == false.
     */
    public ClienteDTO getById(Long id){
        ClienteEntity  cliente = clienteRepository.findById(id).orElseThrow(()
            -> new RuntimeException(("Cliente nao encontrado.")));

        return ClienteDTO.fromEntity(cliente);
    }

    /**
     * Método que adiciona um cliente na tabela clientes do banco de dados.
     * @param dto ClienteDTO - dados dos clientes.
     * @return um cliente salvo no sistema.
     */
    public ClienteDTO criaCliente(ClienteDTO dto){
        ClienteEntity cliente = ClienteDTO.toEntity(dto);
        return ClienteDTO.fromEntity(clienteRepository.save(cliente));
    }

    /**
     * Método que atualiza os dados de um cliente na tabela.
     * @param dto ClienteDTO - dado atualizado.
     * @return um cliente atualizado no sistema.
     */
    public ClienteDTO atualizaCliente(ClienteDTO dto){
        ClienteEntity cliente = clienteRepository.findById(dto.id()).orElseThrow(()
        -> new RuntimeException("Cliente nao encontrado."));
        cliente.setNome(dto.nome());
        cliente.setAtivo(dto.ativo());
        return ClienteDTO.fromEntity(clienteRepository.save(cliente));
    }


    /**
     * Método que exclui um cliente do sistema baseado no seu ID.
     * @param id Long - ID do cliente a ser excluido.
     */

    public void deletaCliente(Long id){
        clienteRepository.deleteById(id);
    }


    /**
     * Método que altera a flag 'ativo' do cliente baseado no seu ID
     * para false.
     * Com isso, ele não pode ser listado ou ter os dados atualizados.
     * @param id Long - ID do cliente.
     */
    public void deletaLogic(Long id){
        ClienteEntity cliente = clienteRepository.findById(id).orElseThrow(()
        -> new RuntimeException("Cliente nao encontrado."));
        cliente.setAtivo(false);
        clienteRepository.save(cliente);
    }
}
