package com.example.Sistema.de.Cadastro.Projeto.Controllers.ClienteController;

import com.example.Sistema.de.Cadastro.Projeto.ClienteEntity.ClienteEntity;
import com.example.Sistema.de.Cadastro.Projeto.DTOS.ClienteDTO.ClienteDTO;
import com.example.Sistema.de.Cadastro.Projeto.Repositorios.ClienteRepository.ClienteRepository;
import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    private final ClienteRepository clienteRepository;

    public ClienteController(ClienteRepository clienteRepository){
        this.clienteRepository = clienteRepository;
    }

    @GetMapping
    public List<ClienteEntity> getAll(){
        return clienteRepository.findAll().stream().filter(ClienteEntity::isAtivo).toList();
    }

    @GetMapping("/{id}")
    public ClienteEntity getById(@PathVariable Long id){
        return clienteRepository.findById(id).orElseThrow();
    }

    @PostMapping
    public ClienteEntity postCliente(@RequestBody ClienteDTO dto){
        ClienteEntity cliente = ClienteEntity.builder()
                .nome(dto.nome())
                .cpf(dto.cpf())
                .genero(dto.genero())
                .dataNascimento(dto.dataNascimento())
                .ativo(true)
                .build();
        return clienteRepository.save(cliente);
    }

    @PutMapping
    public ClienteEntity putCliente(@RequestBody ClienteEntity cliente){
        return clienteRepository.save(cliente);
    }

    @DeleteMapping("/{id}")
    public void deleteCliente(@PathVariable Long id){
        clienteRepository.deleteById(id);
    }

    @PatchMapping("/deleteLogic/{id}")
    public void deleteLogic(@PathVariable Long id){
        ClienteEntity cliente = clienteRepository.findById(id).orElseThrow();
        cliente.setAtivo(false);
        clienteRepository.save(cliente);
    }
}
