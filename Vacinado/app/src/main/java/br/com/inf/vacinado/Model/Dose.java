package br.com.inf.vacinado.Model;

public class Dose {

    private int dia, mes, ano;
    private String info;
    private int dose;

    public Dose() {

    }

    public Dose(int dia, int mes, int ano, String info, int dose) {
        this.dia = dia;
        this.mes = mes;
        this.ano = ano;
        this.info = info;
        this.dose = dose;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getDose() {
        return dose;
    }

    public void setDose(int dose) {
        this.dose = dose;
    }
}
