package br.com.inf.vacinado.DAO;

import android.content.SharedPreferences;
import android.widget.CompoundButton;
import android.widget.EditText;

public class LoginOfflineDAO {

    public static void recuperarLogin(SharedPreferences prefs, EditText emailEditText, EditText passwordEditText, CompoundButton checkBox) {
        if (prefs != null) {
            if (prefs.getBoolean("lembrar", false) == true) {
                emailEditText.setText(prefs.getString("email", ""));
                passwordEditText.setText(prefs.getString("senha", ""));
                checkBox.setChecked(true);
            }
        }
    }

    public static void persistirLogin(SharedPreferences prefs, EditText emailEditText, EditText passwordEditText, CompoundButton checkBox) {
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