package com.br.pdvpostocombustivel.frontend.view.estoque;

import com.br.pdvpostocombustivel.frontend.repository.EstoqueRepository;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ListaEstoqueFrame extends JFrame {

    private JTable tabelaEstoque;
    private DefaultTableModel modeloTabela;
    private JButton btnAtualizar, btnFechar;

    public ListaEstoqueFrame() {
        setTitle("Estoque Cadastrado");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        String[] colunas = {"Produto", "Quantidade", "Custo Unitário (R$)"};
        modeloTabela = new DefaultTableModel(colunas, 0);
        tabelaEstoque = new JTable(modeloTabela);
        JScrollPane scrollPane = new JScrollPane(tabelaEstoque);

        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        btnAtualizar = new JButton("Atualizar");
        btnFechar = new JButton("Fechar");
        painelBotoes.add(btnAtualizar);
        painelBotoes.add(btnFechar);

        add(scrollPane, BorderLayout.CENTER);
        add(painelBotoes, BorderLayout.SOUTH);

        btnAtualizar.addActionListener(e -> carregarEstoque());
        btnFechar.addActionListener(e -> dispose());

        carregarEstoque();
    }

    private void carregarEstoque() {
        modeloTabela.setRowCount(0);
        for (String[] e : EstoqueRepository.listarEstoque()) {
            modeloTabela.addRow(e);
        }
    }
}
