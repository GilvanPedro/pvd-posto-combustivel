package com.br.pdvpostocombustivel.controller;

import com.br.pdvpostocombustivel.domain.entity.Preco;
import com.br.pdvpostocombustivel.service.PrecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/precos")
public class PrecoController {

    @Autowired
    private PrecoService precoService;

    /**
     * 🔹 Define um novo preço para um produto (e desativa o anterior)
     */
    @PostMapping("/definir/{produtoId}")
    public ResponseEntity<Preco> definirNovoPreco(
            @PathVariable Long produtoId,
            @RequestParam BigDecimal valor
    ) {
        Preco preco = precoService.definirNovoPreco(produtoId, valor);
        return ResponseEntity.ok(preco);
    }

    /**
     * 🔹 Busca o preço atual de um produto
     */
    @GetMapping("/atual/{produtoId}")
    public ResponseEntity<Preco> buscarPrecoAtual(@PathVariable Long produtoId) {
        return precoService.buscarPrecoAtual(produtoId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * 🔹 Lista o histórico de preços de um produto
     */
    @GetMapping("/historico/{produtoId}")
    public ResponseEntity<List<Preco>> listarHistorico(@PathVariable Long produtoId) {
        List<Preco> historico = precoService.listarHistoricoPrecos(produtoId);
        return ResponseEntity.ok(historico);
    }

    /**
     * 🔹 Deleta um preço específico (opcional)
     */
    @DeleteMapping("/{precoId}")
    public ResponseEntity<Void> deletarPreco(@PathVariable Long precoId) {
        precoService.deletarPreco(precoId);
        return ResponseEntity.noContent().build();
    }
}
