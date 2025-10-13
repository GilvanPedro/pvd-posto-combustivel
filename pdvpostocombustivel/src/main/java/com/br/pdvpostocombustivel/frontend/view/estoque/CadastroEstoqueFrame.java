package com.br.pdvpostocombustivel.frontend.view.estoque;

import com.br.pdvpostocombustivel.frontend.repository.EstoqueRepository;
import javax.swing.*;
import java.awt.*;

public class CadastroEstoqueFrame extends JFrame {

    private JTextField txtProduto, txtQuantidade, txtCustoUnitario;
    private JButton btnSalvar, btnFechar;

    public CadastroEstoqueFrame() {
        setTitle("Cadastro de Estoque");
        setSize(400, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(4, 2, 10, 10));

        add(new JLabel("Produto:"));
        txtProduto = new JTextField();
        add(txtProduto);

        add(new JLabel("Quantidade:"));
        txtQuantidade = new JTextField();
        add(txtQuantidade);

        add(new JLabel("Custo Unitário (R$):"));
        txtCustoUnitario = new JTextField();
        add(txtCustoUnitario);

        btnSalvar = new JButton("Salvar");
        btnFechar = new JButton("Fechar");
        add(btnSalvar);
        add(btnFechar);

        btnSalvar.addActionListener(e -> salvarEstoque());
        btnFechar.addActionListener(e -> dispose());
    }

    private void salvarEstoque() {
        String produto = txtProduto.getText();
        String quantidade = txtQuantidade.getText();
        String custo = txtCustoUnitario.getText();

        if (produto.isEmpty() || quantidade.isEmpty() || custo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos!", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        EstoqueRepository.adicionarEstoque(produto, quantidade, custo);
        JOptionPane.showMessageDialog(this, "Item adicionado ao estoque com sucesso!");
        limparCampos();
    }

    private void limparCampos() {
        txtProduto.setText("");
        txtQuantidade.setText("");
        txtCustoUnitario.setText("");
    }
}
