package br.com.inf.vacinado.Model;

import java.util.ArrayList;
import java.util.Date;

public class Dose {

    private String nome;
    private Date data;

    public Dose(String nome, Date data) {
        this.nome = nome;
        this.data = data;
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
