package com.br.domain.entity;

import java.math.BigDecimal;

public class Estoque {

    private BigDecimal quantidade;
    private String localTanque;
    private String localEndereco;
    private String loteFabricacao;
    private String dataValidade;

    public Estoque(){
        this.quantidade = quantidade;
        this.localTanque = localTanque;
        this.localEndereco = localEndereco;
        this.loteFabricacao = loteFabricacao;
        this.dataValidade = dataValidade;
    }

    public BigDecimal getQuantidade() {
        return quantidade;
    }

    public String getLocalTanque() {
        return localTanque;
    }

    public String getlocalEndereco() {
        return localEndereco;
    }

    public String getloteFabricacao() {
        return loteFabricacao;
    }

    public String getDataValidade() {
        return dataValidade;
    }

    public void setQuantidade(BigDecimal quantidade) {
        this.quantidade = quantidade;
    }

    public String getLocalEndereco() {
        return localEndereco;
    }

    public void setLocalEndereco(String localEndereco) {
        this.localEndereco = localEndereco;
    }

    public String setloteFabricacao() {
        return loteFabricacao;
    }

    public String setDataValidade() {
        return dataValidade;
    }

}
