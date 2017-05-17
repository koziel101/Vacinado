package br.com.inf.vacinado.View;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import br.com.inf.vacinado.R;

public class VacinaCompletaInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vacina_tomada_info);

        ListView listaDeCursos = (ListView) findViewById(R.id.listaDoses);
    }
}
