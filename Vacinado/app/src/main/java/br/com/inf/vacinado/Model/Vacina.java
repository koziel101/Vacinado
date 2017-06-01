package br.com.inf.vacinado.Model;

public class Vacina {

    private String nome;
    private String informacoes;
    private int quantidadeDoses;
    private int dosesTomadas = 0;

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
}
