package com.xpmets.vacinadoadm.View;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.xpmets.vacinadoadm.DAO.NotificacaoDAO;
import com.xpmets.vacinadoadm.Model.Notificacao;
import com.xpmets.vacinadoadm.R;

import java.util.Calendar;

public class AdicionarNotificacao extends AppCompatActivity {

    private MaterialDialog dialog;
    static final int DialogId = 0;
    protected static Boolean checkDose = false;
    final Calendar c = Calendar.getInstance();
    int ano = c.get(Calendar.YEAR);
    int mes = c.get(Calendar.MONTH);
    int dia = c.get(Calendar.DAY_OF_MONTH);
    TextView diaTextView, mesTextView, anoTextView;
    Notificacao notificacaoG;

    private DatePickerDialog.OnDateSetListener dPickerListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            checkDose = true;
            ano = year;
            mes = month + 1;
            dia = dayOfMonth;

            diaTextView = (TextView) findViewById(R.id.txtDiaInicial);
            diaTextView.setText(" " + String.valueOf(dia));

            mesTextView = (TextView) findViewById(R.id.txtMesInicial);
            mesTextView.setText("/" + String.valueOf(mes));

            anoTextView = (TextView) findViewById(R.id.txtAnoInicial);
            anoTextView.setText("/" + String.valueOf(ano));
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_notificacao);

        final Button concluir = (Button) findViewById(R.id.bttConcluir);
        concluir.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                concluir();
            }
        });

        final Button cancelar = (Button) findViewById(R.id.bttCancelar);
        cancelar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                concluir();
            }
        });

        showDialog();
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
        ImageButton btnDataInicial = (ImageButton) findViewById(R.id.bttnDataInicial);

        btnDataInicial.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showDialog(DialogId);
                    }
                }
        );

    }

    private void concluir() {
        NotificacaoDAO notificacao = new NotificacaoDAO();
        notificacao.persistirNotificacao(notificacaoG);

        super.onBackPressed();
    }
}