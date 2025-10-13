package com.br.pdvpostocombustivel.frontend.view.pessoa;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class CadastroPessoaFrame extends JFrame {

    private final JTextField txtNomeCompleto;
    private final JTextField txtCpfCnpj;
    private final JComboBox<String> cbTipoPessoa;
    private final JTextField txtNumeroCtps;
    private final JTextField txtDataNascimento;

    private final JButton btnSalvar;
    private final JButton btnLimpar;
    private final JButton btnCancelar;

    // Simula um pequeno "banco de dados" em memória
    private static final List<String[]> pessoasCadastradas = new ArrayList<>();

    public CadastroPessoaFrame() {
        setTitle("Cadastro de Pessoa");
        setSize(400, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JPanel panelPrincipal = new JPanel(new GridLayout(6, 2, 10, 10));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel lblNome = new JLabel("Nome completo:");
        txtNomeCompleto = new JTextField();

        JLabel lblCpfCnpj = new JLabel("CPF/CNPJ:");
        txtCpfCnpj = new JTextField();

        JLabel lblTipoPessoa = new JLabel("Tipo de pessoa:");
        cbTipoPessoa = new JComboBox<>(new String[]{"CLIENTE", "FRENTISTA", "GERENTE"});

        JLabel lblNumeroCtps = new JLabel("Número CTPS:");
        txtNumeroCtps = new JTextField();

        JLabel lblDataNascimento = new JLabel("Data de nascimento (dd/MM/yyyy):");
        txtDataNascimento = new JTextField();

        panelPrincipal.add(lblNome);
        panelPrincipal.add(txtNomeCompleto);
        panelPrincipal.add(lblCpfCnpj);
        panelPrincipal.add(txtCpfCnpj);
        panelPrincipal.add(lblTipoPessoa);
        panelPrincipal.add(cbTipoPessoa);
        panelPrincipal.add(lblNumeroCtps);
        panelPrincipal.add(txtNumeroCtps);
        panelPrincipal.add(lblDataNascimento);
        panelPrincipal.add(txtDataNascimento);

        JPanel panelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        btnSalvar = new JButton("Salvar");
        btnLimpar = new JButton("Limpar");
        btnCancelar = new JButton("Cancelar");
        panelBotoes.add(btnSalvar);
        panelBotoes.add(btnLimpar);
        panelBotoes.add(btnCancelar);

        btnSalvar.addActionListener(e -> salvarPessoa());
        btnLimpar.addActionListener(e -> limparCampos());
        btnCancelar.addActionListener(e -> dispose());

        add(panelPrincipal, BorderLayout.CENTER);
        add(panelBotoes, BorderLayout.SOUTH);
    }

    private void salvarPessoa() {
        String nome = txtNomeCompleto.getText().trim();
        String cpfCnpj = txtCpfCnpj.getText().trim();
        String tipo = (String) cbTipoPessoa.getSelectedItem();
        String ctps = txtNumeroCtps.getText().trim();
        String data = txtDataNascimento.getText().trim();

        if (nome.isEmpty() || cpfCnpj.isEmpty() || data.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos obrigatórios!", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            LocalDate dataNasc = LocalDate.parse(data, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

            pessoasCadastradas.add(new String[]{
                    nome, cpfCnpj, tipo, ctps, data
            });

            JOptionPane.showMessageDialog(this, "Pessoa cadastrada com sucesso!");
            limparCampos();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar: verifique a data (use dd/MM/yyyy)", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limparCampos() {
        txtNomeCompleto.setText("");
        txtCpfCnpj.setText("");
        cbTipoPessoa.setSelectedIndex(0);
        txtNumeroCtps.setText("");
        txtDataNascimento.setText("");
    }

    // Método público para que a lista possa ser acessada de fora
    public static List<String[]> getPessoasCadastradas() {
        return pessoasCadastradas;
    }
}
