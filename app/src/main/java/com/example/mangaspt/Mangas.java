package com.example.mangaspt;

public class Mangas {

    int Id;
    String titulo, descricao, capitulos;
    byte[] capa;

    public Mangas(int id, String titulo, String descricao, String capitulos, byte[] capa){
        this.Id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.capitulos = capitulos;
        this.capa = capa;
    }

    public Mangas(){

    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCapitulos() {
        return capitulos;
    }

    public void setCapitulos(String capitulos) {
        this.capitulos = capitulos;
    }

    public byte[] getCapa() {
        return capa;
    }

    public void setCapa(byte[] capa) {
        this.capa = capa;
    }
}
