package com.br.pdvpostocombustivel.frontend.view.financeiro;

import com.br.pdvpostocombustivel.frontend.repository.FinanceiroRepository;
import javax.swing.*;
import java.awt.*;

public class RegistroReceitaFrame extends JFrame {

    private JTextField txtDescricao, txtValor;
    private JButton btnSalvar, btnFechar;

    public RegistroReceitaFrame() {
        setTitle("Registrar Receita");
        setSize(400, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(3, 2, 10, 10));

        add(new JLabel("Descrição:"));
        txtDescricao = new JTextField();
        add(txtDescricao);

        add(new JLabel("Valor (R$):"));
        txtValor = new JTextField();
        add(txtValor);

        btnSalvar = new JButton("Salvar");
        btnFechar = new JButton("Fechar");
        add(btnSalvar);
        add(btnFechar);

        btnSalvar.addActionListener(e -> salvarReceita());
        btnFechar.addActionListener(e -> dispose());
    }

    private void salvarReceita() {
        String descricao = txtDescricao.getText();
        String valor = txtValor.getText();

        if (descricao.isEmpty() || valor.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos!", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            double valorDouble = Double.parseDouble(valor);
            FinanceiroRepository.adicionarReceita(descricao, valorDouble);
            JOptionPane.showMessageDialog(this, "Receita registrada com sucesso!");
            txtDescricao.setText("");
            txtValor.setText("");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Valor inválido!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
