package com.example.usuario.graficollamadas.POJO;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.usuario.graficollamadas.basedatos.Ayudante;
import com.example.usuario.graficollamadas.basedatos.Contrato;

import java.util.ArrayList;
import java.util.List;

public class GestorLlamada {

    private Ayudante abd;
    private SQLiteDatabase bd;

    public GestorLlamada(Context c){
        abd = new Ayudante(c);
    }
    public void open() {
        bd = abd.getWritableDatabase();
    }
    public void openRead() {
        bd = abd.getReadableDatabase();
    }
    public void close() {
        abd.close();
    }

    public long insert(Llamada p) {
        ContentValues valores = new ContentValues();
        valores.put(Contrato.TablaLlamadas.NUMERO, p.getNumero());
        valores.put(Contrato.TablaLlamadas.TIPO,p.isTipo());
        valores.put(Contrato.TablaLlamadas.DIA,p.getDia());
        long id = bd.insert(Contrato.TablaLlamadas.TABLA, null, valores);
        return id;
    }

    public int delete(Llamada p) {
        return delete(p.getId());
    }

    public int delete(long id){
        String condicion = Contrato.TablaLlamadas._ID + " = ?";
        String[] argumentos = { id + "" };
        int cuenta = bd.delete(
                Contrato.TablaLlamadas.TABLA, condicion, argumentos);
        return cuenta;
    }

    public int update(Llamada p){
        ContentValues valores = new ContentValues();
        valores.put(Contrato.TablaLlamadas.NUMERO, p.getNumero());
        valores.put(Contrato.TablaLlamadas.TIPO,p.isTipo());
        valores.put(Contrato.TablaLlamadas.DIA,p.getDia());
        String condicion = Contrato.TablaLlamadas._ID + " = ?";
        String[] argumentos = { p.getId() + "" };
        int cuenta = bd.update(Contrato.TablaLlamadas.TABLA, valores,
                condicion, argumentos);
        return cuenta;
    }

    public Llamada getRow(Cursor c) {
        Llamada p = new Llamada();
        p.setId(c.getLong(c.getColumnIndex(Contrato.TablaLlamadas._ID)));
        p.setNumero(c.getString(c.getColumnIndex(Contrato.TablaLlamadas.NUMERO)));
        p.setTipo(c.getInt(c.getColumnIndex(Contrato.TablaLlamadas.TIPO))>0);
        p.setDia(c.getInt(c.getColumnIndex(Contrato.TablaLlamadas.DIA)));
        return p;
    }

    public Llamada getRow(long id) {
        Cursor c = getCursor("_id = ?", new String[]{id+""});
        return getRow(c);
    }

    public Cursor getCursor(){
        return getCursor(null, null);
    }

    public Cursor getCursor(String condicion, String[] parametros) {
        Cursor cursor = bd.query(
                Contrato.TablaLlamadas.TABLA, null, condicion, parametros, null,
                null, Contrato.TablaLlamadas.NUMERO+", "+Contrato.TablaLlamadas.DIA+", "+Contrato.TablaLlamadas.TIPO);
        return cursor;
    }

    public List<Llamada> select(String condicion, String[] parametros) {
        List<Llamada> la = new ArrayList<>();
        Cursor cursor = getCursor(condicion, parametros);
        Llamada p;
        while (cursor.moveToNext()) {
            p = getRow(cursor);
            la.add(p);
        }
        cursor.close();
        return la;
    }

    public List<Llamada> select() {
        return select(null,null);
    }

}
