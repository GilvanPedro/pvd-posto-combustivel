package com.br.pdvpostocombustivel.controller;

import com.br.pdvpostocombustivel.domain.entity.Financeiro;
import com.br.pdvpostocombustivel.domain.entity.TransacaoFinanceira;
import com.br.pdvpostocombustivel.service.FinanceiroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/financeiro")
public class FinanceiroController {

    @Autowired
    private FinanceiroService financeiroService;

    /**
     * 🔹 Registrar uma nova transação (receita ou despesa)
     * Exemplo de JSON:
     * {
     *   "descricao": "Compra de combustível",
     *   "tipo": "DESPESA",
     *   "valor": 2500.00,
     *   "data": "2025-10-10"
     * }
     */
    @PostMapping("/transacoes")
    public ResponseEntity<TransacaoFinanceira> registrarTransacao(@RequestBody TransacaoFinanceira transacao) {
        TransacaoFinanceira novaTransacao = financeiroService.registrarTransacao(transacao);
        return ResponseEntity.ok(novaTransacao);
    }

    /**
     * 🔹 Listar todas as transações
     */
    @GetMapping("/transacoes")
    public ResponseEntity<List<TransacaoFinanceira>> listarTransacoes() {
        return ResponseEntity.ok(financeiroService.listarTransacoes());
    }

    /**
     * 🔹 Listar todos os resumos financeiros mensais
     */
    @GetMapping("/resumo")
    public ResponseEntity<List<Financeiro>> listarResumoFinanceiro() {
        return ResponseEntity.ok(financeiroService.listarResumoFinanceiro());
    }

    /**
     * 🔹 Consultar o resumo financeiro de um mês específico
     * Exemplo de chamada:
     * GET /api/financeiro/resumo/2025-10-01
     */
    @GetMapping("/resumo/{data}")
    public ResponseEntity<Financeiro> buscarPorMes(
            @PathVariable("data") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataReferencia) {

        Financeiro financeiro = financeiroService.buscarPorMes(dataReferencia);
        return ResponseEntity.ok(financeiro);
    }
}
