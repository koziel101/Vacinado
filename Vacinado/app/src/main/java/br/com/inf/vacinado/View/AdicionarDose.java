package br.com.inf.vacinado.View;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;

import java.util.Calendar;

import br.com.inf.vacinado.Model.Dose;
import br.com.inf.vacinado.Model.Vacina;
import br.com.inf.vacinado.R;

public class AdicionarDose extends AppCompatActivity {

    private MaterialDialog dialog;
    static final int DialogId = 0;
    protected static Boolean checkDose = false;
    final Calendar c = Calendar.getInstance();
    int ano = c.get(Calendar.YEAR);
    int mes = c.get(Calendar.MONTH);
    int dia = c.get(Calendar.DAY_OF_MONTH);
    TextView diaTextView, mesTextView, anoTextView;
    protected Spinner spinner;
    private Vacina vacina;
    private EditText informa;

    private DatePickerDialog.OnDateSetListener dPickerListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            checkDose = true;
            ano = year;
            mes = month + 1;
            dia = dayOfMonth;

            diaTextView = (TextView) findViewById(R.id.txtDia_add_dose);
            diaTextView.setText(" " + String.valueOf(dia));

            mesTextView = (TextView) findViewById(R.id.txtMes_add_dose);
            mesTextView.setText("/" + String.valueOf(mes));

            anoTextView = (TextView) findViewById(R.id.txtAno_add_dose);
            anoTextView.setText("/" + String.valueOf(ano));
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_dose);

        final Button concluir = (Button) findViewById(R.id.bttConcluir_add_dose);
        concluir.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                concluir();
            }
        });

        final Button cancelar = (Button) findViewById(R.id.bttCancelar_add_dose);
        cancelar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                cancelar();
            }
        });

        spinner = (Spinner) findViewById(R.id.vacina_dose_add_dose);
        configuraSpinner(spinner);

        informa = (EditText) findViewById(R.id.dose_info);

        showDialog();
    }

    public void configuraSpinner(Spinner spinner) {
        String[] doseStr = new String[]{"1 dose", "2 doses", "3 doses", "4 doses", "5 doses"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, doseStr);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        if (id == DialogId) {
            return new DatePickerDialog(this, dPickerListener, ano, mes, dia);
        } else {
            return null;
        }
    }

    public void showDialog() {
        ImageButton btnData = (ImageButton) findViewById(R.id.bttnDataVacinacao_add_dose);

        btnData.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showDialog(DialogId);
                    }
                }
        );
    }

    private void cancelar() {
        super.onBackPressed();
    }

    private void concluir() {
        //Realizar a persistencia dos dados
        Dose dose = new Dose(dia, mes, ano, informa.getText().toString(), spinner.getSelectedItemPosition());
        vacina.adicionarDose(dose);
        int dosestomadas = vacina.getDosesTomadas() + 1;
        vacina.setDosesTomadas(dosestomadas);
        super.onBackPressed();
    }
}
