package com.br.pdvpostocombustivel.service;

import com.br.pdvpostocombustivel.domain.entity.Preco;
import com.br.pdvpostocombustivel.domain.entity.Produto;
import com.br.pdvpostocombustivel.domain.repository.PrecoRepository;
import com.br.pdvpostocombustivel.domain.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PrecoService {

    @Autowired
    private PrecoRepository precoRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    /**
     * 🔹 Cadastrar novo preço para um produto.
     * Desativa o preço atual, se existir, e ativa o novo.
     */
    public Preco definirNovoPreco(Long produtoId, BigDecimal novoValor) {
        Produto produto = produtoRepository.findById(produtoId)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        // Desativar o preço atual, se existir
        precoRepository.findByProdutoAndAtivoTrue(produto)
                .ifPresent(precoAtual -> {
                    precoAtual.setAtivo(false);
                    precoAtual.setDataFim(LocalDateTime.now());
                    precoRepository.save(precoAtual);
                });

        // Criar novo preço ativo
        Preco novoPreco = new Preco();
        novoPreco.setProduto(produto);
        novoPreco.setValor(novoValor);
        novoPreco.setDataInicio(LocalDateTime.now());
        novoPreco.setAtivo(true);

        return precoRepository.save(novoPreco);
    }

    /**
     * 🔹 Retorna o preço atual de um produto.
     */
    public Optional<Preco> buscarPrecoAtual(Long produtoId) {
        Produto produto = produtoRepository.findById(produtoId)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        return precoRepository.findByProdutoAndAtivoTrue(produto);
    }

    /**
     * 🔹 Lista todos os preços (histórico) de um produto.
     */
    public List<Preco> listarHistoricoPrecos(Long produtoId) {
        Produto produto = produtoRepository.findById(produtoId)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        return precoRepository.findByProduto(produto);
    }

    /**
     * 🔹 Deleta um preço específico (caso precise).
     */
    public void deletarPreco(Long precoId) {
        precoRepository.deleteById(precoId);
    }
}
