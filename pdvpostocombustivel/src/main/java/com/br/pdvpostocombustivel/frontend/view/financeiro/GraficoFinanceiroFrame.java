package com.br.pdvpostocombustivel.frontend.view.financeiro;

import com.br.pdvpostocombustivel.frontend.repository.FinanceiroRepository;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.*;
import java.awt.*;

public class GraficoFinanceiroFrame extends JFrame {

    public GraficoFinanceiroFrame() {
        setTitle("Gráficos Financeiros");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(1, 2, 10, 10));

        add(criarGraficoBarras());
        add(criarGraficoPizza());
    }

    private JPanel criarGraficoBarras() {
        double totalReceitas = FinanceiroRepository.listarReceitas().stream()
                .mapToDouble(r -> Double.parseDouble(r[1]))
                .sum();

        double totalDespesas = FinanceiroRepository.listarDespesas().stream()
                .mapToDouble(d -> Double.parseDouble(d[1]))
                .sum();

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(totalReceitas, "Financeiro", "Receitas");
        dataset.addValue(totalDespesas, "Financeiro", "Despesas");

        JFreeChart grafico = ChartFactory.createBarChart(
                "Comparativo Financeiro",
                "Categoria",
                "Valor (R$)",
                dataset
        );

        return new ChartPanel(grafico);
    }

    private JPanel criarGraficoPizza() {
        double totalReceitas = FinanceiroRepository.listarReceitas().stream()
                .mapToDouble(r -> Double.parseDouble(r[1]))
                .sum();

        double totalDespesas = FinanceiroRepository.listarDespesas().stream()
                .mapToDouble(d -> Double.parseDouble(d[1]))
                .sum();

        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("Receitas", totalReceitas);
        dataset.setValue("Despesas", totalDespesas);

        JFreeChart grafico = ChartFactory.createPieChart(
                "Distribuição Financeira",
                dataset,
                true,
                true,
                false
        );

        return new ChartPanel(grafico);
    }
}
