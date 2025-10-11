package com.br.pdvpostocombustivel.api.pessoa;

import com.br.pdvpostocombustivel.api.pessoa.dto.PessoaRequest;
import com.br.pdvpostocombustivel.api.pessoa.dto.PessoaResponse;
import com.br.pdvpostocombustivel.domain.entity.Pessoa;
import com.br.pdvpostocombustivel.domain.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PessoaService {

    @Autowired
    private PessoaRepository repository;

    // Cadastrar nova pessoa
    public PessoaResponse create(PessoaRequest req) {
        validarUnicidadeCpfCnpj(req.cpfCnpj(), null);
        Pessoa p = toEntity(req);
        Pessoa salvo = repository.save(p);
        return toResponse(salvo);
    }

    // Listar com paginação
    public Page<PessoaResponse> list(int page, int size, String sort, String direction) {
        Sort.Direction dir = "desc".equalsIgnoreCase(direction) ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, size, Sort.by(dir, sort));
        Page<Pessoa> pageP = repository.findAll(pageable);
        return pageP.map(this::toResponse);
    }

    // Buscar por id
    public PessoaResponse findById(Long id) {
        Pessoa p = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Pessoa não encontrada. id=" + id));
        return toResponse(p);
    }

    // Atualizar
    public PessoaResponse update(Long id, PessoaRequest req) {
        Pessoa p = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Pessoa não encontrada. id=" + id));

        validarUnicidadeCpfCnpj(req.cpfCnpj(), id);

        p.setNomeCompleto(req.nomeCompleto());
        p.setCpfCnpj(req.cpfCnpj());
        p.setNumeroCtps(Long.valueOf(req.numeroCtps()));
        p.setDataNascimento(req.dataNascimento());
        p.setTipoPessoa(req.tipoPessoa());

        Pessoa salvo = repository.save(p);
        return toResponse(salvo);
    }

    // Patch parcial
    public PessoaResponse patch(Long id, PessoaRequest req) {
        Pessoa p = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Pessoa não encontrada. id=" + id));

        if (req.nomeCompleto() != null) p.setNomeCompleto(req.nomeCompleto());
        if (req.cpfCnpj() != null) {
            validarUnicidadeCpfCnpj(req.cpfCnpj(), id);
            p.setCpfCnpj(req.cpfCnpj());
        }
        if (req.numeroCtps() != null) p.setNumeroCtps(Long.valueOf(req.numeroCtps()));
        if (req.dataNascimento() != null) p.setDataNascimento(req.dataNascimento());
        if (req.tipoPessoa() != null) p.setTipoPessoa(req.tipoPessoa());

        Pessoa salvo = repository.save(p);
        return toResponse(salvo);
    }

    // Excluir
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new IllegalArgumentException("Pessoa não encontrada. id=" + id);
        }
        repository.deleteById(id);
    }

    // Helpers
    private void validarUnicidadeCpfCnpj(String cpfCnpj, Long idAtual) {
        repository.findByCpfCnpj(cpfCnpj).ifPresent(existente -> {
            if (idAtual == null || !existente.getId().equals(idAtual)) {
                throw new DataIntegrityViolationException("CPF/CNPJ já cadastrado: " + cpfCnpj);
            }
        });
    }

    private Pessoa toEntity(PessoaRequest req) {
        Pessoa p = new Pessoa();
        p.setNomeCompleto(req.nomeCompleto());
        p.setCpfCnpj(req.cpfCnpj());
        p.setNumeroCtps(Long.valueOf(req.numeroCtps()));
        p.setDataNascimento(req.dataNascimento());
        p.setTipoPessoa(req.tipoPessoa());
        return p;
    }

    private PessoaResponse toResponse(Pessoa p) {
        return new PessoaResponse(
                p.getId(),
                p.getNomeCompleto(),
                p.getCpfCnpj(),
                Math.toIntExact(p.getNumeroCtps()),
                p.getDataNascimento(),
                p.getTipoPessoa() != null ? p.getTipoPessoa().name() : null
        );
    }
}
