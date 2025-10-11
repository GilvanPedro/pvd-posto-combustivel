package com.br.pdvpostocombustivel.service;

import com.br.pdvpostocombustivel.domain.entity.Gerente;
import com.br.pdvpostocombustivel.domain.repository.GerenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GerenteService {

    @Autowired
    private GerenteRepository gerenteRepository;

    // 🔹 Cadastrar novo gerente
    public Gerente salvarGerente(Gerente gerente) {
        if (gerenteRepository.existsByUsuario(gerente.getUsuario())) {
            throw new RuntimeException("Usuário já existente!");
        }
        return gerenteRepository.save(gerente);
    }

    // 🔹 Listar todos os gerentes
    public List<Gerente> listarGerentes() {
        return gerenteRepository.findAll();
    }

    // 🔹 Buscar gerente por ID
    public Optional<Gerente> buscarPorId(Long id) {
        return gerenteRepository.findById(id);
    }

    // 🔹 Atualizar dados do gerente
    public Gerente atualizarGerente(Long id, Gerente dadosAtualizados) {
        return gerenteRepository.findById(id)
                .map(gerente -> {
                    gerente.setNomeCompleto(dadosAtualizados.getNomeCompleto());
                    gerente.setCpf(dadosAtualizados.getCpf());
                    gerente.setEmail(dadosAtualizados.getEmail());
                    gerente.setTelefone(dadosAtualizados.getTelefone());
                    return gerenteRepository.save(gerente);
                })
                .orElseThrow(() -> new RuntimeException("Gerente não encontrado."));
    }

    // 🔹 Deletar gerente
    public void deletarGerente(Long id) {
        if (!gerenteRepository.existsById(id)) {
            throw new RuntimeException("Gerente não encontrado para exclusão.");
        }
        gerenteRepository.deleteById(id);
    }

    // 🔹 Login do gerente
    public Optional<Gerente> autenticar(String usuario, String senha) {
        return gerenteRepository.findByUsuarioAndSenha(usuario, senha);
    }
}