package br.com.inf.vacinado.Model;

import java.util.ArrayList;
import java.util.Date;
import java.util.StringTokenizer;

public class Dose {

    private String nome;
    private Date data;
    private String informacoes;

    public Dose(String nome, Date data, String informacoes) {
        this.nome = nome;
        this.data = data;
    }

    public String getInformacoes() {
        return informacoes;
    }

    public void setInformacoes(String informacoes) {
        this.informacoes = informacoes;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
}
