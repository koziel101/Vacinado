package br.com.inf.vacinado.View;

import android.content.Intent;
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

        final CardView button2 = (CardView) findViewById(R.id.card_view2);
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                vacinaInfo();
            }
        });
    }

    private void vacinaInfo(){
        Intent intent = new Intent(Carteira.this, VacinaInfo.class);
        startActivity(intent);
    }
}
