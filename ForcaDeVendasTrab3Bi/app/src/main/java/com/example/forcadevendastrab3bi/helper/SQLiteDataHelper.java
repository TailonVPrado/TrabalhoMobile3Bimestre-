package com.example.forcadevendastrab3bi.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SQLiteDataHelper extends SQLiteOpenHelper {

    public SQLiteDataHelper(@Nullable Context context,
                            @Nullable String name,
                            @Nullable SQLiteDatabase.CursorFactory factory,
                            int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE ENDERECO (CODIGO INTEGER, LOGRADOURO VARCHAR(60), NUMERO VARCHAR(10), BAIRRO VARCHAR(60), CIDADE VARCHAR(60), UF VARCHAR(2))");
        sqLiteDatabase.execSQL("CREATE TABLE CLIENTE (CODIGO INTEGER, NOME VARCHAR(60), CPF VARCHAR(11), COD_ENDERECO INTEGER)");
        sqLiteDatabase.execSQL("CREATE TABLE ITEM (CODIGO INTEGER, DESCRICAO VARCHAR(60), UNMEDIDA VARCHAR(30), VALORUNIT INTEGER)");
        sqLiteDatabase.execSQL("CREATE TABLE PEDIDOVENDA (CODIGO INTEGER, COD_ENDERECO INTEGER, COD_ITEM INTEGER, COD_CLIENTE INTEGER, QTDE INTEGER, FORMAPGTO VARCHAR(10), VALORFINAL INTEGER)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

    }


}