package br.com.inf.vacinado.Model;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class UsuarioInfo {

    private String nome, email, sexo;
    private String cpf;
    private int diaNascimento, mesNascimento, anoNascimento;
    private static FirebaseUser currentFirebaseUser;

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

    public static String getmUserId() {
        currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        return currentFirebaseUser.getUid();
    }

    public static FirebaseUser getFireBaseUser() {
        return currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
    }
}
