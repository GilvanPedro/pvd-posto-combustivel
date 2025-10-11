package com.br.pdvpostocombustivel.service;

import com.br.pdvpostocombustivel.domain.entity.*;
import com.br.pdvpostocombustivel.domain.repository.*;
import com.br.pdvpostocombustivel.enums.TipoTransacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class FinanceiroService {

    @Autowired
    private FinanceiroRepository financeiroRepository;

    @Autowired
    private TransacaoFinanceiraRepository transacaoFinanceiraRepository;

    /**
     * 🔹 Adiciona uma transação (receita ou despesa)
     * e atualiza o financeiro do mês automaticamente.
     */
    public TransacaoFinanceira registrarTransacao(TransacaoFinanceira transacao) {
        LocalDate dataRef = transacao.getData().withDayOfMonth(1); // agrupar por mês

        Financeiro financeiro = financeiroRepository.findByDataReferencia(dataRef)
                .orElseGet(() -> {
                    Financeiro novo = new Financeiro();
                    novo.setDataReferencia(dataRef);
                    novo.setTotalReceitas(BigDecimal.ZERO);
                    novo.setTotalDespesas(BigDecimal.ZERO);
                    novo.setSaldoFinal(BigDecimal.ZERO);
                    return financeiroRepository.save(novo);
                });

        // Vincula ao financeiro
        transacao.setFinanceiro(financeiro);
        TransacaoFinanceira transacaoSalva = transacaoFinanceiraRepository.save(transacao);

        // Atualiza valores
        atualizarTotais(financeiro, transacaoSalva);

        return transacaoSalva;
    }

    /**
     * 🔹 Atualiza o resumo de receitas/despesas no Financeiro.
     */
    private void atualizarTotais(Financeiro financeiro, TransacaoFinanceira transacao) {
        BigDecimal totalReceitas = financeiro.getTotalReceitas() != null ? financeiro.getTotalReceitas() : BigDecimal.ZERO;
        BigDecimal totalDespesas = financeiro.getTotalDespesas() != null ? financeiro.getTotalDespesas() : BigDecimal.ZERO;

        if (transacao.getTipo() == TipoTransacao.RECEITA) {
            totalReceitas = totalReceitas.add(transacao.getValor());
        } else {
            totalDespesas = totalDespesas.add(transacao.getValor());
        }

        BigDecimal saldoFinal = totalReceitas.subtract(totalDespesas);

        financeiro.setTotalReceitas(totalReceitas);
        financeiro.setTotalDespesas(totalDespesas);
        financeiro.setSaldoFinal(saldoFinal);

        financeiroRepository.save(financeiro);
    }

    /**
     * 🔹 Lista todas as transações registradas.
     */
    public List<TransacaoFinanceira> listarTransacoes() {
        return transacaoFinanceiraRepository.findAll();
    }

    /**
     * 🔹 Lista o resumo financeiro de todos os meses.
     */
    public List<Financeiro> listarResumoFinanceiro() {
        return financeiroRepository.findAll();
    }

    /**
     * 🔹 Busca o financeiro de um mês específico.
     */
    public Financeiro buscarPorMes(LocalDate dataReferencia) {
        return financeiroRepository.findByDataReferencia(dataReferencia.withDayOfMonth(1))
                .orElseThrow(() -> new RuntimeException("Nenhum registro financeiro encontrado para o mês informado."));
    }
}
