package br.com.inf.vacinado.View;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import br.com.inf.vacinado.R;

public class AdicionarDose extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_dose);

        final Button concluir = (Button) findViewById(R.id.bttConcluir);
        concluir.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                concluir();
            }
        });

        final Button cancelar = (Button) findViewById(R.id.bttCancelar);
        cancelar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                concluir();
            }
        });
    }

    private void concluir() {
        //Realizar a persistencia dos dados

        super.onBackPressed();
    }
}
