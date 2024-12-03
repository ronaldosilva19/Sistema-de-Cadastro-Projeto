package com.example.Sistema.de.Cadastro.Projeto.Cliente;

import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * Cria a classe ClienteController para fazer acesso e manipulaçôes na tabela clientes
 * do banco de dados imd_market.
 * @author Jose Ronaldo.
 */
@RestController
@RequestMapping("/clientes")
public class ClienteController {
    private final ClienteRepository clienteRepository;

    /**
     * Método que cria um cliente.
     * @param clienteRepository ClienteRepository-
     */
    public ClienteController(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    /**
     * Método que lista todos os clientes que estão no sistema e estão
     * com a flag 'ativo' como true.
     */
    @GetMapping
    public List<ClienteEntity> getAll() {
        return clienteRepository.findAll().stream().filter(ClienteEntity::isAtivo).toList();
    }

    /**
     * Método que procura por um cliente baseado no seu ID.
     * @return cliente - caso ID esteja no sistema e se a flag 'ativo' == true.
     * /@return null - caso ID não esteja no sistema ou a flag 'ativo' == false.
     */
    @GetMapping("/{id}")
    public ClienteEntity getById(@PathVariable Long id) {
        ClienteEntity cliente = clienteRepository.findById(id).orElse(null);
        if (cliente != null) {
            if(cliente.isAtivo()){
                return cliente;
            }else{
                return null;
            }
        }else{
            return null;
        }
    }

    /**
     * Método que adiciona um cliente na tabela clientes do banco de dados.
     * @param dto ClienteDTO - dados dos clientes.
     * @return um cliente salvo no sistema.
     */
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

    /**
     * Método que atualiza os dados de um cliente na tabela.
     * @param cliente ClienteEntity - dado atualizado.
     * @return um cliente atualizado no sistema.
     */
    @PutMapping
    public ClienteEntity putCliente(@RequestBody ClienteEntity cliente){
        return clienteRepository.save(cliente);
    }

    /**
     * Método que exclui um cliente do sistema baseado no seu ID.
     * @param id Long - ID do cliente a ser excluido.
     */
    @DeleteMapping("/{id}")
    public void deleteCliente(@PathVariable Long id){
        clienteRepository.deleteById(id);
    }

    /**
     * Método que altera a flag 'ativo' do cliente baseado no seu ID
     * para false.
     * Com isso, ele não pode ser listado ou ter os dados atualizados.
     * @param id Long - ID do cliente.
     */
    @PatchMapping("/delete-logic/{id}")
    public void deleteLogic(@PathVariable Long id){
        ClienteEntity cliente = clienteRepository.findById(id).orElse(null);
        if(cliente != null){
            cliente.setAtivo(false);
            clienteRepository.save(cliente);
        }
    }
}
