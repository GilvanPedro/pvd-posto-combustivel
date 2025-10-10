package com.br.pdvpostocombustivel.service;

import com.br.pdvpostocombustivel.domain.entity.Produto;
import com.br.pdvpostocombustivel.domain.entity.Estoque;
import com.br.pdvpostocombustivel.domain.repository.ProdutoRepository;
import com.br.pdvpostocombustivel.domain.repository.EstoqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private EstoqueRepository estoqueRepository;

    /**
     * 🔹 Salva um novo produto e cria automaticamente um registro de estoque para ele.
     */
    public Produto salvarProduto(Produto produto) {
        // Salva o produto no banco
        Produto novoProduto = produtoRepository.save(produto);

        // Cria o estoque inicial para o produto
        Estoque estoque = new Estoque();
        estoque.setProduto(novoProduto);
        estoque.setQuantidadeAtual(produto.getQuantidadeEstoque() != null ? produto.getQuantidadeEstoque() : 0);
        estoque.setQuantidadeMinima(10); // valor padrão inicial
        estoqueRepository.save(estoque);

        return novoProduto;
    }

    /**
     * 🔹 Lista todos os produtos cadastrados.
     */
    public List<Produto> listarProdutos() {
        return produtoRepository.findAll();
    }

    /**
     * 🔹 Busca um produto pelo ID.
     */
    public Optional<Produto> buscarPorId(Long id) {
        return produtoRepository.findById(id);
    }

    /**
     * 🔹 Atualiza um produto existente.
     */
    public Produto atualizarProduto(Long id, Produto produtoAtualizado) {
        return produtoRepository.findById(id)
                .map(produto -> {
                    produto.setNome(produtoAtualizado.getNome());
                    produto.setPreco(produtoAtualizado.getPreco());
                    produto.setCategoria(produtoAtualizado.getCategoria());
                    produto.setQuantidadeEstoque(produtoAtualizado.getQuantidadeEstoque());

                    Produto produtoSalvo = produtoRepository.save(produto);

                    // Atualiza o estoque vinculado ao produto
                    Estoque estoque = estoqueRepository.findByProdutoId(produtoSalvo.getId())
                            .orElseGet(() -> {
                                Estoque novoEstoque = new Estoque();
                                novoEstoque.setProduto(produtoSalvo);
                                novoEstoque.setQuantidadeMinima(10);
                                return novoEstoque;
                            });

                    estoque.setQuantidadeAtual(produtoSalvo.getQuantidadeEstoque());
                    estoqueRepository.save(estoque);

                    return produtoSalvo;
                })
                .orElseThrow(() -> new RuntimeException("Produto não encontrado para atualização."));
    }

    /**
     * 🔹 Remove um produto e o estoque vinculado a ele.
     */
    public void deletarProduto(Long id) {
        // Remove o estoque antes de deletar o produto (para evitar violação de FK)
        estoqueRepository.findByProdutoId(id).ifPresent(estoqueRepository::delete);
        produtoRepository.deleteById(id);
    }
}
