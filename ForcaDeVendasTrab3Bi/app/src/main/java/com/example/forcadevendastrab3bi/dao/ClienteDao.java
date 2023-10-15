package com.example.forcadevendastrab3bi.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.forcadevendastrab3bi.controller.EnderecoController;
import com.example.forcadevendastrab3bi.helper.SQLiteDataHelper;
import com.example.forcadevendastrab3bi.model.Cliente;

import java.util.ArrayList;

public class ClienteDao implements GenericDao<Cliente>{
    private SQLiteOpenHelper openHelper;

    private SQLiteDatabase bd;

    //sqLiteDatabase.execSQL("CREATE TABLE CLIENTE (CODIGO INTEGER, NOME VARCHAR(60), CPF VARCHAR(11), COD_ENDERECO INTEGER)");
    private String[]colunas = {"CODIGO", "NOME", "CPF", "COD_ENDERECO"};

    private String tableName = "CLIENTE";

    private Context context;

    private static ClienteDao instancia;
    private static EnderecoController enderecoController;

    public static ClienteDao getInstancia(Context context){
        if(instancia == null)
            return instancia = new ClienteDao(context);
        else
            return instancia;
    }

    private ClienteDao(Context context) {
        this.context = context;
        openHelper = new SQLiteDataHelper(this.context, "UNIPAR", null, 1);
        bd = openHelper.getWritableDatabase();
        enderecoController = new EnderecoController(context);
    }


    @Override
    public long insert(Cliente obj) {
        try{
            //{"CODIGO", "NOME", "CPF", "COD_ENDERECO"};
            ContentValues valores = new ContentValues();
            valores.put("CODIGO", obj.getCodigo());
            valores.put("NOME", obj.getNome());
            valores.put("CPF", obj.getCpf());
            valores.put("COD_ENDERECO", obj.getEndereco().getCodigo());

            return bd.insert(tableName, null, valores);

        }catch (SQLException ex){
            Log.e("ERRO", "ClienteDao.insert(): "+ex.getMessage());
        }
        return -1;
    }

    @Override
    public long update(Cliente obj) {
        try{
            //{"CODIGO", "NOME", "CPF", "COD_ENDERECO"};
            ContentValues valores = new ContentValues();
            valores.put("NOME", obj.getNome());
            valores.put("CPF", obj.getCpf());
            valores.put("COD_ENDERECO", obj.getEndereco().getCodigo());

            String[]identificador = {String.valueOf(obj.getCodigo())};

            return bd.update(tableName, valores, "CODIGO = ?", identificador);

        }catch (SQLException ex){
            Log.e("ERRO", "ClienteDao.update(): "+ex.getMessage());
        }
        return -1;
    }

    @Override
    public long delete(Cliente obj) {
        try{
            String[]identificador = {String.valueOf(obj.getCodigo())};
            return bd.delete(tableName, "CODIGO = ?", identificador);

        }catch (SQLException ex){
            Log.e("ERRO", "ClienteDao.delete(): "+ex.getMessage());
        }
        return -1;
    }

    @Override
    public ArrayList<Cliente> getAll() {
        ArrayList<Cliente> lista = new ArrayList<>();

        try{
            Cursor cursor = bd.query(tableName, colunas,
                    null, null,
                    null, null, "CODIGO");
            if(cursor.moveToFirst()){
                do{
                    //{"CODIGO", "NOME", "CPF", "COD_ENDERECO"};
                    Cliente cliente = new Cliente();
                    cliente.setCodigo(cursor.getInt(0));
                    cliente.setNome(cursor.getString(1));
                    cliente.setCpf(cursor.getString(2));
                    cliente.setEndereco(enderecoController.retornarEndereco(cursor.getInt(3)));

                    lista.add(cliente);
                }while(cursor.moveToNext());
            }
        }catch (SQLException ex){
            Log.e("ERRO", "ClienteDao.getAll(): "+ex.getMessage());
        }

        return lista;
    }

    @Override
    public Cliente getById(int id) {
        try{
            String[]identificador = {String.valueOf(id)};

            Cursor cursor = bd.query(tableName, colunas,
                    "CODIGO = ?", identificador, null,
                    null, null);

            if(cursor.moveToFirst()){
                //{"CODIGO", "NOME", "CPF", "COD_ENDERECO"};
                Cliente cliente = new Cliente();
                cliente.setCodigo(cursor.getInt(0));
                cliente.setNome(cursor.getString(1));
                cliente.setCpf(cursor.getString(2));
                cliente.setEndereco(enderecoController.retornarEndereco(cursor.getInt(3)));


                return cliente;
            }

        }catch (SQLException ex){
            Log.e("ERRO", "ClienteDao.getAll(): "+ex.getMessage());
        }
        return null;
    }
}
