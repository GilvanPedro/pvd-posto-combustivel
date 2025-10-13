package com.br.pdvpostocombustivel.frontend.repository;

import java.util.ArrayList;
import java.util.List;

public class PessoaRepository {

    private static final List<String[]> pessoas = new ArrayList<>();

    public static void adicionarPessoa(String nome, String cpfCnpj, String tipoPessoa, String numeroCtps) {
        pessoas.add(new String[]{nome, cpfCnpj, tipoPessoa, numeroCtps});
    }

    public static List<String[]> listarPessoas() {
        return new ArrayList<>(pessoas);
    }
}
