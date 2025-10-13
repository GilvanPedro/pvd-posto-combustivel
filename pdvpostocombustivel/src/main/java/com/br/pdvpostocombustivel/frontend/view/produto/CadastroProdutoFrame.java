package com.br.pdvpostocombustivel.frontend.view.produto;

import com.br.pdvpostocombustivel.frontend.repository.ProdutoRepository;
import javax.swing.*;
import java.awt.*;

public class CadastroProdutoFrame extends JFrame {

    private JTextField txtCodigo, txtNome, txtCategoria, txtPreco, txtQuantidade;
    private JButton btnSalvar, btnFechar;

    public CadastroProdutoFrame() {
        setTitle("Cadastro de Produto");
        setSize(400, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(6, 2, 10, 10));

        add(new JLabel("Código:"));
        txtCodigo = new JTextField();
        add(txtCodigo);

        add(new JLabel("Nome:"));
        txtNome = new JTextField();
        add(txtNome);

        add(new JLabel("Categoria:"));
        txtCategoria = new JTextField();
        add(txtCategoria);

        add(new JLabel("Preço:"));
        txtPreco = new JTextField();
        add(txtPreco);

        add(new JLabel("Quantidade:"));
        txtQuantidade = new JTextField();
        add(txtQuantidade);

        btnSalvar = new JButton("Salvar");
        btnFechar = new JButton("Fechar");

        add(btnSalvar);
        add(btnFechar);

        // Eventos
        btnSalvar.addActionListener(e -> salvarProduto());
        btnFechar.addActionListener(e -> dispose());
    }

    private void salvarProduto() {
        String codigo = txtCodigo.getText();
        String nome = txtNome.getText();
        String categoria = txtCategoria.getText();
        String preco = txtPreco.getText();
        String quantidade = txtQuantidade.getText();

        if (codigo.isEmpty() || nome.isEmpty() || preco.isEmpty() || quantidade.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos obrigatórios!", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        ProdutoRepository.adicionarProduto(codigo, nome, categoria, preco, quantidade);

        JOptionPane.showMessageDialog(this, "Produto cadastrado com sucesso!");
        limparCampos();
    }

    private void limparCampos() {
        txtCodigo.setText("");
        txtNome.setText("");
        txtCategoria.setText("");
        txtPreco.setText("");
        txtQuantidade.setText("");
    }
}
