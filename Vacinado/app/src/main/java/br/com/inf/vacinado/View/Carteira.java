package br.com.inf.vacinado.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import br.com.inf.vacinado.Model.Vacina;
import br.com.inf.vacinado.R;

public class Carteira extends AppCompatActivity {

    static private FirebaseAuth mFirebaseAuth = FirebaseAuth.getInstance();
    static private FirebaseUser mFirebaseUser = mFirebaseAuth.getCurrentUser();
    private String mUserId = mFirebaseUser.getUid();
    private DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
    private DatabaseReference ref = mDatabase.child("users").child(mUserId).child("vacinas");
    private TextView texto;
    private Toast toast;
    private long lastBackPressTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carteira);

        Intent it = getIntent();
        boolean userOffline = it.getBooleanExtra("Modo offline", false);

        if (userOffline) {
            Toast.makeText(getApplicationContext(), R.string.user_offline, Toast.LENGTH_LONG).show();
        }

        final CardView button = (CardView) findViewById(R.id.card_view1);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                vacinaInfo();
            }
        });

        texto = (TextView) findViewById(R.id.vacina_nome);

        final FloatingActionButton addVacina = (FloatingActionButton) findViewById(R.id.button_nova_vacina);
        addVacina.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                addVacina();
            }
        });

        // Configurando a ListView
        final ListView listView = (ListView) findViewById(R.id.listView);
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, android.R.id.text1);
        listView.setAdapter(adapter);

    }

    @Override
    public void onBackPressed() {
        if (this.lastBackPressTime < System.currentTimeMillis() - 4000) {
            toast = Toast.makeText(this, R.string.fechar_app, Toast.LENGTH_LONG);
            toast.show();
            this.lastBackPressTime = System.currentTimeMillis();
        } else {
            if (toast != null) {
                toast.cancel();
            }
            super.onBackPressed();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot vacSnapshot : dataSnapshot.getChildren()) {
                    Vacina vac = vacSnapshot.getValue(Vacina.class);
                    texto.setText(vac.getNome());
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


    private void vacinaInfo() {
        Intent intent = new Intent(Carteira.this, VacinaInfo.class);
        startActivity(intent);
    }

    private void addVacina() {
        Intent intent = new Intent(Carteira.this, AdicionarVacina.class);
        startActivity(intent);
    }
}
