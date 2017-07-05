package br.com.inf.vacinado.Model;

import java.util.Date;

public class Notificacao {

    private String id;
    private String titulo;
    private String texto;
    private int dia, mes, ano;

    public Notificacao() {

    }

    public Notificacao(String titulo, String texto, int dia, int mes, int ano){
        this.titulo = titulo;
        this.texto = texto;
        this.dia = dia;
        this.mes = mes;
        this.ano = ano;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }
}
