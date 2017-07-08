package br.com.inf.vacinado.Controller;


import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.inf.vacinado.Model.Vacina;
import br.com.inf.vacinado.R;
import br.com.inf.vacinado.View.VacinaInfo;

public class VacinaAdapter extends RecyclerView.Adapter<VacinaAdapter.VacinaViewHolder> {

    List<Vacina> vacinas;
    static private List<Vacina> vacinasG = new ArrayList<>();

    public VacinaAdapter(List<Vacina> vacinas) {
        this.vacinas = new ArrayList<>();
        this.vacinas = vacinas;
        vacinasG = vacinas;
    }

    public static class VacinaViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView nome_vacina;
        TextView dose_vacina;

        VacinaViewHolder(final View itemView) {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.card_view);
            nome_vacina = (TextView) itemView.findViewById(R.id.vacina_nome);
            dose_vacina = (TextView) itemView.findViewById(R.id.vacina_dose);

            //card abre a informação da vacina
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent it;
                    it = new Intent(itemView.getContext(), VacinaInfo.class);
                    it.putExtra("vacina", vacinasG.get(getAdapterPosition()));
//                    Vacina vacina = new Vacina();
                    Log.e("id Vacina: ", "");
//                    it.putExtra("vacina", vacina);
                    itemView.getContext().startActivity(it);
                }
            });
        }
    }

    //inicializa a viewHolder (cardView)
    @Override
    public VacinaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_carteira, parent, false);
        VacinaViewHolder vacinaView = new VacinaViewHolder(v);
        return vacinaView;
    }

    //adiciona valores aos itens da view
    @Override
    public void onBindViewHolder(VacinaViewHolder holder, int position) {
        holder.nome_vacina.setText(vacinas.get(position).getNome());
        holder.dose_vacina.setText(vacinas.get(position).getDosesTomadas() + "/" + vacinas.get(position).getQuantidadeDoses());
    }

    @Override
    public int getItemCount() {

        if (vacinas.isEmpty()) {
            return 0;
        }
        return vacinas.size();
    }
}

