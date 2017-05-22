package br.com.inf.vacinado.View;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
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
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Calendar;

import br.com.inf.vacinado.Controller.MascaraCpf;
import br.com.inf.vacinado.Controller.Validador;
import br.com.inf.vacinado.DAO.BancoOfflineController;
import br.com.inf.vacinado.DAO.UsuarioDAO;
import br.com.inf.vacinado.Model.UsuarioInfo;
import br.com.inf.vacinado.R;

public class Cadastro extends AppCompatActivity implements View.OnClickListener {

    static final int DialogId = 0;
    final Calendar c = Calendar.getInstance();
    int ano = c.get(Calendar.YEAR);
    int mes = c.get(Calendar.MONTH);
    int dia = c.get(Calendar.DAY_OF_MONTH);
    TextView diaTextView, mesTextView, anoTextView;

    protected EditText nomeEditText;
    protected EditText passwordEditText;
    protected EditText emailEditText;
    protected static EditText edit_cpf;
    protected static Boolean checkNascimento = false;
    protected Spinner spinner;

    private FirebaseAuth mFirebaseAuth;
    static boolean isUpdating;

    private MaterialDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        spinner = (Spinner) findViewById(R.id.sexo_cadastro);
        configuraSpinner(spinner);

        showDialog();

        nomeEditText = (EditText) findViewById(R.id.edit_nome_cadastro);

        edit_cpf = (EditText) findViewById(R.id.edt_cpf_cadastro);

        edit_cpf.addTextChangedListener(new TextWatcher() {


            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int after) {

                MascaraCpf.ControlarMascaraCpf(isUpdating, edit_cpf, s, start, before, after);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        Button botaoVoltar = (Button) findViewById(R.id.bttnVoltar);
        botaoVoltar.setOnClickListener(Cadastro.this);
        Button botaoFinalizar = (Button) findViewById(R.id.bttnFinalizar);
        botaoFinalizar.setOnClickListener(Cadastro.this);

        // Iniciando o FirebaseAuth
        try {
            mFirebaseAuth = FirebaseAuth.getInstance();
        } catch (Exception e) {
            mFirebaseAuth = null;
        }

        passwordEditText = (EditText) findViewById(R.id.edit_senha_cadastro);
        emailEditText = (EditText) findViewById(R.id.edit_email_cadastro);

    }

    public static void setIsUpdating(boolean isUpdating) {
        Cadastro.isUpdating = isUpdating;
    }

    public static Boolean getCheckNascimento() {
        return checkNascimento;
    }

    private DatePickerDialog.OnDateSetListener dPickerListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            checkNascimento = true;
            ano = year;
            mes = month + 1;
            dia = dayOfMonth;

            diaTextView = (TextView) findViewById(R.id.txtDia);
            diaTextView.setText("Dia: " + String.valueOf(dia));

            mesTextView = (TextView) findViewById(R.id.txtMes);
            mesTextView.setText("/ Mês: " + String.valueOf(mes));

            anoTextView = (TextView) findViewById(R.id.txtAno);
            anoTextView.setText("/ Ano: " + String.valueOf(ano));
        }
    };

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
        Intent it;
        switch (v.getId()) {
            case R.id.bttnVoltar:
                it = new Intent(this, Login.class);
                startActivity(it);
                break;

            case R.id.bttnFinalizar:

                int validador = Validador.validaDados(nomeEditText, emailEditText, passwordEditText, edit_cpf);

                if (validador == 0) {

                    dialog = new MaterialDialog.Builder(this).content(R.string.realizando_cadastro).progress(true, 0).show();
                    mFirebaseAuth.createUserWithEmailAndPassword(emailEditText.getText().toString().trim(), passwordEditText.getText().toString().trim())
                            .addOnCompleteListener(Cadastro.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {

                                        String cpfString = edit_cpf.getText().toString().toString().replaceAll("[.]", "").replaceAll("[-]", "");

                                        UsuarioInfo usuario = new UsuarioInfo(nomeEditText.getText().toString(),
                                                emailEditText.getText().toString().trim(), spinner.getSelectedItem().toString(), cpfString,
                                                dia, mes, ano);

                                        UsuarioDAO.persistirUsuario(usuario);

                                        //Criando os arquivos do BD Offline
                                        BancoOfflineController crud = new BancoOfflineController(getBaseContext());
                                        String nome = nomeEditText.getText().toString();
                                        String email = emailEditText.getText().toString();
                                        String resultado;
                                        resultado = crud.insereDado(nome, email, cpfString);
                                        Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_LONG).show();
                                        //BD Offline criado

                                        dialog.dismiss();
                                        Intent intent = new Intent(Cadastro.this, Carteira.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                        startActivity(intent);
                                    } else {
                                        dialog.dismiss();
                                        Snackbar.make(findViewById(android.R.id.content), R.string.cadastro_erro_geral, Snackbar.LENGTH_LONG).show();
                                    }
                                }
                            });

                } else {
                    Validador.mostrarNotificacao(findViewById(android.R.id.content), validador);
                }
        }
    }
}
