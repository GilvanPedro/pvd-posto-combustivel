package com.br.pdvpostocombustivel.frontend.repository;

import java.util.ArrayList;
import java.util.List;

public class EstoqueRepository {

    private static final List<String[]> estoque = new ArrayList<>();

    public static void adicionarEstoque(String produto, String quantidade, String custoUnitario) {
        estoque.add(new String[]{produto, quantidade, custoUnitario});
    }

    public static List<String[]> listarEstoque() {
        return new ArrayList<>(estoque);
    }
}
