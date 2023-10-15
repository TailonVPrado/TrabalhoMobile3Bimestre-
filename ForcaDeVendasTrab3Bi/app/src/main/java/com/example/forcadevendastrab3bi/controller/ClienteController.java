package com.example.forcadevendastrab3bi.controller;

import android.content.Context;

import com.example.forcadevendastrab3bi.dao.ClienteDao;
import com.example.forcadevendastrab3bi.dao.EnderecoDao;
import com.example.forcadevendastrab3bi.model.Cliente;
import com.example.forcadevendastrab3bi.model.Endereco;

import java.util.ArrayList;

public class ClienteController {
    private Context context;
    private EnderecoController enderecoController;
    public ClienteController(Context context) {
        this.context = context;
        //enderecoController = new EnderecoController(context);
    }
    /*
    private int codigo;
    private String nome;
    private String cpf;
    private Endereco endereco;
  */
    public long salvarCliente(String codigo, String nome,  String cpf,  String codEndereco){
        Cliente cliente = new Cliente(Integer.parseInt(codigo), nome, cpf, enderecoController.retornarEndereco(Integer.parseInt(codEndereco)));
        return ClienteDao.getInstancia(context).insert(cliente);
    }

    public long atualizarCliente(String codigo, String nome,  String cpf,  String codEndereco){
        Cliente cliente = new Cliente(Integer.parseInt(codigo), nome, cpf, enderecoController.retornarEndereco(Integer.parseInt(codEndereco)));
        return ClienteDao.getInstancia(context).update(cliente);
    }

    public long apagarCliente(String codigo, String nome,  String cpf,  String codEndereco){
        Cliente cliente = new Cliente(Integer.parseInt(codigo), nome, cpf, enderecoController.retornarEndereco(Integer.parseInt(codEndereco)));
        return ClienteDao.getInstancia(context).delete(cliente);
    }


    public ArrayList<Cliente> retornarTodosClientes(){
        return ClienteDao.getInstancia(context).getAll();
    }


    public Cliente retornarCliente(int id){
        return ClienteDao.getInstancia(context).getById(id);
    }



    public String validaClinte(String nome,  String cpf,  String codEndereco){
        String mensagem = "";

        if(nome == null || nome.isEmpty()){
            mensagem += "O nome deve ser informado.";
        }
        if(cpf == null || cpf.isEmpty()){
            mensagem += "O CPF deve ser informado.";
        }
        if(codEndereco == null || codEndereco.isEmpty()){
            mensagem += "O Endereço deve ser informado.";
        }
        return mensagem;
    }

}
