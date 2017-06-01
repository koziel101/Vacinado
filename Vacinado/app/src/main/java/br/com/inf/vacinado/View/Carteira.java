package br.com.inf.vacinado.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

import br.com.inf.vacinado.DAO.VacinaDAO;
import br.com.inf.vacinado.Model.UsuarioInfo;
import br.com.inf.vacinado.R;

public class Carteira extends AppCompatActivity {

    //    private DatabaseReferencece mDatabase;
    private String mUserId;

    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;
    private DatabaseReference mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carteira);

        final CardView button = (CardView) findViewById(R.id.card_view1);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                vacinaInfo();
            }
        });

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

//        VacinaDAO.getmDatabase().child("users").child(UsuarioInfo.getmUserId()).child("vacinas").addChildEventListener(new ChildEventListener() {
//            @Override
//            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
//                adapter.add((String) dataSnapshot.child("informacoes").getValue());
//            }
//
//            @Override
//            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
//
//            }
//
//            @Override
//            public void onChildRemoved(DataSnapshot dataSnapshot) {
//                adapter.remove((String) dataSnapshot.child("nome").getValue());
//            }
//
//            @Override
//            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
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
