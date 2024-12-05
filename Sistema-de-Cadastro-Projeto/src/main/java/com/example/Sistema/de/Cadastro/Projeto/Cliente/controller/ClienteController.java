package com.example.Sistema.de.Cadastro.Projeto.Cliente.controller;

import com.example.Sistema.de.Cadastro.Projeto.Cliente.DTO.ClienteDTO;
import com.example.Sistema.de.Cadastro.Projeto.Cliente.model.ClienteEntity;
import com.example.Sistema.de.Cadastro.Projeto.Cliente.repository.ClienteRepository;
import com.example.Sistema.de.Cadastro.Projeto.Cliente.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * Cria a classe ClienteController para fazer acesso e manipulaçôes na tabela clientes
 * do banco de dados imd_market.
 * @author Jose Ronaldo.
 */
@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor
public class ClienteController {
    private final ClienteService clienteService;

    /**
     * Método que lista todos os clientes que estão no sistema e estão
     * com a flag 'ativo' como true.
     */
    @GetMapping
    public List<ClienteDTO> getAll() {
        return clienteService.getAll();
    }

    /**
     * Método que procura por um cliente baseado no seu ID.
     * @return cliente - caso ID esteja no sistema e se a flag 'ativo' == true.
     * /@return null - caso ID não esteja no sistema ou a flag 'ativo' == false.
     */
    @GetMapping("/{id}")
    public ClienteDTO getById(@PathVariable Long id) {
        return clienteService.getById(id);
    }

    /**
     * Método que adiciona um cliente na tabela clientes do banco de dados.
     * @param clienteDTO ClienteDTO - dados dos clientes.
     * @return um cliente salvo no sistema.
     */
    @PostMapping
    public ClienteDTO postCliente(@RequestBody @Validated ClienteDTO clienteDTO){
       return clienteService.createCliente(clienteDTO);
    }

    /**
     * Método que atualiza os dados de um cliente na tabela.
     * @param clienteDTO ClienteEntity - dado atualizado.
     * @return um cliente atualizado no sistema.
     */
    @PutMapping
    public ClienteDTO putCliente(@RequestBody @Validated ClienteDTO clienteDTO){
        return clienteService.updateCliente(clienteDTO);
    }

    /**
     * Método que exclui um cliente do sistema baseado no seu ID.
     * @param id Long - ID do cliente a ser excluido.
     */
    @DeleteMapping("/{id}")
    public void deleteCliente(@PathVariable Long id){
        clienteService.deletaCliente(id);
    }

    /**
     * Método que altera a flag 'ativo' do cliente baseado no seu ID
     * para false.
     * Com isso, ele não pode ser listado ou ter os dados atualizados.
     * @param id Long - ID do cliente.
     */
    @PatchMapping("/delete-logic/{id}")
    public void deleteLogic(@PathVariable Long id){
        clienteService.deletaLogic(id);
    }
}
