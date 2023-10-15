package com.example.forcadevendastrab3bi.controller;

import android.content.Context;

import com.example.forcadevendastrab3bi.dao.EnderecoDao;
import com.example.forcadevendastrab3bi.dao.ItemDao;
import com.example.forcadevendastrab3bi.model.Endereco;
import com.example.forcadevendastrab3bi.model.Item;

import java.util.ArrayList;

public class ItemController {
    private Context context;


    public ItemController(Context context) {
        this.context = context;
    }
    /*
    private int codigo;
    private String descricao;
    private double valorUnit;
    private String unidadeMedida;
    */
    public long salvarItem(String codigo, String descricao,  String valorUnit,  String unidadeMedida){
        Item item = new Item(Integer.parseInt(codigo), descricao, Double.parseDouble(valorUnit), unidadeMedida);
        return ItemDao.getInstancia(context).insert(item);
    }

    public long atualizarItem(String codigo, String descricao,  String valorUnit,  String unidadeMedida){
        Item item = new Item(Integer.parseInt(codigo), descricao, Double.parseDouble(valorUnit), unidadeMedida);
        return ItemDao.getInstancia(context).update(item);
    }

    public long apagarItem(String codigo, String descricao,  String valorUnit,  String unidadeMedida){
        Item item = new Item(Integer.parseInt(codigo), descricao, Double.parseDouble(valorUnit), unidadeMedida);
        return ItemDao.getInstancia(context).delete(item);
    }


    public ArrayList<Item> retornarTodositens(){
        return ItemDao.getInstancia(context).getAll();
    }


    public Item retornarItem(int id){
        return ItemDao.getInstancia(context).getById(id);
    }

    public String validaItem(String descricao,  String valorUnit,  String unidadeMedida){
        String mensagem = "";

        if(descricao == null || descricao.isEmpty()){
            mensagem += "A descrição deve ser informada.";
        }
        if(valorUnit == null || valorUnit.isEmpty()){
            mensagem += "O valor unitário deve ser informado.";
        }
        if(unidadeMedida == null || unidadeMedida.isEmpty()){
            mensagem += "A unidade de medida deve ser informada.";
        }
        return mensagem;
    }
}
