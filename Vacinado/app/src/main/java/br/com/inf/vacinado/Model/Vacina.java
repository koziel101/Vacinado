package br.com.inf.vacinado.Model;

import java.util.ArrayList;
import java.util.Date;

public class Vacina {

    private class Dose {

        private String nome;
        private Date data;
        private String info;
    }

    private String nome;
    private String informacoes;
    private int quantidadeDoses;
    private int dosesTomadas = 0;
    private ArrayList<Dose> doses = new ArrayList<>();

    public Vacina() {

    }

    public Vacina(String nome, int quantidadeDoses, String informacoes) {
        this.nome = nome;
        this.quantidadeDoses = quantidadeDoses;
        this.informacoes = informacoes;
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getInformacoes() {
        return informacoes;
    }

    public void setInformacoes(String informacoes) {
        this.informacoes = informacoes;
    }

    public int getQuantidadeDoses() {
        return quantidadeDoses;
    }

    public void setQuantidadeDoses(int quantidadeDoses) {
        this.quantidadeDoses = quantidadeDoses;
    }

    public int getDosesTomadas() {
        return dosesTomadas;
    }

    public void setDosesTomadas(int dosesTomadas) {
        this.dosesTomadas = dosesTomadas;
    }

    public ArrayList<Dose> getDoses() {
        return this.doses;
    }

    public void adicionarDoses(Dose dose) {
        this.doses.add(dose);
    }

    public void removerDoses(int index) {
        this.doses.remove(index);
    }
}
