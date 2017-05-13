package com.example.hiago.vacinado;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Calendar;

public class Cadastro extends AppCompatActivity implements View.OnClickListener {

    final Calendar c = Calendar.getInstance();
    int ano = c.get(Calendar.YEAR);
    int mes = c.get(Calendar.MONTH);
    int dia = c.get(Calendar.DAY_OF_MONTH);

    TextView diaTextView, mesTextView, anoTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        Spinner spinner = (Spinner) findViewById(R.id.sexo_cadastro);
        configuraSpinner(spinner);

        showDialog();

        final EditText edit_cpf = (EditText) findViewById(R.id.edt_cpf_cadastro);
        edit_cpf.addTextChangedListener(new TextWatcher() {
            boolean isUpdating;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int after) {

                //Quando o texto é alterado o onTextChange é chamado
                //Essa flag evita a chamada infinita desse método
                if (isUpdating) {
                    isUpdating = false;
                    return;
                }

                //Ao apagar o texto, a máscara é removida, então o posicionamento do cursor precisa
                //saber se o texto atual tinha ou não a máscara
                boolean hasMask = s.toString().indexOf('.') > -1 || s.toString().indexOf('-') > -1;

                //Remove os '.' e o '-' da String
                String str = s.toString().replaceAll("[.]", "").replaceAll("[-]", "");

                //Os parâmetros before e after dizem o tamanho anterior e atual da String digitada.
                //If: After > Before = Usuário está digitando
                //Else: Usuário está apagando

                if (after > before) {
                    //Se tem mais de 9 caracteres (sem máscara) oloca os '.' e o '-'
                    if (str.length() > 9) {
                        str = str.substring(0, 3) + '.' + str.substring(3, 6) + '.' + str.substring(6, 9) + '-' + str.substring(9);

                        //Se tem mais de 6, coloca o '.' e o '-'
                    } else if (str.length() > 6) {
                        str = str.substring(0, 3) + '.' + str.substring(3, 6) + '.' + str.substring(6);

                        //Se tem mais de 3, coloca só o '.'
                    } else if (str.length() > 3) {
                        str = str.substring(0, 3) + '.' + str.substring(3);
                    }

                    //Modifica a flag para evitar chamada infinita
                    isUpdating = true;

                    //Altera o novo texto para o usuário
                    edit_cpf.setText(str);

                    //Seta a posicao do curso
                    edit_cpf.setSelection(edit_cpf.getText().length());

                } else {
                    isUpdating = true;
                    edit_cpf.setText(str);

                    //Se estiver apagando posiciona o cursor no local correto.
                    //Isso irá tratar a deleção dos caracteres da mascara
                    edit_cpf.setSelection(Math.max(0, Math.min(hasMask ? start - before : start, str.length())));

                    //Se tem mais de 9 caracteres (sem máscara) oloca os '.' e o '-'
                    if (str.length() > 9) {
                        str = str.substring(0, 3) + '.' + str.substring(3, 6) + '.' + str.substring(6, 9) + '-' + str.substring(9);

                        //Se tem mais de 6, coloca o '.' e o '-'
                    } else if (str.length() > 6) {
                        str = str.substring(0, 3) + '.' + str.substring(3, 6) + '.' + str.substring(6);

                        //Se tem mais de 3, coloca só o '.'
                    } else if (str.length() > 3) {
                        str = str.substring(0, 3) + '.' + str.substring(3);
                    }

                    //Modifica a flag para evitar chamada infinita
                    isUpdating = true;

                    //Altera o novo texto para o usuário
                    edit_cpf.setText(str);

                    //Seta a posicao do curso
                    edit_cpf.setSelection(edit_cpf.getText().length());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        Button botaoVoltar = (Button) findViewById(R.id.bttnVoltar);
        botaoVoltar.setOnClickListener(Cadastro.this);
        Button botaoFinalizar = (Button) findViewById(R.id.bttnFinalizar);
        botaoFinalizar.setOnClickListener(Cadastro.this);
    }

    private DatePickerDialog.OnDateSetListener dPickerListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            ano = year;
            mes = month;
            dia = dayOfMonth;

            diaTextView = (TextView) findViewById(R.id.txtDia);
            diaTextView.setText("Dia: " + String.valueOf(dia));

            mesTextView = (TextView) findViewById(R.id.txtMes);
            mesTextView.setText("; Mês: " + String.valueOf(mes));

            anoTextView = (TextView) findViewById(R.id.txtAno);
            anoTextView.setText("; Ano: " + String.valueOf(ano));
        }
    };

    static final int DialogId = 0;

    public void showDialog() {
        Button btnData = (Button) findViewById(R.id.bttnDataNascimento);

        btnData.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showDialog(DialogId);
                    }
                }
        );
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        if (id == DialogId) {
            return new DatePickerDialog(this, dPickerListener, ano, mes, dia);
        } else {
            return null;
        }
    }

    public void configuraSpinner(Spinner spinner) {
        String[] sexoStr = new String[]{"Masculino", "Feminino"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, sexoStr);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bttnVoltar:
                Intent it = new Intent(this, MainActivity.class);
                startActivity(it);
                break;
            case R.id.bttnFinalizar:
                break;
        }
    }
}
