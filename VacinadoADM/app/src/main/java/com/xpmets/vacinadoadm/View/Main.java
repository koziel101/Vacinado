package com.xpmets.vacinadoadm.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;

import com.xpmets.vacinadoadm.R;

public class Main extends AppCompatActivity {

    private CardView card1;
    private CardView card2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        card1 = (CardView) findViewById(R.id.card_view);
        card2 = (CardView) findViewById(R.id.card_view2);

        card1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Main.this, ListaVacinas.class);
                startActivity(intent);
            }
        });

        card2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Main.this, ListaVacinas.class);
                startActivity(intent);
            }
        });
    }
}
