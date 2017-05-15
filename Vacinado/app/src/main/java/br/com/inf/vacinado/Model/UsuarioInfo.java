package br.com.inf.vacinado.Model;

import com.google.firebase.auth.FirebaseAuth;

public class UsuarioInfo {

    private String nome, email, sexo;
    private String cpf;
    private int diaNascimento, mesNascimento, anoNascimento;

    public UsuarioInfo(String nome, String email, String sexo, String cpf,
                       int diaNascimento, int mesNascimento, int anoNascimento) {
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.sexo = sexo;
        this.diaNascimento = diaNascimento;
        this.mesNascimento = mesNascimento;
        this.anoNascimento = anoNascimento;
    }

    public String getNome() {
        return nome;
    }


    public String getEmail() {
        return email;
    }


    public String getSexo() {
        return sexo;
    }


    public String getCpf() {
        return cpf;
    }


    public int getDiaNascimento() {
        return diaNascimento;
    }


    public int getMesNascimento() {
        return mesNascimento;
    }


    public int getAnoNascimento() {
        return anoNascimento;
    }

}
