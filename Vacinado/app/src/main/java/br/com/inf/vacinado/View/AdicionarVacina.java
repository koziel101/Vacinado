package br.com.inf.vacinado.View;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import br.com.inf.vacinado.DAO.VacinaDAO;
import br.com.inf.vacinado.Model.Vacina;
import br.com.inf.vacinado.R;

public class AdicionarVacina extends AppCompatActivity {

    protected EditText nomeVacinaEdt;
    protected EditText vacinaQntdEdt;
    protected EditText informacoesVacinaEdt;
    LinearLayout listaVacinas;
    private Context contextoCarteira;
    Carteira carteira;
    LinearLayout listaCard;
    protected int WIDTH_CARD = 50;
    Vacina vacina;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_vacina);

//        contextoCarteira = carteira.contextoAplicacao();

        //referências layout xml para lista de cards de vacina
        listaCard = (LinearLayout) findViewById(R.id.lista_vacinas);

        final Button concluir = (Button) findViewById(R.id.bttConcluir);
        concluir.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                concluir();

                CardView card = new CardView(contextoCarteira);

                //Parâmetros do CardView
                CardView.LayoutParams params = new CardView.LayoutParams(
                        CardView.LayoutParams.WRAP_CONTENT,
                        CardView.LayoutParams.MATCH_PARENT
                );
                params.setMargins(10,10,10,10);
                card.setLayoutParams(params);

                card.setRadius(2);
                card.setMaxCardElevation(2);
                card.setMinimumHeight(50);

                //Cria TextView e adiciona ao Card
                TextView nomeVacina = new TextView(contextoCarteira);
                params.setMargins(20,0,0,0);
                nomeVacina.setLayoutParams(params);
                nomeVacina.setText(vacina.getNome());
                nomeVacina.setGravity(Gravity.CENTER_VERTICAL);

                card.addView(nomeVacina);

                listaCard.addView(card);

                voltarTelaCarteira();
            }
        });
    }

    private void concluir() {

        nomeVacinaEdt = (EditText) findViewById(R.id.vacina_nome);
        vacinaQntdEdt = (EditText) findViewById(R.id.vacina_lote);
        informacoesVacinaEdt = (EditText) findViewById(R.id.vacina_ponto_vacinacao);

        if (nomeVacinaEdt.getText().toString().trim().isEmpty()) {
            Snackbar.make(findViewById(android.R.id.content), R.string.nome_vacina_vazio, Snackbar.LENGTH_LONG).show();
        } else if (vacinaQntdEdt.getText().toString().trim().isEmpty()) {
            Snackbar.make(findViewById(android.R.id.content), R.string.qntd_vacina_vazio, Snackbar.LENGTH_LONG).show();
        } else if (informacoesVacinaEdt.getText().toString().trim().isEmpty()) {
            Snackbar.make(findViewById(android.R.id.content), R.string.informacoes_vacina_vazio, Snackbar.LENGTH_LONG).show();
        } else {
            vacina = new Vacina(nomeVacinaEdt.getText().toString(),
                    Integer.parseInt(vacinaQntdEdt.getText().toString()), informacoesVacinaEdt.getText().toString());
            VacinaDAO.persistirVacina(vacina);
        }
    }

    private void voltarTelaCarteira(){
        Intent intent = new Intent(AdicionarVacina.this, Carteira.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
