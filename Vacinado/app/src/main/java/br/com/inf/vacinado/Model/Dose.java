package br.com.inf.vacinado.Model;

import java.util.ArrayList;
import java.util.Date;

public class Dose {

    Vacina vacina;
    private String nome;
    private Date data;
    String pontoVacinacao;
    String lote;

    private ArrayList<Dose> doses = new ArrayList<>();

    public Dose(Vacina vacina, Date data, String pontoVacinacao) {
        this.vacina = vacina;
        this.data = data;
        this.pontoVacinacao = pontoVacinacao;
    }

    public Dose(Vacina vacina, Date data, String pontoVacinacao, String lote) {
        this.vacina = vacina;
        this.data = data;
        this.pontoVacinacao = pontoVacinacao;
        this.lote = lote;
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
