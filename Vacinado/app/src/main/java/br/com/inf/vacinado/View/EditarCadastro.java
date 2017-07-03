package br.com.inf.vacinado.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.com.inf.vacinado.Model.Usuario;
import br.com.inf.vacinado.R;

public class EditarCadastro extends AppCompatActivity {

    Usuario user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_cadastro);

        Intent intent = getIntent();

        user = (Usuario) intent.getSerializableExtra("usuario");
//        String nome = user.getNome();
//        EditText nomeEdt = (EditText) findViewById(R.id.edit_nome_cadastro);
//        nomeEdt.setText(nome);

        final Button concluir = (Button) findViewById(R.id.bttnSalvar);
        concluir.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

            }
        });

        final Button cancelar = (Button) findViewById(R.id.bttnCancelar);
        cancelar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                voltar();
            }
        });
    }

    private void voltar() {
        Intent it;
        it = new Intent(this, Carteira.class);
        startActivity(it);
    }
}
