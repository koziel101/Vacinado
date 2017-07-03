package br.com.inf.vacinado.Model;

import java.util.ArrayList;
import java.util.Date;
import java.util.StringTokenizer;

public class Dose {

    private Date data;
    private String informacoes;

    public Dose(Date data, String informacoes) {
        this.data = data;
        this.informacoes = informacoes;
    }

    public String getInformacoes() {
        return informacoes;
    }

    public void setInformacoes(String informacoes) {
        this.informacoes = informacoes;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
}
