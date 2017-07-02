package br.com.inf.vacinado.View;


import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import br.com.inf.vacinado.Model.Vacina;
import br.com.inf.vacinado.R;

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.VacinaViewHolder> {

    List<Vacina> vacinas;

    public RecycleAdapter(List<Vacina> vacinas) {
        this.vacinas = vacinas;
    }

    public static class VacinaViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView nome_vacina;
        TextView dose_vacina;

        VacinaViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.card_view);
            nome_vacina = (TextView)itemView.findViewById(R.id.vacina_nome);
            dose_vacina = (TextView)itemView.findViewById(R.id.vacina_dose);
        }
    }

    @Override
    public VacinaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(VacinaViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return vacinas.size();
    }
}

