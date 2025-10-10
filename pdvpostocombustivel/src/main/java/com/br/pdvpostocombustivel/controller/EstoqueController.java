package com.br.pdvpostocombustivel.controller;

import com.br.pdvpostocombustivel.domain.entity.Estoque;
import com.br.pdvpostocombustivel.service.EstoqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/estoques")
@CrossOrigin(origins = "*") // permite acesso do front-end
public class EstoqueController {

    @Autowired
    private EstoqueService estoqueService;

    // 🔹 Registrar entrada ou saída no estoque
    @PostMapping("/movimentar/{produtoId}")
    public ResponseEntity<Estoque> movimentarEstoque(
            @PathVariable Long produtoId,
            @RequestParam int quantidade,
            @RequestParam String tipoMovimento
    ) {
        Estoque estoque = estoqueService.registrarMovimento(produtoId, quantidade, tipoMovimento);
        return ResponseEntity.ok(estoque);
    }

    // 🔹 Listar todos os estoques
    @GetMapping
    public ResponseEntity<List<Estoque>> listarEstoques() {
        List<Estoque> estoques = estoqueService.listarEstoques();
        return ResponseEntity.ok(estoques);
    }

    // 🔹 Buscar estoque de um produto específico
    @GetMapping("/{produtoId}")
    public ResponseEntity<Estoque> buscarEstoquePorProduto(@PathVariable Long produtoId) {
        Estoque estoque = estoqueService.buscarPorProduto(produtoId);
        return ResponseEntity.ok(estoque);
    }

    // 🔹 Atualizar quantidade mínima de um produto
    @PutMapping("/{produtoId}/minimo")
    public ResponseEntity<Estoque> atualizarQuantidadeMinima(
            @PathVariable Long produtoId,
            @RequestParam int novaMinima
    ) {
        Estoque estoqueAtualizado = estoqueService.atualizarQuantidadeMinima(produtoId, novaMinima);
        return ResponseEntity.ok(estoqueAtualizado);
    }
}
