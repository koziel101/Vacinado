package br.com.inf.vacinado;

import android.app.Application;
import android.os.Bundle;

import com.google.firebase.database.FirebaseDatabase;

public class Vacinado extends Application {

    protected void onCreate(Bundle savedInstanceState) {
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }
}
