package com.br.pdvpostocombustivel.frontend.view;

import com.br.pdvpostocombustivel.frontend.view.estoque.CadastroEstoqueFrame;
import com.br.pdvpostocombustivel.frontend.view.estoque.ListaEstoqueFrame;
import com.br.pdvpostocombustivel.frontend.view.financeiro.GraficoFinanceiroFrame;
import com.br.pdvpostocombustivel.frontend.view.financeiro.RegistroDespesaFrame;
import com.br.pdvpostocombustivel.frontend.view.financeiro.RegistroReceitaFrame;
import com.br.pdvpostocombustivel.frontend.view.financeiro.RelatorioFinanceiroFrame;
import com.br.pdvpostocombustivel.frontend.view.pessoa.CadastroPessoaFrame;
import com.br.pdvpostocombustivel.frontend.view.pessoa.ListaPessoasFrame;
import com.br.pdvpostocombustivel.frontend.view.produto.CadastroProdutoFrame;
import com.br.pdvpostocombustivel.frontend.view.produto.ListaProdutosFrame;

import javax.swing.*;
import java.awt.*;

public class MenuPrincipal extends JFrame {

    public MenuPrincipal() {
        setTitle("🏪 Sistema de Gestão - Posto de Combustível");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 500);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(0, 2, 10, 10));

        // --- BOTÕES ---
        // Pessoas
        JButton btnPessoa = new JButton("👤 Cadastrar Pessoa");
        JButton btnListarPessoas = new JButton("📋 Ver Pessoas");

        // Produtos
        JButton btnProduto = new JButton("Cadastrar Produto");
        JButton btnListarProdutos = new JButton("Ver Produtos");

        // Estoque
        JButton btnCadastrarEstoque = new JButton("Adicionar ao Estoque");
        JButton btnVerEstoque = new JButton("Ver Estoque");

        // Financeiro
        JButton btnRegistrarReceita = new JButton("Registrar Receita");
        JButton btnRegistrarDespesa = new JButton("Registrar Despesa");
        JButton btnRelatorioFinanceiro = new JButton("Relatório Financeiro");
        JButton btnGraficoFinanceiro = new JButton("Ver Gráficos Financeiros");

        // --- AÇÕES ---
        // Pessoa
        btnPessoa.addActionListener(e -> new CadastroPessoaFrame().setVisible(true));
        btnListarPessoas.addActionListener(e -> new ListaPessoasFrame().setVisible(true));

        // Produto
        btnProduto.addActionListener(e -> new CadastroProdutoFrame().setVisible(true));
        btnListarProdutos.addActionListener(e -> new ListaProdutosFrame().setVisible(true));

        // Estoque
        btnCadastrarEstoque.addActionListener(e -> new CadastroEstoqueFrame().setVisible(true));
        btnVerEstoque.addActionListener(e -> new ListaEstoqueFrame().setVisible(true));

        // Financeiro
        btnRegistrarReceita.addActionListener(e -> new RegistroReceitaFrame().setVisible(true));
        btnRegistrarDespesa.addActionListener(e -> new RegistroDespesaFrame().setVisible(true));
        btnRelatorioFinanceiro.addActionListener(e -> new RelatorioFinanceiroFrame().setVisible(true));
        btnGraficoFinanceiro.addActionListener(e -> new GraficoFinanceiroFrame().setVisible(true));



        // --- ADICIONA BOTÕES NA INTERFACE ---
        add(btnPessoa);
        add(btnListarPessoas);
        add(btnProduto);
        add(btnListarProdutos);
        add(btnCadastrarEstoque);
        add(btnVerEstoque);
        add(btnRegistrarReceita);
        add(btnRegistrarDespesa);
        add(btnRelatorioFinanceiro);
        add(btnGraficoFinanceiro);
    }
}
