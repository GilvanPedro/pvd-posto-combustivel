package com.br.pdvpostocombustivel.service;

import com.br.pdvpostocombustivel.domain.entity.Estoque;
import com.br.pdvpostocombustivel.domain.entity.Produto;
import com.br.pdvpostocombustivel.domain.repository.EstoqueRepository;
import com.br.pdvpostocombustivel.domain.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EstoqueService {

    @Autowired
    private EstoqueRepository estoqueRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    // Criar ou atualizar o estoque de um produto
    public Estoque registrarMovimento(Long produtoId, int quantidade, String tipoMovimento) {
        Produto produto = produtoRepository.findById(produtoId)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        Estoque estoque = estoqueRepository.findByProdutoId(produtoId)
                .orElse(new Estoque(produto, 0, 100, tipoMovimento));

        if (tipoMovimento.equalsIgnoreCase("ENTRADA")) {
            estoque.setQuantidadeAtual(estoque.getQuantidadeAtual() + quantidade);
        } else if (tipoMovimento.equalsIgnoreCase("SAIDA")) {
            estoque.setQuantidadeAtual(estoque.getQuantidadeAtual() - quantidade);
        } else {
            throw new IllegalArgumentException("Tipo de movimento inválido. Use ENTRADA ou SAIDA.");
        }

        estoque.setTipoMovimento(tipoMovimento.toUpperCase());
        estoque.setUltimaAtualizacao(LocalDateTime.now());

        return estoqueRepository.save(estoque);
    }

    // Listar todos os estoques
    public List<Estoque> listarEstoques() {
        return estoqueRepository.findAll();
    }

    // Buscar estoque de um produto
    public Estoque buscarPorProduto(Long produtoId) {
        return estoqueRepository.findByProdutoId(produtoId)
                .orElseThrow(() -> new RuntimeException("Estoque não encontrado para o produto ID " + produtoId));
    }

    // Atualizar quantidade mínima
    public Estoque atualizarQuantidadeMinima(Long produtoId, int novaMinima) {
        Estoque estoque = buscarPorProduto(produtoId);
        estoque.setQuantidadeMinima(novaMinima);
        return estoqueRepository.save(estoque);
    }
}
