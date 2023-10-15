package com.example.forcadevendastrab3bi.model;

public class Item {
    private int codigo;
    private String descricao;
    private double valorUnit;
    private String unidadeMedida;

    public Item() {
    }

    public Item(int codigo, String descricao, double valorUnit, String unidadeMedida) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.valorUnit = valorUnit;
        this.unidadeMedida = unidadeMedida;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValorUnit() {
        return valorUnit;
    }

    public void setValorUnit(double valorUnit) {
        this.valorUnit = valorUnit;
    }

    public String getUnidadeMedida() {
        return unidadeMedida;
    }

    public void setUnidadeMedida(String unidadeMedida) {
        this.unidadeMedida = unidadeMedida;
    }
}
