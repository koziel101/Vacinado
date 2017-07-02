package br.com.inf.vacinado.View;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import br.com.inf.vacinado.R;

public class EditarCadastro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_cadastro);

        final Button concluir = (Button) findViewById(R.id.bttCancelar);
        concluir.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

            }
        });

        final Button cancelar = (Button) findViewById(R.id.bttnSalvar);
        cancelar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

            }
        });

    }
}
