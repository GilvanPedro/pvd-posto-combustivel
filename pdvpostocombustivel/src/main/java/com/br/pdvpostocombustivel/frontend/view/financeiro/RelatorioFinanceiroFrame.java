package com.br.pdvpostocombustivel.frontend.view.financeiro;

import com.br.pdvpostocombustivel.frontend.repository.FinanceiroRepository;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class RelatorioFinanceiroFrame extends JFrame {

    private JTable tabelaReceitas, tabelaDespesas;
    private JLabel lblSaldo;

    public RelatorioFinanceiroFrame() {
        setTitle("Relatório Financeiro");
        setSize(800, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // Tabelas
        JPanel painelTabelas = new JPanel(new GridLayout(1, 2, 10, 10));

        tabelaReceitas = new JTable(new DefaultTableModel(new Object[]{"Descrição", "Valor (R$)"}, 0));
        tabelaDespesas = new JTable(new DefaultTableModel(new Object[]{"Descrição", "Valor (R$)"}, 0));

        painelTabelas.add(new JScrollPane(tabelaReceitas));
        painelTabelas.add(new JScrollPane(tabelaDespesas));

        // Rodapé
        lblSaldo = new JLabel("Saldo Total: R$ 0.00", SwingConstants.CENTER);
        lblSaldo.setFont(new Font("Arial", Font.BOLD, 16));

        JButton btnAtualizar = new JButton("Atualizar");
        btnAtualizar.addActionListener(e -> atualizarDados());

        JPanel painelRodape = new JPanel(new BorderLayout());
        painelRodape.add(lblSaldo, BorderLayout.CENTER);
        painelRodape.add(btnAtualizar, BorderLayout.EAST);

        add(painelTabelas, BorderLayout.CENTER);
        add(painelRodape, BorderLayout.SOUTH);

        atualizarDados();
    }

    private void atualizarDados() {
        DefaultTableModel modeloR = (DefaultTableModel) tabelaReceitas.getModel();
        DefaultTableModel modeloD = (DefaultTableModel) tabelaDespesas.getModel();

        modeloR.setRowCount(0);
        modeloD.setRowCount(0);

        for (String[] r : FinanceiroRepository.listarReceitas()) {
            modeloR.addRow(r);
        }
        for (String[] d : FinanceiroRepository.listarDespesas()) {
            modeloD.addRow(d);
        }

        double saldo = FinanceiroRepository.calcularSaldo();
        lblSaldo.setText(String.format("Saldo Total: R$ %.2f", saldo));
        lblSaldo.setForeground(saldo >= 0 ? Color.BLUE : Color.RED);
    }
}
