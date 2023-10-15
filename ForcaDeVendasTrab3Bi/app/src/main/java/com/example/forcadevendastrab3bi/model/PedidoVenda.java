package com.example.forcadevendastrab3bi.model;

public class PedidoVenda {
    private int codigo;
    private Endereco endereco;
    private Cliente cliente;
    private Item item;
    private double qtde;
    private String formaPgto;
    private double valorFinal;

    public PedidoVenda() {
    }

    public PedidoVenda(int codigo, Endereco endereco, Cliente cliente, Item item, double qtde, String formaPgto, double valorFinal) {
        this.codigo = codigo;
        this.endereco = endereco;
        this.cliente = cliente;
        this.item = item;
        this.qtde = qtde;
        this.formaPgto = formaPgto;
        this.valorFinal = valorFinal;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public double getQtde() {
        return qtde;
    }

    public void setQtde(double qtde) {
        this.qtde = qtde;
    }

    public String getFormaPgto() {
        return formaPgto;
    }

    public void setFormaPgto(String formaPgto) {
        this.formaPgto = formaPgto;
    }

    public double getValorFinal() {
        return valorFinal;
    }

    public void setValorFinal(double valorFinal) {
        this.valorFinal = valorFinal;
    }
}
