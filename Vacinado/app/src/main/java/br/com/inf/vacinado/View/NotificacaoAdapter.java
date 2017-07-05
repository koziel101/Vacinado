package br.com.inf.vacinado.View;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.inf.vacinado.Model.Notificacao;
import br.com.inf.vacinado.R;

public class NotificacaoAdapter extends RecyclerView.Adapter<NotificacaoAdapter.NotificacaoViewHolder>{

    List<Notificacao> notificacoes;

    public NotificacaoAdapter( List<Notificacao> notificacoes){
        this.notificacoes = new ArrayList<>();
        this.notificacoes = notificacoes;
    }

    public static class NotificacaoViewHolder extends RecyclerView.ViewHolder{

        CardView cv;
        TextView nome_notificacao;

        public NotificacaoViewHolder(View itemView) {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.card_view_notification);
            nome_notificacao = (TextView) itemView.findViewById(R.id.notificacaoNome);
        }
    }

    //inicializa a viewHolder (cardView)
    @Override
    public NotificacaoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_notificacao, parent, false);
        NotificacaoViewHolder ntView = new NotificacaoViewHolder(v);
        return ntView;
    }

    //adiciona valores aos itens da view
    @Override
    public void onBindViewHolder(NotificacaoViewHolder holder, int position) {
        holder.nome_notificacao.setText(notificacoes.get(position).getTitulo());
    }

    @Override
    public int getItemCount() {
        if (notificacoes.isEmpty()) {
            return 0;
        }
        return notificacoes.size();
    }
}
