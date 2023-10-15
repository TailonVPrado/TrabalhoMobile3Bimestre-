package com.example.forcadevendastrab3bi.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.forcadevendastrab3bi.helper.SQLiteDataHelper;
import com.example.forcadevendastrab3bi.model.Item;

import java.util.ArrayList;

public class ItemDao implements GenericDao<Item>{
    private SQLiteOpenHelper openHelper;

    private SQLiteDatabase bd;

    //        sqLiteDatabase.execSQL("CREATE TABLE ITEM (CODIGO INTEGER, DESCRICAO VARCHAR(60), UNMEDIDA VARCHAR(30), VALORUNIT INTEGER)");
    private String[]colunas = {"CODIGO", "DESCRICAO", "UNMEDIDA", "VALORUNIT"};

    private String tableName = "ITEM";

    private Context context;

    private static ItemDao instancia;

    public static ItemDao getInstancia(Context context){
        if(instancia == null)
            return instancia = new ItemDao(context);
        else
            return instancia;
    }

    private ItemDao(Context context) {
        this.context = context;
        openHelper = new SQLiteDataHelper(this.context, "UNIPAR", null, 1);
        bd = openHelper.getWritableDatabase();

    }


    @Override
    public long insert(Item obj) {
        try{
            // {"CODIGO", "DESCRICAO", "UNMEDIDA", "VALORUNIT"};
            ContentValues valores = new ContentValues();
            valores.put("CODIGO", obj.getCodigo());
            valores.put("DESCRICAO", obj.getDescricao());
            valores.put("UNMEDIDA", obj.getUnidadeMedida());
            valores.put("VALORUNIT", obj.getValorUnit());


            return bd.insert(tableName, null, valores);

        }catch (SQLException ex){
            Log.e("ERRO", "ItemDao.insert(): "+ex.getMessage());
        }
        return -1;
    }

    @Override
    public long update(Item obj) {
        try{
            // {"CODIGO", "DESCRICAO", "UNMEDIDA", "VALORUNIT"};
            ContentValues valores = new ContentValues();
            valores.put("DESCRICAO", obj.getDescricao());
            valores.put("UNMEDIDA", obj.getUnidadeMedida());
            valores.put("VALORUNIT", obj.getValorUnit());

            String[]identificador = {String.valueOf(obj.getCodigo())};

            return bd.update(tableName, valores, "CODIGO = ?", identificador);

        }catch (SQLException ex){
            Log.e("ERRO", "ItemDao.update(): "+ex.getMessage());
        }
        return -1;
    }

    @Override
    public long delete(Item obj) {
        try{
            String[]identificador = {String.valueOf(obj.getCodigo())};
            return bd.delete(tableName, "CODIGO = ?", identificador);

        }catch (SQLException ex){
            Log.e("ERRO", "ItemDao.delete(): "+ex.getMessage());
        }
        return -1;
    }

    @Override
    public ArrayList<Item> getAll() {
        ArrayList<Item> lista = new ArrayList<>();

        try{
            Cursor cursor = bd.query(tableName, colunas,
                    null, null,
                    null, null, "CODIGO");
            if(cursor.moveToFirst()){
                do{
                    // {"CODIGO", "DESCRICAO", "UNMEDIDA", "VALORUNIT"};
                    Item item = new Item();

                    item.setCodigo(cursor.getInt(0));
                    item.setDescricao(cursor.getString(1));
                    item.setUnidadeMedida(cursor.getString(2));
                    item.setValorUnit(cursor.getInt(3));

                    lista.add(item);
                }while(cursor.moveToNext());
            }
        }catch (SQLException ex){
            Log.e("ERRO", "ItemDao.getAll(): "+ex.getMessage());
        }

        return lista;
    }

    @Override
    public Item getById(int id) {
        try{
            String[]identificador = {String.valueOf(id)};

            Cursor cursor = bd.query(tableName, colunas,
                    "CODIGO = ?", identificador, null,
                    null, null);

            if(cursor.moveToFirst()){
                // {"CODIGO", "DESCRICAO", "UNMEDIDA", "VALORUNIT"};
                Item item = new Item();

                item.setCodigo(cursor.getInt(0));
                item.setDescricao(cursor.getString(1));
                item.setUnidadeMedida(cursor.getString(2));
                item.setValorUnit(cursor.getInt(3));

                return item;
            }

        }catch (SQLException ex){
            Log.e("ERRO", "ItemDao.getAll(): "+ex.getMessage());
        }
        return null;
    }
}
