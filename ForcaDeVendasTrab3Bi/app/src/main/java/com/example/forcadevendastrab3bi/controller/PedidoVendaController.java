package com.example.forcadevendastrab3bi.controller;

import android.content.Context;

import com.example.forcadevendastrab3bi.dao.ClienteDao;
import com.example.forcadevendastrab3bi.dao.PedidoVendaDao;
import com.example.forcadevendastrab3bi.model.Cliente;
import com.example.forcadevendastrab3bi.model.PedidoVenda;

import java.util.ArrayList;

public class PedidoVendaController {
    private Context context;
    private EnderecoController enderecoController;
    private ClienteController clienteController;
    private ItemController itemController;

    public PedidoVendaController(Context context) {
        this.context = context;
        itemController = new ItemController(context);
        clienteController = new ClienteController(context);
        enderecoController = new EnderecoController(context);
    }
    /*
    private int codigo;
    private Endereco endereco;
    private Cliente cliente;
    private Item item;
    private double qtde;
    private String formaPgto;
    private double valorFinal;
  */

    //    public PedidoVenda(int codigo, Endereco endereco, Cliente cliente, Item item, double qtde, String formaPgto, double valorFinal) {
    public long salvarPedidoVenda(String codigo, String codEndereco,  String codCliente,  String codItem, String qtde, String formaPgto, String valorFinal){
        PedidoVenda pedidoVenda = new PedidoVenda(Integer.parseInt(codigo),
                                                  enderecoController.retornarEndereco(Integer.parseInt(codEndereco)),
                                                  clienteController.retornarCliente(Integer.parseInt(codCliente)),
                                                  itemController.retornarItem(Integer.parseInt(codItem)),
                                                  Double.parseDouble(qtde),
                                                  formaPgto,
                                                  Double.parseDouble(valorFinal));

        return PedidoVendaDao.getInstancia(context).insert(pedidoVenda);
    }

    public long atualizarPedidoVenda(String codigo, String codEndereco,  String codCliente,  String codItem, String qtde, String formaPgto, String valorFinal){
        PedidoVenda pedidoVenda = new PedidoVenda(Integer.parseInt(codigo),
                enderecoController.retornarEndereco(Integer.parseInt(codEndereco)),
                clienteController.retornarCliente(Integer.parseInt(codCliente)),
                itemController.retornarItem(Integer.parseInt(codItem)),
                Double.parseDouble(qtde),
                formaPgto,
                Double.parseDouble(valorFinal));

        return PedidoVendaDao.getInstancia(context).update(pedidoVenda);
    }

    public long apagarPedidoVenda(String codigo, String codEndereco,  String codCliente,  String codItem, String qtde, String formaPgto, String valorFinal){
        PedidoVenda pedidoVenda = new PedidoVenda(Integer.parseInt(codigo),
                enderecoController.retornarEndereco(Integer.parseInt(codEndereco)),
                clienteController.retornarCliente(Integer.parseInt(codCliente)),
                itemController.retornarItem(Integer.parseInt(codItem)),
                Double.parseDouble(qtde),
                formaPgto,
                Double.parseDouble(valorFinal));

        return PedidoVendaDao.getInstancia(context).delete(pedidoVenda);
    }


    public ArrayList<PedidoVenda> retornarTodosPedidos(){
        return PedidoVendaDao.getInstancia(context).getAll();
    }


    public PedidoVenda retornarPedidoVenda(int id){
        return PedidoVendaDao.getInstancia(context).getById(id);
    }

}
