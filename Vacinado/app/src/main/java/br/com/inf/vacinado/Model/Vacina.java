package br.com.inf.vacinado.Model;

public class Vacina {

    private String id;
    private String nome;
    private String informacoes;
    private int quantidadeDoses;
    private int dosesTomadas;

    public Vacina(String nome, String informacoes, int quantidadeDoses) {
        this.nome = nome;
        this.informacoes = informacoes;
        this.quantidadeDoses = quantidadeDoses;
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
