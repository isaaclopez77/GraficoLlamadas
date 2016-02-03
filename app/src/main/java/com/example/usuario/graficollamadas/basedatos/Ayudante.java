package com.example.usuario.graficollamadas.basedatos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class Ayudante extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "llamadas.sqlite";
    public static final int DATABASE_VERSION = 2;

    public Ayudante(Context context) {
        super(context, context.getExternalFilesDir(null) + "/" + DATABASE_NAME, null,DATABASE_VERSION);
        Log.v("estado","ayudanteConstr");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,
                          int newVersion) {
        String sql="drop table if exists " + Contrato.TablaLlamadas.TABLA;
        db.execSQL(sql);
        onCreate(db);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.v("estado","ok");
        String sql;
        sql="create table "+Contrato.TablaLlamadas.TABLA+
                " ("+
                  Contrato.TablaLlamadas._ID + " integer primary key autoincrement, "+
                  Contrato.TablaLlamadas.NUMERO+" text, "+
                  Contrato.TablaLlamadas.TIPO+" boolean, "+
                  Contrato.TablaLlamadas.DIA+" integer "+
                ")";
        db.execSQL(sql);

    }
}