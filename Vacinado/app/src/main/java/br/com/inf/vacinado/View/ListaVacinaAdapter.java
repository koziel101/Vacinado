package br.com.inf.vacinado.View;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import br.com.inf.vacinado.R;

public class ListaVacinaAdapter extends BaseAdapter {
    String resultado[];
    Context contexto;
    private static LayoutInflater inflater = null;

    public ListaVacinaAdapter(Carteira carteira, String[] resultado) {
        this.resultado = resultado;
        this.contexto = carteira;
        inflater = ( LayoutInflater )contexto.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return resultado.length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class HolderLista {
        TextView vacina_nome;
        //TextView vacina_dose;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        HolderLista holder = new HolderLista();
        View view = inflater.inflate(R.layout.item_carteira, null);

        holder.vacina_nome = (TextView) view.findViewById(R.id.vacina_nome);
        //holder.vacina_dose = (TextView) view.findViewById(R.id.vacina_dose);

        holder.vacina_nome.setText(resultado[position]);
        //holder.vacina_dose.setText(resultado[position]);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(contexto, "Teste" + resultado[position], Toast.LENGTH_LONG).show();
            }
        });
        return view;
    }


}

