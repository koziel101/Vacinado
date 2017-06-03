package br.com.inf.vacinado.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.com.inf.vacinado.DAO.VacinaDAO;
import br.com.inf.vacinado.Model.Vacina;
import br.com.inf.vacinado.R;

public class AdicionarVacina extends AppCompatActivity {

    protected EditText nomeVacinaEdt;
    protected EditText vacinaLoteEdt;
    protected EditText informacoesVacinaEdt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_vacina);

        final Button concluir = (Button) findViewById(R.id.bttConcluir);
        concluir.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                concluir();
            }
        });

    }

    private void concluir() {

        nomeVacinaEdt = (EditText) findViewById(R.id.vacina_nome);
        vacinaLoteEdt = (EditText) findViewById(R.id.vacina_lote);
        informacoesVacinaEdt = (EditText) findViewById(R.id.vacina_ponto_vacinacao);

        Vacina vacina = new Vacina(nomeVacinaEdt.getText().toString(),
                Integer.parseInt(vacinaLoteEdt.getText().toString()), informacoesVacinaEdt.getText().toString());
        VacinaDAO.persistirVacina(vacina);

        Intent intent = new Intent(AdicionarVacina.this, Carteira.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
