package br.com.inf.vacinado.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import br.com.inf.vacinado.R;

public class VacinaInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vacina_info);

        final Button novaDose = (Button) findViewById(R.id.adicionarDose_vacina_info);
        novaDose.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                adicionaDose();
            }
        });

        final Button voltar = (Button) findViewById(R.id.bttnVoltar_vacina_info);
        voltar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                voltarViewCarteira();
            }
        });

        ListView listaDeCursos = (ListView) findViewById(R.id.listaDoses_vacina_info);
    }

    private void adicionaDose() {
        Intent intent = new Intent(VacinaInfo.this, AdicionarDose.class);
        startActivity(intent);
    }

    private void voltarViewCarteira() {
        Intent intent = new Intent(VacinaInfo.this, Carteira.class);
        startActivity(intent);
    }
}
