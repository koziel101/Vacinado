package br.com.inf.vacinado.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.com.inf.vacinado.DAO.VacinaDAO;
import br.com.inf.vacinado.Model.Vacina;
import br.com.inf.vacinado.R;

public class AdicionarVacina extends AppCompatActivity {

    protected EditText nomeVacinaEdt;
    protected EditText vacinaQntdEdt;
    protected EditText informacoesVacinaEdt;
    Vacina vacina;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_vacina);

        final Button concluir = (Button) findViewById(R.id.bttConcluir_add_vacina);
        concluir.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                concluir();
            }
        });

        final Button cancelar = (Button) findViewById(R.id.bttCancelar_add_vacina);
        cancelar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                voltar();
            }
        });
    }

    private void voltar() {
        Intent it;
        it = new Intent(this, Carteira.class);
        startActivity(it);
    }

    private void concluir() {

        nomeVacinaEdt = (EditText) findViewById(R.id.vacina_nome_add_vacina);
        vacinaQntdEdt = (EditText) findViewById(R.id.vacina_qnt_doses_add_vacina);
        informacoesVacinaEdt = (EditText) findViewById(R.id.vacina_informacoes_add_vacina);

        if (nomeVacinaEdt.getText().toString().trim().isEmpty()) {
            Snackbar.make(findViewById(android.R.id.content), R.string.nome_vacina_vazio, Snackbar.LENGTH_LONG).show();
        } else if (vacinaQntdEdt.getText().toString().trim().isEmpty() || vacinaQntdEdt.getText().toString().trim().equals("0")) {
            Snackbar.make(findViewById(android.R.id.content), R.string.qntd_vacina_vazio, Snackbar.LENGTH_LONG).show();
        } else if (informacoesVacinaEdt.getText().toString().trim().isEmpty()) {
            Snackbar.make(findViewById(android.R.id.content), R.string.informacoes_vacina_vazio, Snackbar.LENGTH_LONG).show();
        } else {
            vacina = new Vacina(nomeVacinaEdt.getText().toString(),
                    Integer.parseInt(vacinaQntdEdt.getText().toString()), informacoesVacinaEdt.getText().toString());
            VacinaDAO.persistirVacina(vacina);

            Intent intent = new Intent(AdicionarVacina.this, Carteira.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }
}
