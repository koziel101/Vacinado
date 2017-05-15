package br.com.inf.vacinado;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class VacinaInfo extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vacina_tomada_info);

        final Button button = (Button) findViewById(R.id.vacinaVoltar);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                carteira();
            }
        });

        final Button button2 = (Button) findViewById(R.id.adicionarDose);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                adicionarDose();
            }
        });

        ListView listaDeCursos = (ListView) findViewById(R.id.listaDoses);
    }

    private void carteira(){
        Intent intent = new Intent(VacinaInfo.this, Carteira.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    private void adicionarDose(){
        Intent intent = new Intent(VacinaInfo.this, Carteira.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
