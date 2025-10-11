package com.br.pdvpostocombustivel.domain.repository;

import com.br.pdvpostocombustivel.domain.entity.Preco;
import com.br.pdvpostocombustivel.domain.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PrecoRepository extends JpaRepository<Preco, Long> {

    // 🔹 Buscar todos os preços de um produto
    List<Preco> findByProduto(Produto produto);

    // 🔹 Buscar o preço ativo de um produto
    Optional<Preco> findByProdutoAndAtivoTrue(Produto produto);
}
