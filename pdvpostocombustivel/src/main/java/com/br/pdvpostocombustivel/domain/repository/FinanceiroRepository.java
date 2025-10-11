package com.br.pdvpostocombustivel.domain.repository;

import com.br.pdvpostocombustivel.domain.entity.Financeiro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface FinanceiroRepository extends JpaRepository<Financeiro, Long> {
    Optional<Financeiro> findByDataReferencia(LocalDate dataReferencia);
}
