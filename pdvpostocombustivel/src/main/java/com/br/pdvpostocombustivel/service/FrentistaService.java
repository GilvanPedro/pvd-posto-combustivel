package com.br.pdvpostocombustivel.service;

import com.br.pdvpostocombustivel.domain.entity.Frentista;
import com.br.pdvpostocombustivel.domain.repository.FrentistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FrentistaService {

    @Autowired
    private FrentistaRepository frentistaRepository;

    // 🔹 Cadastrar novo frentista
    public Frentista salvarFrentista(Frentista frentista) {
        return frentistaRepository.save(frentista);
    }

    // 🔹 Listar todos os frentistas
    public List<Frentista> listarFrentistas() {
        return frentistaRepository.findAll();
    }

    // 🔹 Buscar frentista por ID
    public Optional<Frentista> buscarPorId(Long id) {
        return frentistaRepository.findById(id);
    }

    // 🔹 Atualizar frentista existente
    public Frentista atualizarFrentista(Long id, Frentista dadosAtualizados) {
        return frentistaRepository.findById(id)
                .map(frentista -> {
                    frentista.setNomeCompleto(dadosAtualizados.getNomeCompleto());
                    frentista.setCpf(dadosAtualizados.getCpf());
                    frentista.setTelefone(dadosAtualizados.getTelefone());
                    frentista.setTurno(dadosAtualizados.getTurno());
                    frentista.setSalario(dadosAtualizados.getSalario());
                    return frentistaRepository.save(frentista);
                })
                .orElseThrow(() -> new RuntimeException("Frentista não encontrado."));
    }

    // 🔹 Deletar frentista
    public void deletarFrentista(Long id) {
        if (!frentistaRepository.existsById(id)) {
            throw new RuntimeException("Frentista não encontrado para exclusão.");
        }
        frentistaRepository.deleteById(id);
    }
}
