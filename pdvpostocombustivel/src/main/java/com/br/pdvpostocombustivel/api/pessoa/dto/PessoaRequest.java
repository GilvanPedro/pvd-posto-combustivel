package com.br.pdvpostocombustivel.api.pessoa.dto;

import com.br.pdvpostocombustivel.domain.entity.Pessoa;
import java.time.LocalDate;

public record PessoaRequest(
        String nomeCompleto,
        String cpfCnpj,
        Integer numeroCtps,             // <-- Corrigido
        LocalDate dataNascimento,
        Pessoa.TipoPessoa tipoPessoa    // <-- Corrigido
) {}
