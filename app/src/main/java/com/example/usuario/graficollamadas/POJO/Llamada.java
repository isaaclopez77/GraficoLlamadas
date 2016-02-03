package com.example.usuario.graficollamadas.POJO;

public class Llamada {

    private long id;
    private String numero;
    private int dia;
    private boolean tipo;

    public Llamada(){
        this(0,null,0,false);
    }

    public Llamada(long id, String num, int d, boolean tipo){
        this.id=id;
        this.numero=num;
        this.dia=d;
        this.tipo=tipo;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public boolean isTipo() {
        return tipo;
    }

    public void setTipo(boolean tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Llamada{" +
                "id=" + id +
                ", numero='" + numero + '\'' +
                ", dia=" + dia +
                ", tipo=" + tipo +
                '}';
    }
}