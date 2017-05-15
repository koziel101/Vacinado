package br.com.inf.vacinado.Model;

public class Vacina {

    private String nome;
    private String informacoes;
    private int quantidadeDoses;

    public Vacina(String nome, String informacoes, int quantidadeDoses) {
        this.nome = nome;
        this.informacoes = informacoes;
        this.quantidadeDoses = quantidadeDoses;
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
}
