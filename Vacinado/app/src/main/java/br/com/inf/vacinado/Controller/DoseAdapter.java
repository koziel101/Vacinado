package br.com.inf.vacinado.Controller;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.inf.vacinado.Model.Dose;
import br.com.inf.vacinado.R;

public class DoseAdapter extends RecyclerView.Adapter<DoseAdapter.DoseViewHolder> {

    List<Dose> doses;

    public DoseAdapter(List<Dose> doses) {
        this.doses = new ArrayList<>();
        this.doses = doses;
    }

    public static class DoseViewHolder extends RecyclerView.ViewHolder {
        TextView num_dose;
        TextView data_dose;
        TextView info_dose;

        DoseViewHolder(final View itemView) {
            super(itemView);
            num_dose = (TextView) itemView.findViewById(R.id.num_dose);
            data_dose = (TextView) itemView.findViewById(R.id.data_dose);
            info_dose = (TextView) itemView.findViewById(R.id.info_dose);
        }
    }

    @Override
    public DoseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_vacina_dose, parent, false);
        DoseViewHolder doseView = new DoseViewHolder(v);
        return doseView;
    }

    @Override
    public void onBindViewHolder(DoseViewHolder holder, int position) {
        holder.num_dose.setText(doses.get(position).getDose());
        holder.info_dose.setText(doses.get(position).getInfo());
        holder.data_dose.setText(doses.get(position).getDia() + "/" + doses.get(position).getMes() + "/" + doses.get(position).getAno());
    }

    @Override
    public int getItemCount() {
        if (doses.isEmpty()) {
            return 0;
        }
        return doses.size();
    }
}
