package br.com.inf.vacinado.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.afollestad.materialdialogs.MaterialDialog;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import br.com.inf.vacinado.Controller.MascaraCpf;
import br.com.inf.vacinado.Model.Usuario;
import br.com.inf.vacinado.R;

public class EditarCadastro extends AppCompatActivity {

    Usuario usuario;
    EditText cpfEdit;
    static boolean isUpdating;
    private FirebaseUser user;
    private String nome, email, cpf, senhaAntiga, senhaNova;
    EditText oldPassEdit, newPassEdit;
    private MaterialDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_cadastro);

        Intent intent = getIntent();

        usuario = (Usuario) intent.getSerializableExtra("usuario");

        nome = usuario.getNome();
        EditText nomeEdt = (EditText) findViewById(R.id.edit_nome_editar_cadastro);
        nomeEdt.setText(nome);

        email = usuario.getEmail();
        EditText emailEdit = (EditText) findViewById(R.id.edit_email_editar_cadastro);
        emailEdit.setText(email);

        oldPassEdit = (EditText) findViewById(R.id.edit_senha_editar_cadastro_antiga);
        newPassEdit = (EditText) findViewById(R.id.edit_senha_editar_cadastro_nova);

        cpf = usuario.getCpf();
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
                concluir();
            }
        });

        final Button cancelar = (Button) findViewById(R.id.bttnCancelar_editar_cadastro);
        cancelar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                voltar();
            }
        });
    }

    private void concluir() {
        try {
            senhaAntiga = oldPassEdit.getText().toString();
            senhaNova = newPassEdit.getText().toString();
            user = FirebaseAuth.getInstance().getCurrentUser();
            final String email = user.getEmail();
            AuthCredential credential = EmailAuthProvider.getCredential(email, senhaAntiga);
            dialog = new MaterialDialog.Builder(this).content(R.string.atualizando_cadastro).progress(true, 0).show();
            user.reauthenticate(credential).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        dialog.dismiss();
                        user.updatePassword(senhaNova).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Snackbar.make(findViewById(android.R.id.content), R.string.senha_alterada_sucesso, Snackbar.LENGTH_LONG).show();
                                } else {
                                    Snackbar.make(findViewById(android.R.id.content), R.string.erro_alterar_senha, Snackbar.LENGTH_LONG).show();
                                }
                            }
                        });
                    } else {
                        dialog.dismiss();
                        Snackbar.make(findViewById(android.R.id.content), R.string.atualizar_cadastro_senha_erro, Snackbar.LENGTH_LONG).show();
                    }
                }
            });

        } catch (Exception e) {
            Snackbar.make(findViewById(android.R.id.content), R.string.atualizar_cadastro_senha_erro, Snackbar.LENGTH_LONG).show();
        }
    }


    private void voltar() {
        super.onBackPressed();
    }

    public static void setIsUpdating(boolean isUpdating) {
        EditarCadastro.isUpdating = isUpdating;
    }
}
