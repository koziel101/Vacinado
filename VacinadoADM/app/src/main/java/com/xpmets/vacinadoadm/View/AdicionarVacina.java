package com.xpmets.vacinadoadm.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.xpmets.vacinadoadm.DAO.VacinaDAO;
import com.xpmets.vacinadoadm.Model.Vacina;
import com.xpmets.vacinadoadm.R;

public class AdicionarVacina extends AppCompatActivity {

    protected EditText nomeVacinaEdt;
    protected EditText vacinaQntdEdt;
    protected EditText informacoesVacinaEdt;
    private EditText classificacao;
    Vacina vacina;


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

        final Button cancelar = (Button) findViewById(R.id.bttCancelar);
        cancelar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                voltar();
            }
        });
    }

    private void voltar() {
        Intent it;
        it = new Intent(this, Vacinas.class);
        startActivity(it);
    }

    private void concluir() {

        nomeVacinaEdt = (EditText) findViewById(R.id.vacina_nome);
        vacinaQntdEdt = (EditText) findViewById(R.id.vacina_lote);
        informacoesVacinaEdt = (EditText) findViewById(R.id.vacina_ponto_vacinacao);


        if (nomeVacinaEdt.getText().toString().trim().isEmpty()) {
            Snackbar.make(findViewById(android.R.id.content), "Nome da vacina vazio", Snackbar.LENGTH_LONG).show();
        } else if (vacinaQntdEdt.getText().toString().trim().isEmpty() || vacinaQntdEdt.getText().toString().trim().equals("0")) {
            Snackbar.make(findViewById(android.R.id.content), "Quantidade de doses vazia", Snackbar.LENGTH_LONG).show();
        } else if (informacoesVacinaEdt.getText().toString().trim().isEmpty()) {
            Snackbar.make(findViewById(android.R.id.content), "Informações vazias", Snackbar.LENGTH_LONG).show();
        } else if (classificacao.getText().toString().trim().isEmpty()) {
            Snackbar.make(findViewById(android.R.id.content), "Classificação vazia", Snackbar.LENGTH_LONG).show();
        } else {
            vacina = new Vacina(nomeVacinaEdt.getText().toString(),
                    Integer.parseInt(vacinaQntdEdt.getText().toString()), informacoesVacinaEdt.getText().toString(), classificacao.getText().toString());
            VacinaDAO.persistirVacina(vacina);

            Intent intent = new Intent(AdicionarVacina.this, Vacinas.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }
}
