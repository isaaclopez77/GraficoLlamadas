package com.example.usuario.graficollamadas.basedatos;

import android.provider.BaseColumns;

public class Contrato{

    private Contrato (){
    }

    public static abstract class TablaLlamadas implements BaseColumns{
        public static final String TABLA = "llamadas";
        public static final String NUMERO = "numero";
        public static final String TIPO = "tipo";
        public static final String DIA = "dia";
    }
}