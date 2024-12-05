package com.example.Sistema.de.Cadastro.Projeto.Cliente.service;

import com.example.Sistema.de.Cadastro.Projeto.Cliente.DTO.ClienteDTO;
import com.example.Sistema.de.Cadastro.Projeto.Cliente.model.ClienteEntity;
import com.example.Sistema.de.Cadastro.Projeto.Cliente.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteService {
    private final ClienteRepository clienteRepository;

    public List<ClienteDTO> getAll(){
        return clienteRepository.findAll().stream().map(ClienteDTO::fromEntity).toList();
    }

    public ClienteDTO getById(Long id){
        ClienteEntity  cliente = clienteRepository.findById(id).orElseThrow(()
            -> new RuntimeException(("Cliente nao encontrado.")));

        return ClienteDTO.fromEntity(cliente);
    }

    public ClienteDTO createCliente(ClienteDTO dto){
        ClienteEntity cliente = ClienteDTO.toEntity(dto);
        return ClienteDTO.fromEntity(clienteRepository.save(cliente));
    }

    public ClienteDTO updateCliente(ClienteDTO dto){
        ClienteEntity cliente = clienteRepository.findById(dto.id()).orElseThrow(()
        -> new RuntimeException("Cliente nao encontrado."));
        cliente.setNome(dto.nome());
        cliente.setAtivo(dto.ativo());
        return ClienteDTO.fromEntity(clienteRepository.save(cliente));
    }

    public void deletaCliente(Long id){
        clienteRepository.deleteById(id);
    }

    public void deletaLogic(Long id){
        ClienteEntity cliente = clienteRepository.findById(id).orElseThrow(()
        -> new RuntimeException("Cliente nao encontrado."));
        cliente.setAtivo(false);
        clienteRepository.save(cliente);
    }
}
