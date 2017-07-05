package br.com.inf.vacinado.View;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.inf.vacinado.Model.Notificacao;
import br.com.inf.vacinado.R;

public class Notificacoes extends AppCompatActivity {

    private List<Notificacao> notificacoes = new ArrayList<>();
    RecyclerView recycleNotificacao;
    NotificacaoAdapter adapter;
    final Calendar c = Calendar.getInstance();
    int ano = c.get(Calendar.YEAR);
    int mes = c.get(Calendar.MONTH);
    int dia = c.get(Calendar.DAY_OF_MONTH);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notificacao);

        //recycle lista de Notificações
        Notificacao notificacao = new Notificacao("Notificação1", "Texto da notificacao", dia, mes, ano);
        notificacoes.add(notificacao);

        setRecycleNotificacao(notificacoes);
        //termina recycle
    }

    //configura RecycleView
    private void setRecycleNotificacao(List listaNotificacoes) {
        recycleNotificacao = (RecyclerView) findViewById(R.id.recycle_view_notification);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        recycleNotificacao.setLayoutManager(llm);
        adapter = new NotificacaoAdapter(listaNotificacoes);
        recycleNotificacao.setAdapter(adapter);
    }

}
