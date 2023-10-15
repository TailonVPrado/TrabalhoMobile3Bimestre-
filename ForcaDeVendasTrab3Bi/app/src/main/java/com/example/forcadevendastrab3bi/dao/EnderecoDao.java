package com.example.forcadevendastrab3bi.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.forcadevendastrab3bi.helper.SQLiteDataHelper;
import com.example.forcadevendastrab3bi.model.Endereco;

import java.util.ArrayList;

public class EnderecoDao implements GenericDao<Endereco>{
    private SQLiteOpenHelper openHelper;

    private SQLiteDatabase bd;

    //sqLiteDatabase.execSQL("CREATE TABLE ENDERECO (CODIGO INTEGER, LOGRADOURO VARCHAR(60), NUMERO VARCHAR(10), BAIRRO VARCHAR(60), CIDADE VARCHAR(60), UF VARCHAR(2))");
    private String[]colunas = {"CODIGO", "LOGRADOURO", "NUMERO", "BAIRRO", "CIDADE", "UF"};

    private String tableName = "ENDERECO";

    private Context context;

    private static EnderecoDao instancia;

    public static EnderecoDao getInstancia(Context context){
        if(instancia == null)
            return instancia = new EnderecoDao(context);
        else
            return instancia;
    }

    private EnderecoDao(Context context) {
        this.context = context;
        openHelper = new SQLiteDataHelper(this.context, "UNIPAR", null, 1);
        bd = openHelper.getWritableDatabase();

    }


    @Override
    public long insert(Endereco obj) {
        try{
            // {"CODIGO", "LOGRADOURO", "NUMERO", "BAIRRO", "CIDADE", "UF"};
            ContentValues valores = new ContentValues();
            valores.put("CODIGO", obj.getCodigo());
            valores.put("LOGRADOURO", obj.getLogradouro());
            valores.put("NUMERO", obj.getNumero());
            valores.put("BAIRRO", obj.getBairro());
            valores.put("CIDADE", obj.getCidade());
            valores.put("UF", obj.getUf());

            return bd.insert(tableName, null, valores);

        }catch (SQLException ex){
            Log.e("ERRO", "EnderecoDao.insert(): "+ex.getMessage());
        }
        return -1;
    }

    @Override
    public long update(Endereco obj) {
        try{
            // {"CODIGO", "LOGRADOURO", "NUMERO", "BAIRRO", "CIDADE", "UF"};
            ContentValues valores = new ContentValues();
            valores.put("LOGRADOURO", obj.getLogradouro());
            valores.put("NUMERO", obj.getNumero());
            valores.put("BAIRRO", obj.getBairro());
            valores.put("CIDADE", obj.getCidade());
            valores.put("UF", obj.getUf());

            String[]identificador = {String.valueOf(obj.getCodigo())};

            return bd.update(tableName, valores, "CODIGO = ?", identificador);

        }catch (SQLException ex){
            Log.e("ERRO", "EnderecoDao.update(): "+ex.getMessage());
        }
        return -1;
    }

    @Override
    public long delete(Endereco obj) {
        try{
            String[]identificador = {String.valueOf(obj.getCodigo())};
            return bd.delete(tableName, "CODIGO = ?", identificador);

        }catch (SQLException ex){
            Log.e("ERRO", "EnderecoDao.delete(): "+ex.getMessage());
        }
        return -1;
    }

    @Override
    public ArrayList<Endereco> getAll() {
        ArrayList<Endereco> lista = new ArrayList<>();

        try{
            Cursor cursor = bd.query(tableName, colunas,
                    null, null,
                    null, null, "CODIGO");
            if(cursor.moveToFirst()){
                do{
                    // {"CODIGO", "LOGRADOURO", "NUMERO", "BAIRRO", "CIDADE", "UF"};
                    Endereco endereco = new Endereco();
                    endereco.setCodigo(cursor.getInt(0));
                    endereco.setLogradouro(cursor.getString(1));
                    endereco.setNumero(cursor.getString(2));
                    endereco.setBairro(cursor.getString(3));
                    endereco.setCidade(cursor.getString(4));
                    endereco.setUf(cursor.getString(5));

                    lista.add(endereco);
                }while(cursor.moveToNext());
            }
        }catch (SQLException ex){
            Log.e("ERRO", "EnderecoDao.getAll(): "+ex.getMessage());
        }

        return lista;
    }

    @Override
    public Endereco getById(int id) {
        try{
            String[]identificador = {String.valueOf(id)};

            Cursor cursor = bd.query(tableName, colunas,
                    "CODIGO = ?", identificador, null,
                    null, null);

            if(cursor.moveToFirst()){
                // {"CODIGO", "LOGRADOURO", "NUMERO", "BAIRRO", "CIDADE", "UF"};
                Endereco endereco = new Endereco();
                endereco.setCodigo(cursor.getInt(0));
                endereco.setLogradouro(cursor.getString(1));
                endereco.setNumero(cursor.getString(2));
                endereco.setBairro(cursor.getString(3));
                endereco.setCidade(cursor.getString(4));
                endereco.setUf(cursor.getString(5));

                return endereco;
            }

        }catch (SQLException ex){
            Log.e("ERRO", "EnderecoDao.getAll(): "+ex.getMessage());
        }
        return null;
    }
}
