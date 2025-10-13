package com.br.pdvpostocombustivel.frontend.view.produto;

import com.br.pdvpostocombustivel.frontend.repository.ProdutoRepository;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ListaProdutosFrame extends JFrame {

    private JTable tabelaProdutos;
    private DefaultTableModel modeloTabela;
    private JButton btnAtualizar, btnFechar;

    public ListaProdutosFrame() {
        setTitle("Lista de Produtos");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        String[] colunas = {"Código", "Nome", "Categoria", "Preço", "Quantidade"};
        modeloTabela = new DefaultTableModel(colunas, 0);
        tabelaProdutos = new JTable(modeloTabela);
        JScrollPane scrollPane = new JScrollPane(tabelaProdutos);

        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        btnAtualizar = new JButton("Atualizar");
        btnFechar = new JButton("Fechar");

        painelBotoes.add(btnAtualizar);
        painelBotoes.add(btnFechar);

        add(scrollPane, BorderLayout.CENTER);
        add(painelBotoes, BorderLayout.SOUTH);

        btnAtualizar.addActionListener(e -> carregarProdutos());
        btnFechar.addActionListener(e -> dispose());

        carregarProdutos();
    }

    private void carregarProdutos() {
        modeloTabela.setRowCount(0);
        for (String[] p : ProdutoRepository.listarProdutos()) {
            modeloTabela.addRow(p);
        }
    }
}
