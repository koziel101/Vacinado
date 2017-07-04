package com.xpmets.vacinadoadm.Controller;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.xpmets.vacinadoadm.Model.Notificacao;
import com.xpmets.vacinadoadm.R;

import java.util.ArrayList;
import java.util.List;

public class NotificacaoAdapter extends RecyclerView.Adapter <NotificacaoAdapter.NotificacaoViewHolder> {

    List<Notificacao> notificacoes;

    public NotificacaoAdapter(List<Notificacao> notificacao) {
        this.notificacoes = new ArrayList<>();
        this.notificacoes = notificacao;

    }

    public static class NotificacaoViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView nome_notificacao;
        TextView texto;


        NotificacaoViewHolder(View itemView) {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.card_view);
            nome_notificacao = (TextView) itemView.findViewById(R.id.vacina_nome);
            texto = (TextView) itemView.findViewById(R.id.vacina_dose);
        }
    }

    //inicializa a viewHolder (cardView)
    @Override
    public NotificacaoAdapter.NotificacaoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_carteira, parent, false);
        NotificacaoAdapter.NotificacaoViewHolder vacinaView = new NotificacaoAdapter.NotificacaoViewHolder(v);
        return vacinaView;
    }

    //adiciona valores aos itens da view
    @Override
    public void onBindViewHolder(NotificacaoAdapter.NotificacaoViewHolder holder, int position) {
        holder.nome_notificacao.setText(notificacoes.get(position).getNome());
        holder.texto.setText(notificacoes.get(position).getTexto());
    }

    @Override
    public int getItemCount() {

        if (notificacoes.isEmpty()) {
            return 0;
        }
        return notificacoes.size();
    }
}