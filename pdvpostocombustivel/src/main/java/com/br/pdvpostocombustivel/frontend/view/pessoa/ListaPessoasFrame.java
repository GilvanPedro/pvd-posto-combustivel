package com.br.pdvpostocombustivel.frontend.view.pessoa;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ListaPessoasFrame extends JFrame {

    private JTable tabelaPessoas;
    private DefaultTableModel modeloTabela;
    private JButton btnAtualizar, btnFechar;

    public ListaPessoasFrame() {
        setTitle("Lista de Pessoas Cadastradas");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        String[] colunas = {"Nome", "CPF/CNPJ", "Tipo", "Número CTPS", "Data Nasc."};
        modeloTabela = new DefaultTableModel(colunas, 0);
        tabelaPessoas = new JTable(modeloTabela);
        JScrollPane scrollPane = new JScrollPane(tabelaPessoas);

        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        btnAtualizar = new JButton("Atualizar");
        btnFechar = new JButton("Fechar");
        painelBotoes.add(btnAtualizar);
        painelBotoes.add(btnFechar);

        add(scrollPane, BorderLayout.CENTER);
        add(painelBotoes, BorderLayout.SOUTH);

        btnAtualizar.addActionListener(e -> carregarPessoas());
        btnFechar.addActionListener(e -> dispose());

        carregarPessoas();
    }

    private void carregarPessoas() {
        modeloTabela.setRowCount(0);
        List<String[]> pessoas = CadastroPessoaFrame.getPessoasCadastradas();
        for (String[] p : pessoas) {
            modeloTabela.addRow(p);
        }
    }
}