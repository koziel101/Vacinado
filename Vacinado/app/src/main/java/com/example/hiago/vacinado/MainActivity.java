package com.example.hiago.vacinado;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ButtonBarLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button bttnEntrar = (Button) findViewById(R.id.bttnEntrar);
        bttnEntrar.setOnClickListener(this);
        Button bttnCadastrar = (Button) findViewById(R.id.bttnCadastrar);
        bttnCadastrar.setOnClickListener(this);

        final EditText edit_cpf = (EditText) findViewById(R.id.edtCpf);
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
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bttnCadastrar:
                Intent it = new Intent(this, Cadastro.class);
                startActivity(it);
                break;
            case R.id.bttnEntrar:
                break;
        }
    }
}
