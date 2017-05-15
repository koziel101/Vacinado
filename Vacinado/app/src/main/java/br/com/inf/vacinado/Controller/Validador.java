package br.com.inf.vacinado.Controller;

import android.widget.EditText;

import br.com.inf.vacinado.Cadastro;
import br.com.inf.vacinado.R;

public class Validador {


    public static int validaDados(EditText nomeEditText, EditText emailEditText, EditText passwordEditText, EditText edit_cpf) {


        String nome = nomeEditText.getText().toString();

        if (nome.isEmpty()) {
            return 1;
        }

        String email = emailEditText.getText().toString().trim();

        if (email.isEmpty()) {
            return 2;
        }

        String password = passwordEditText.getText().toString().trim();

        if (password.length() < 6) {
            return 3;
        }

        if (verificaCpf(edit_cpf) == false) {
            return 4;
        }
        if (Cadastro.getCheckNascimento() == false) {
            return 5;
        }

        return 0;

    }

    private static boolean verificaCpf(EditText edit_cpf) {

        String cpf = edit_cpf.getText().toString();

        if (cpf.isEmpty() || cpf.length() < 14) {
            return false;
        } else {
            return true;
        }
    }


}
