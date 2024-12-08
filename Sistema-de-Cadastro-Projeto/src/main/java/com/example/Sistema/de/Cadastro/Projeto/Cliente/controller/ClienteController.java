package com.example.Sistema.de.Cadastro.Projeto.Cliente.controller;

import com.example.Sistema.de.Cadastro.Projeto.Cliente.DTO.ClienteDTO;
import com.example.Sistema.de.Cadastro.Projeto.Cliente.model.ClienteEntity;
import com.example.Sistema.de.Cadastro.Projeto.Cliente.repository.ClienteRepository;
import com.example.Sistema.de.Cadastro.Projeto.Cliente.service.ClienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * Cria a classe ClienteController para fazer acesso e manipulaçôes na tabela clientes
 * do banco de dados imd_market.
 * Os metodos abaixo apenas chamam os metodos que fazem a logica do aplicacao na tabela clientes
 * da classe ClienteService.
 * @author Jose Ronaldo.
 */
@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor
public class ClienteController {
    private final ClienteService clienteService;

    @GetMapping
    public List<ClienteDTO> getAll() {
        return clienteService.getAll();
    }

    @GetMapping("/{id}")
    public ClienteDTO getById(@PathVariable Long id) {
        return clienteService.getById(id);
    }

    @PostMapping
    public ClienteDTO postCliente(@RequestBody @Valid ClienteDTO clienteDTO){
       return clienteService.criaCliente(clienteDTO);
    }

    @PutMapping
    public ClienteDTO putCliente(@RequestBody ClienteDTO clienteDTO){
        return clienteService.atualizaCliente(clienteDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteCliente(@PathVariable Long id){
        clienteService.deletaCliente(id);
    }

    @PatchMapping("/delete-logic/{id}")
    public void deleteLogic(@PathVariable Long id){
        clienteService.deletaLogic(id);
    }
}
