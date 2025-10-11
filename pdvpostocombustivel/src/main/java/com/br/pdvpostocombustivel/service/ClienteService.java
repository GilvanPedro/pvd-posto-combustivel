package com.br.pdvpostocombustivel.service;

import com.br.pdvpostocombustivel.domain.entity.Cliente;
import com.br.pdvpostocombustivel.domain.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    /**
     * 🔹 Cadastra um novo cliente, verificando duplicidade de CPF.
     */
    public Cliente salvarCliente(Cliente cliente) {
        if (clienteRepository.existsByCpf(cliente.getCpf())) {
            throw new RuntimeException("Já existe um cliente cadastrado com este CPF!");
        }
        return clienteRepository.save(cliente);
    }

    /**
     * 🔹 Lista todos os clientes cadastrados.
     */
    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }

    /**
     * 🔹 Busca cliente pelo ID.
     */
    public Optional<Cliente> buscarPorId(Long id) {
        return clienteRepository.findById(id);
    }

    /**
     * 🔹 Atualiza os dados de um cliente existente.
     */
    public Cliente atualizarCliente(Long id, Cliente clienteAtualizado) {
        return clienteRepository.findById(id)
                .map(cliente -> {
                    cliente.setNomeCompleto(clienteAtualizado.getNomeCompleto());
                    cliente.setCpf(clienteAtualizado.getCpf());
                    cliente.setTelefone(clienteAtualizado.getTelefone());
                    cliente.setEmail(clienteAtualizado.getEmail());
                    cliente.setEndereco(clienteAtualizado.getEndereco());
                    return clienteRepository.save(cliente);
                })
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado para atualização."));
    }

    /**
     * 🔹 Remove um cliente.
     */
    public void deletarCliente(Long id) {
        if (!clienteRepository.existsById(id)) {
            throw new RuntimeException("Cliente não encontrado para exclusão.");
        }
        clienteRepository.deleteById(id);
    }
}
