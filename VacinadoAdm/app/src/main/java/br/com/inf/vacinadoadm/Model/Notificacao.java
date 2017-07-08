package br.com.inf.vacinadoadm.Model;

import java.util.Date;

public class Notificacao {

    private String id;
    private String nome;
    private String texto;
    private Date dataInicial;
    private Date dataFinal;

    public Notificacao() {

    }

    public Notificacao(String nome, String texto, Date dataInicial, Date dataFinal){
        this.nome = nome;
        this.texto = texto;
        this.dataInicial = dataInicial;
        this.dataFinal = dataFinal;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Date getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(Date dataInicial) {
        this.dataInicial = dataInicial;
    }

    public Date getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(Date dataFinal) {
        this.dataFinal = dataFinal;
    }
}
