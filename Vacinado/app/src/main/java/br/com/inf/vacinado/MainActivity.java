package br.com.inf.vacinado;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.afollestad.materialdialogs.MaterialDialog;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import static br.com.inf.vacinado.R.string.login_error_message;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    protected EditText emailEditText;
    protected EditText passwordEditText;
    private FirebaseAuth mFirebaseAuth;
    MaterialDialog dialog;
    CompoundButton checkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button bttnEntrar = (Button) findViewById(R.id.bttnEntrar);
        bttnEntrar.setOnClickListener(this);
        Button bttnCadastrar = (Button) findViewById(R.id.bttnCadastrar);
        bttnCadastrar.setOnClickListener(this);

        // Iniciando o FirebaseAuth
        try {
            mFirebaseAuth = FirebaseAuth.getInstance();
        } catch (Exception e) {
            mFirebaseAuth = null;
        }

        emailEditText = (EditText) findViewById(R.id.edtEmail);
        passwordEditText = (EditText) findViewById(R.id.edtSenha);

        checkBox = (CompoundButton) findViewById(R.id.checkBoxLembrar);

        SharedPreferences prefs = getSharedPreferences("login", MODE_PRIVATE);
        if (prefs != null) {

            if (prefs.getBoolean("lembrar", false) == true) {
                emailEditText.setText(prefs.getString("email", ""));
                passwordEditText.setText(prefs.getString("senha", ""));
                checkBox.setChecked(true);
            }
        }
    }

    @Override
    public void onClick(View v) {
        Intent it;
        switch (v.getId()) {
            case R.id.bttnCadastrar:
                it = new Intent(this, Cadastro.class);
                startActivity(it);
                break;
            case R.id.bttnEntrar:

                String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                email = email.trim();
                password = password.trim();

                if (email.isEmpty() || password.isEmpty()) {
                    Snackbar.make(findViewById(android.R.id.content), login_error_message, Snackbar.LENGTH_LONG).show();
                } else {
                    dialog = new MaterialDialog.Builder(this).content(R.string.realizando_login).progress(true, 0).show();
                    mFirebaseAuth.signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        persistirLogin();
                                        dialog.dismiss();
                                        Intent intent = new Intent(MainActivity.this, Carteira.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                        startActivity(intent);
                                    } else {
                                        dialog.dismiss();
                                        Snackbar.make(findViewById(android.R.id.content),
                                                R.string.login_error_message_return, Snackbar.LENGTH_LONG).show();
                                    }
                                }
                            });
                }
                break;
        }
    }

    private void persistirLogin() {

        SharedPreferences prefs = getSharedPreferences("login", MODE_PRIVATE);

        SharedPreferences.Editor editor = prefs.edit();

        if (checkBox.isChecked()) {
            editor.putString("email", emailEditText.getText().toString());
            editor.putString("senha", passwordEditText.getText().toString());
            editor.putBoolean("lembrar", true);
        } else {
            editor.putBoolean("lembrar", false);
        }
        editor.apply();
    }
}
