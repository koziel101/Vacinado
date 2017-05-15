package br.com.inf.vacinado;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class VacinaIncompletaInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vacina_incompleta);

        final Button button = (Button) findViewById(R.id.adicionarDose);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                adicionaDose();
            }
        });

        ListView listaDeCursos = (ListView) findViewById(R.id.listaDoses);
    }

    private void adicionaDose(){
        Intent intent = new Intent(VacinaIncompletaInfo.this, Carteira.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
