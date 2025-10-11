package com.br.pdvpostocombustivel.controller;

import com.br.pdvpostocombustivel.domain.entity.MovimentacaoEstoque;
import com.br.pdvpostocombustivel.service.MovimentacaoEstoqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movimentacoes")
@CrossOrigin(origins = "*")
public class MovimentacaoEstoqueController {

    @Autowired
    private MovimentacaoEstoqueService movimentacaoService;

    @PostMapping("/entrada/{produtoId}")
    public ResponseEntity<MovimentacaoEstoque> registrarEntrada(@PathVariable Long produtoId, @RequestParam Integer quantidade) {
        MovimentacaoEstoque mov = movimentacaoService.registrarMovimentacao(produtoId, quantidade, MovimentacaoEstoque.TipoMovimentacao.ENTRADA);
        return ResponseEntity.ok(mov);
    }

    @PostMapping("/saida/{produtoId}")
    public ResponseEntity<MovimentacaoEstoque> registrarSaida(@PathVariable Long produtoId, @RequestParam Integer quantidade) {
        MovimentacaoEstoque mov = movimentacaoService.registrarMovimentacao(produtoId, quantidade, MovimentacaoEstoque.TipoMovimentacao.SAIDA);
        return ResponseEntity.ok(mov);
    }

    @GetMapping
    public ResponseEntity<List<MovimentacaoEstoque>> listarMovimentacoes() {
        return ResponseEntity.ok(movimentacaoService.listarMovimentacoes());
    }
}
