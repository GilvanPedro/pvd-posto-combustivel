package com.br.pdvpostocombustivel.api.pessoa.dto;

import com.br.pdvpostocombustivel.domain.entity.Pessoa;
import java.time.LocalDate;

public record PessoaResponse(
        Long id,
        String nomeCompleto,
        String cpfCnpj,
        Integer numeroCtps,             // <-- Corrigido
        LocalDate dataNascimento,
        String tipoPessoa               // <-- pode ser String (name())
) {}
