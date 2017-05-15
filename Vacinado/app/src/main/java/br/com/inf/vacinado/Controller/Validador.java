package br.com.inf.vacinado.Controller;

import android.support.design.widget.Snackbar;
import android.view.View;
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

    public static void mostrarNotificacao(View view, int validador) {

        switch (validador) {
            case 1:
                Snackbar.make(view, R.string.cadastro_nome_erro, Snackbar.LENGTH_LONG).show();
                break;
            case 2:
                Snackbar.make(view, R.string.cadastro_email_erro, Snackbar.LENGTH_LONG).show();
                break;
            case 3:
                Snackbar.make(view, R.string.cadastro_senha_erro, Snackbar.LENGTH_LONG).show();
                break;
            case 4:
                Snackbar.make(view, R.string.cadastro_cpf_erro, Snackbar.LENGTH_LONG).show();
                break;
            case 5:
                Snackbar.make(view, R.string.cadastro_nascimento_erro, Snackbar.LENGTH_LONG).show();
                break;
            default:
                Snackbar.make(view, R.string.cadastro_erro_geral, Snackbar.LENGTH_LONG).show();
                break;
        }
    }
}
