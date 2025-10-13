package com.br.pdvpostocombustivel.frontend.repository;

import java.util.ArrayList;
import java.util.List;

public class ProdutoRepository {

    private static final List<String[]> produtos = new ArrayList<>();

    public static void adicionarProduto(String codigo, String nome, String categoria, String preco, String quantidade) {
        produtos.add(new String[]{codigo, nome, categoria, preco, quantidade});
    }

    public static List<String[]> listarProdutos() {
        return new ArrayList<>(produtos);
    }
}
