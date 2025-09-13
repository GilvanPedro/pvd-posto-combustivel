package com.br.domain.entity;

import java.util.Date;

public class Custo {
    private Double imposto;
    private Double custoVariavel;
    private Double margemLucro;
    private Date dataProcessamento;

    public Custo(Double imposto, Double custoVariavel, Double margemLucro, Date dataProcessamento) {
        this.imposto = imposto;
        this.dataProcessamento = dataProcessamento;
        this.custoVariavel = custoVariavel;
        this.margemLucro = margemLucro;
    }

    public Double getImposto() {
        return imposto;
    }

    public void setImposto(Double imposto) {
        this.imposto = imposto;
    }

    public Double getCustoVariavel() {
        return custoVariavel;
    }

    public void setCustoVariavel(Double custoVariavel) {
        this.custoVariavel = custoVariavel;
    }

    public Double getMargemLucro() {
        return margemLucro;
    }

    public void setMargemLucro(Double margemLucro) {
        this.margemLucro = margemLucro;
    }

    public Date getDataProcessamento() {
        return dataProcessamento;
    }

    public void setDataProcessamento(Date dataProcessamento) {
        this.dataProcessamento = dataProcessamento;
    }
}
