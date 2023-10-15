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
import com.example.forcadevendastrab3bi.model.PedidoVenda;

import java.util.ArrayList;

public class PedidoVendaDao implements GenericDao<PedidoVenda>{

    private SQLiteOpenHelper openHelper;

    private SQLiteDatabase bd;

    //sqLiteDatabase.execSQL("CREATE TABLE PEDIDOVENDA (CODIGO INTEGER, COD_ENDERECO INTEGER, COD_ITEM INTEGER, COD_CLIENTE INTEGER, QTDE INTEGER, FORMAPGTO VARCHAR(10), VALORFINAL INTEGER)");
    private String[]colunas = {"CODIGO", "COD_ENDERECO", "COD_ITEM", "COD_CLIENTE", "QTDE", "FORMAPGTO", "VALORFINAL"};

    private String tableName = "PEDIDOVENDA";

    private Context context;

    private static PedidoVendaDao instancia;

    private static EnderecoDao enderecoDao;
    private static ClienteDao clienteDao;
    private static ItemDao itemDao;

    public static PedidoVendaDao getInstancia(Context context){
        if(instancia == null)
            return instancia = new PedidoVendaDao(context);
        else
            return instancia;
    }

    private PedidoVendaDao(Context context) {
        this.context = context;
        openHelper = new SQLiteDataHelper(this.context, "UNIPAR", null, 1);
        bd = openHelper.getWritableDatabase();

    }


    @Override
    public long insert(PedidoVenda obj) {
        try{
            // {"CODIGO", "COD_ENDERECO", "COD_ITEM", "COD_CLIENTE", "QTDE", "FORMAPGTO", "VALORFINAL"};
            ContentValues valores = new ContentValues();
            valores.put("CODIGO", obj.getCodigo());
            valores.put("COD_ENDERECO", obj.getEndereco().getCodigo());
            valores.put("COD_ITEM", obj.getItem().getCodigo());
            valores.put("COD_CLIENTE", obj.getCliente().getCodigo());
            valores.put("QTDE", obj.getQtde());
            valores.put("FORMAPGTO", obj.getFormaPgto());
            valores.put("VALORFINAL", obj.getValorFinal());

            return bd.insert(tableName, null, valores);

        }catch (SQLException ex){
            Log.e("ERRO", "PedidoVendaDao.insert(): "+ex.getMessage());
        }
        return -1;
    }

    @Override
    public long update(PedidoVenda obj) {
        try{
            // {"CODIGO", "COD_ENDERECO", "COD_ITEM", "COD_CLIENTE", "QTDE", "FORMAPGTO", "VALORFINAL"};
            ContentValues valores = new ContentValues();
            valores.put("COD_ENDERECO", obj.getEndereco().getCodigo());
            valores.put("COD_ITEM", obj.getItem().getCodigo());
            valores.put("COD_CLIENTE", obj.getCliente().getCodigo());
            valores.put("QTDE", obj.getQtde());
            valores.put("FORMAPGTO", obj.getQtde());
            valores.put("VALORFINAL", obj.getValorFinal());

            String[]identificador = {String.valueOf(obj.getCodigo())};

            return bd.update(tableName, valores, "CODIGO = ?", identificador);

        }catch (SQLException ex){
            Log.e("ERRO", "PedidoVendaDao.update(): "+ex.getMessage());
        }
        return -1;
    }

    @Override
    public long delete(PedidoVenda obj) {
        try{
            String[]identificador = {String.valueOf(obj.getCodigo())};
            return bd.delete(tableName, "CODIGO = ?", identificador);

        }catch (SQLException ex){
            Log.e("ERRO", "PedidoVenda.delete(): "+ex.getMessage());
        }
        return -1;
    }

    @Override
    public ArrayList<PedidoVenda> getAll() {
        ArrayList<PedidoVenda> lista = new ArrayList<>();

        try{
            Cursor cursor = bd.query(tableName, colunas,
                    null, null,
                    null, null, "CODIGO");
            if(cursor.moveToFirst()){
                do{
                    // {"CODIGO", "COD_ENDERECO", "COD_ITEM", "COD_CLIENTE", "QTDE", "FORMAPGTO", "VALORFINAL"}; -6
                    PedidoVenda pedidoVenda = new PedidoVenda();

                    pedidoVenda.setCodigo(cursor.getInt(0));
                    pedidoVenda.setEndereco(enderecoDao.getById(cursor.getInt(1)));
                    pedidoVenda.setItem(itemDao.getById(cursor.getInt(2)));
                    pedidoVenda.setCliente(clienteDao.getById(cursor.getInt(3)));
                    pedidoVenda.setQtde(cursor.getInt(4));
                    pedidoVenda.setFormaPgto(cursor.getString(5));
                    pedidoVenda.setValorFinal(cursor.getInt(6));

                    lista.add(pedidoVenda);
                }while(cursor.moveToNext());
            }
        }catch (SQLException ex){
            Log.e("ERRO", "PedidoVendaoDao.getAll(): "+ex.getMessage());
        }

        return lista;
    }

    @Override
    public PedidoVenda getById(int id) {
        try{
            String[]identificador = {String.valueOf(id)};

            Cursor cursor = bd.query(tableName, colunas,
                    "CODIGO = ?", identificador, null,
                    null, null);

            if(cursor.moveToFirst()){
                // {"CODIGO", "COD_ENDERECO", "COD_ITEM", "COD_CLIENTE", "QTDE", "FORMAPGTO", "VALORFINAL"}; -6
                PedidoVenda pedidoVenda = new PedidoVenda();

                pedidoVenda.setCodigo(cursor.getInt(0));
                pedidoVenda.setEndereco(enderecoDao.getById(cursor.getInt(1)));
                pedidoVenda.setItem(itemDao.getById(cursor.getInt(2)));
                pedidoVenda.setCliente(clienteDao.getById(cursor.getInt(3)));
                pedidoVenda.setQtde(cursor.getInt(4));
                pedidoVenda.setFormaPgto(cursor.getString(5));
                pedidoVenda.setValorFinal(cursor.getInt(6));

                return pedidoVenda;
            }

        }catch (SQLException ex){
            Log.e("ERRO", "PedidoVendaDao.getAll(): "+ex.getMessage());
        }
        return null;
    }
}
