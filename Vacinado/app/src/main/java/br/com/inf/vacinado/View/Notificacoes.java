package br.com.inf.vacinado.View;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import br.com.inf.vacinado.Controller.NotificacaoAdapter;
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

        //recycle lista de Vacinas
        Notificacao notificacao = new Notificacao("Notificação1", "Texto da notificacao", dia, mes, ano);
        notificacoes.add(notificacao);
        recycleNotificacao = (RecyclerView) findViewById(R.id.recycle_view_carteira);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        recycleNotificacao.setLayoutManager(llm);
        adapter = new NotificacaoAdapter(notificacoes);
        recycleNotificacao.setAdapter(adapter);
        //termina recycle
    }

}
