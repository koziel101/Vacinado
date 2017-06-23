package br.com.inf.vacinado.Model;

import java.util.Date;

public class Dose {

    private String nome;
    private Date data;
    private String info;

    public Dose (String nome, Date data, String info){
        this.nome = nome;
        this.data = data;
        this.info = info;
    }
}
