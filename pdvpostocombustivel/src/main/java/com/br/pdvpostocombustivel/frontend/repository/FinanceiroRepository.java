package com.br.pdvpostocombustivel.frontend.repository;

import java.util.ArrayList;
import java.util.List;

public class FinanceiroRepository {

    private static final List<String[]> receitas = new ArrayList<>();
    private static final List<String[]> despesas = new ArrayList<>();

    public static void adicionarReceita(String descricao, double valor) {
        receitas.add(new String[]{descricao, String.valueOf(valor)});
    }

    public static void adicionarDespesa(String descricao, double valor) {
        despesas.add(new String[]{descricao, String.valueOf(valor)});
    }

    public static List<String[]> listarReceitas() {
        return new ArrayList<>(receitas);
    }

    public static List<String[]> listarDespesas() {
        return new ArrayList<>(despesas);
    }

    public static double calcularSaldo() {
        double totalReceitas = receitas.stream()
                .mapToDouble(r -> Double.parseDouble(r[1]))
                .sum();

        double totalDespesas = despesas.stream()
                .mapToDouble(d -> Double.parseDouble(d[1]))
                .sum();

        return totalReceitas - totalDespesas;
    }
}
