package com.br.pdvpostocombustivel.domain.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "pessoas")
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nomeCompleto;

    @Column(nullable = false, unique = true)
    private String cpfCnpj;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoPessoa tipoPessoa;

    @Column
    private Long numeroCtps; // Alterado para Long (para evitar cast e erros)

    @Column(nullable = false)
    private LocalDate dataNascimento;

    @Column(nullable = false)
    private LocalDateTime dataCadastro = LocalDateTime.now();

    public enum TipoPessoa {
        CLIENTE,
        FRENTISTA,
        GERENTE
    }

    // Construtores
    public Pessoa() {}

    public Pessoa(String nomeCompleto, String cpfCnpj, TipoPessoa tipoPessoa, Long numeroCtps, LocalDate dataNascimento) {
        this.nomeCompleto = nomeCompleto;
        this.cpfCnpj = cpfCnpj;
        this.tipoPessoa = tipoPessoa;
        this.numeroCtps = numeroCtps;
        this.dataNascimento = dataNascimento;
    }

    // Getters e Setters
    public Long getId() { return id; }

    public String getNomeCompleto() { return nomeCompleto; }
    public void setNomeCompleto(String nomeCompleto) { this.nomeCompleto = nomeCompleto; }

    public String getCpfCnpj() { return cpfCnpj; }
    public void setCpfCnpj(String cpfCnpj) { this.cpfCnpj = cpfCnpj; }

    public TipoPessoa getTipoPessoa() { return tipoPessoa; }
    public void setTipoPessoa(TipoPessoa tipoPessoa) { this.tipoPessoa = tipoPessoa; }

    public Long getNumeroCtps() { return numeroCtps; }
    public void setNumeroCtps(Long numeroCtps) { this.numeroCtps = numeroCtps; }

    public LocalDate getDataNascimento() { return dataNascimento; }
    public void setDataNascimento(LocalDate dataNascimento) { this.dataNascimento = dataNascimento; }

    public LocalDateTime getDataCadastro() { return dataCadastro; }
    public void setDataCadastro(LocalDateTime dataCadastro) { this.dataCadastro = dataCadastro; }
}
