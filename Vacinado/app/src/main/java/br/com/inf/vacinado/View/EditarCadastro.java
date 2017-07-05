package br.com.inf.vacinado.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.com.inf.vacinado.Controller.MascaraCpf;
import br.com.inf.vacinado.Model.Usuario;
import br.com.inf.vacinado.R;

public class EditarCadastro extends AppCompatActivity {

    Usuario user;
    EditText cpfEdit;
    static boolean isUpdating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_cadastro);

        Intent intent = getIntent();

        user = (Usuario) intent.getSerializableExtra("usuario");

        String nome = user.getNome();
        EditText nomeEdt = (EditText) findViewById(R.id.edit_nome_editar_cadastro);
        nomeEdt.setText(nome);

        String email = user.getEmail();
        EditText emailEdit = (EditText) findViewById(R.id.edit_email_editar_cadastro);
        emailEdit.setText(email);

        String cpf = user.getCpf();
        cpfEdit = (EditText) findViewById(R.id.edit_cpf_editar_cadastro);

        cpfEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int after) {
                MascaraCpf.ControlarMascaraCpf(isUpdating, cpfEdit, s, start, before, after);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        cpfEdit.setText(cpf);

        final Button concluir = (Button) findViewById(R.id.bttnSalvar_editar_cadastro);
        concluir.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            }
        });

        final Button cancelar = (Button) findViewById(R.id.bttnCancelar_editar_cadastro);
        cancelar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                voltar();
            }
        });
    }

    private void voltar() {
        super.onBackPressed();
    }

    public static void setIsUpdating(boolean isUpdating) {
        EditarCadastro.isUpdating = isUpdating;
    }
}
