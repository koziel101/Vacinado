package br.com.inf.vacinado.View;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

import br.com.inf.vacinado.R;

public class Carteira extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carteira);

        final CardView button = (CardView) findViewById(R.id.card_view1);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                vacinaInfo();
            }
        });

        final FloatingActionButton addVacina = (FloatingActionButton) findViewById(R.id.button_nova_vacina);
        addVacina.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                addVacina();
            }
        });
    }

    private void vacinaInfo(){
        Intent intent = new Intent(Carteira.this, VacinaInfo.class);
        startActivity(intent);
    }

    private void addVacina(){
        Intent intent = new Intent(Carteira.this, AdicionarVacina.class);
        startActivity(intent);
    }
}
