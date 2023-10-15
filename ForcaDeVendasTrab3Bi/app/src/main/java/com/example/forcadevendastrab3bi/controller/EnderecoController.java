package com.example.forcadevendastrab3bi.controller;

import android.content.Context;

import com.example.forcadevendastrab3bi.dao.EnderecoDao;
import com.example.forcadevendastrab3bi.model.Endereco;

import java.util.ArrayList;

public class EnderecoController {
    private Context context;

    public EnderecoController(Context context) {
        this.context = context;
    }

    public long salvarEndereco(String codigo, String logradouro,  String numero,  String bairro,  String cidade, String uf){
        Endereco endereco = new Endereco(Integer.parseInt(codigo), logradouro, numero, bairro, cidade, uf);
        return EnderecoDao.getInstancia(context).insert(endereco);
    }

    public long atualizarEndereco(String codigo, String logradouro,  String numero,  String bairro,  String cidade, String uf){
        Endereco endereco = new Endereco(Integer.parseInt(codigo), logradouro, numero, bairro, cidade, uf);
        return EnderecoDao.getInstancia(context).update(endereco);
    }

    public long apagarEndereco(String codigo, String logradouro,  String numero,  String bairro,  String cidade, String uf){
        Endereco endereco = new Endereco(Integer.parseInt(codigo), logradouro, numero, bairro, cidade, uf);
        return EnderecoDao.getInstancia(context).delete(endereco);
    }


    public ArrayList<Endereco> retornarTodosEnderecos(){
        return EnderecoDao.getInstancia(context).getAll();
    }


    public Endereco retornarEndereco(int id){
        return EnderecoDao.getInstancia(context).getById(id);
    }

    public String validaEndereco(String logradouro,  String numero,  String bairro,  String cidade, String uf){
        String mensagem = "";

        if(logradouro == null || logradouro.isEmpty()){
            mensagem += "O logradouro deve ser informado.";
        }
        if(numero == null || numero.isEmpty()){
            mensagem += "O número deve ser informado.";
        }
        if(bairro == null || bairro.isEmpty()){
            mensagem += "O bairro deve ser informado.";
        }
        if(cidade == null || cidade.isEmpty()){
            mensagem += "A cidade deve ser informado.";
        }
        if(uf == null || uf.isEmpty()){
            mensagem += "O estado deve ser informado.";
        }
        return mensagem;
    }
}
