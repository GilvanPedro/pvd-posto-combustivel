package com.br.pdvpostocombustivel.service;

import com.br.pdvpostocombustivel.domain.entity.Estoque;
import com.br.pdvpostocombustivel.domain.entity.MovimentacaoEstoque;
import com.br.pdvpostocombustivel.domain.entity.Produto;
import com.br.pdvpostocombustivel.domain.repository.EstoqueRepository;
import com.br.pdvpostocombustivel.domain.repository.MovimentacaoEstoqueRepository;
import com.br.pdvpostocombustivel.domain.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovimentacaoEstoqueService {

    @Autowired
    private MovimentacaoEstoqueRepository movimentacaoRepository;

    @Autowired
    private EstoqueRepository estoqueRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    // 🔹 Registrar movimentação (entrada ou saída)
    public MovimentacaoEstoque registrarMovimentacao(Long produtoId, Integer quantidade, MovimentacaoEstoque.TipoMovimentacao tipo) {
        Produto produto = produtoRepository.findById(produtoId)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado."));

        Estoque estoque = estoqueRepository.findByProdutoId(produtoId)
                .orElseThrow(() -> new RuntimeException("Estoque não encontrado para o produto."));

        MovimentacaoEstoque movimentacao = new MovimentacaoEstoque();
        movimentacao.setProduto(produto);
        movimentacao.setEstoque(estoque);
        movimentacao.setQuantidade(quantidade);
        movimentacao.setTipo(tipo);

        // Atualiza o estoque de acordo com o tipo
        if (tipo == MovimentacaoEstoque.TipoMovimentacao.ENTRADA) {
            estoque.setQuantidadeAtual(estoque.getQuantidadeAtual() + quantidade);
        } else if (tipo == MovimentacaoEstoque.TipoMovimentacao.SAIDA) {
            if (estoque.getQuantidadeAtual() < quantidade) {
                throw new RuntimeException("Quantidade em estoque insuficiente!");
            }
            estoque.setQuantidadeAtual(estoque.getQuantidadeAtual() - quantidade);
        }

        estoqueRepository.save(estoque);
        return movimentacaoRepository.save(movimentacao);
    }

    public List<MovimentacaoEstoque> listarMovimentacoes() {
        return movimentacaoRepository.findAll();
    }
}
