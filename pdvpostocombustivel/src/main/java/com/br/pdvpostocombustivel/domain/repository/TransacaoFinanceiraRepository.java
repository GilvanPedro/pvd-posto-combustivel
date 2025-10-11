package com.br.pdvpostocombustivel.domain.repository;

import com.br.pdvpostocombustivel.domain.entity.TransacaoFinanceira;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransacaoFinanceiraRepository extends JpaRepository<TransacaoFinanceira, Long> {
}
