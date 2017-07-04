package com.xpmets.vacinadoadm.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.xpmets.vacinadoadm.Controller.VacinaAdapter;
import com.xpmets.vacinadoadm.Model.Vacina;
import com.xpmets.vacinadoadm.R;
import java.util.ArrayList;
import java.util.List;

public class ListaVacinas extends AppCompatActivity {

    static private FirebaseAuth mFirebaseAuth = FirebaseAuth.getInstance();
    private DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
    private DatabaseReference ref = mDatabase.child("vacinas");
    private TextView texto;
    List<Vacina> vacinasG;
    static private FirebaseAuth.AuthStateListener mAuthListener;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_vacinas);

        fab = (FloatingActionButton) findViewById(R.id.btn_add_vacina);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addVacina();
            }
        });

        //instacia recycleView e define o prosicionamento dos intens
        RecyclerView recycle = (RecyclerView) findViewById(R.id.recycle_view);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        recycle.setLayoutManager(llm);

        this.vacinasG = new ArrayList<>();
        Vacina vacina01 = new Vacina("vacina", 2, "lixo", "Adulto");
        Vacina vacina02 = new Vacina("vacina2", 2, "mt bom", "Idoso");
        vacinasG.add(vacina01);
        vacinasG.add(vacina02);

        VacinaAdapter adapter = new VacinaAdapter(vacinasG);
        recycle.setAdapter(adapter);

        mFirebaseAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
            }
        };

        texto = (TextView) findViewById(R.id.vacina_nome);
    }

    private void addVacina() {
        Intent intent = new Intent(ListaVacinas.this, AdicionarVacina.class);
        startActivity(intent);
    }


    @Override
    protected void onStart() {
        super.onStart();
        mFirebaseAuth.addAuthStateListener(mAuthListener);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot vacSnapshot : dataSnapshot.getChildren()) {
                    Vacina vac = vacSnapshot.getValue(Vacina.class);
                    vacinasG.add(vac);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
}