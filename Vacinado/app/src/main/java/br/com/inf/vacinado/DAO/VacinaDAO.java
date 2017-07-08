package br.com.inf.vacinado.DAO;

import android.support.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import br.com.inf.vacinado.Model.Dose;
import br.com.inf.vacinado.Model.Vacina;

public class VacinaDAO {

    //Variaveis para realizar a autenticacao
    static private FirebaseAuth mFirebaseAuth = FirebaseAuth.getInstance();
    static private FirebaseUser mFirebaseUser = mFirebaseAuth.getCurrentUser();

    static private DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
    static private String mUserId = mFirebaseUser.getUid();
    private DatabaseReference ref = mDatabase.child("users").child(mUserId).child("vacinas");
    private Vacina vacinaG;

    public static void persistirVacina(Vacina vacina) {
        String id = mDatabase.child("users").child(mUserId).child("vacinas").child("id").push().getKey();
        vacina.setId(id);
        mDatabase.child("users").child(mUserId).child("vacinas").child(id).setValue(vacina);
        mDatabase.keepSynced(true);
    }

    public Vacina findVacinaById(FirebaseAuth.AuthStateListener mAuthListener, final String id) {
        mFirebaseAuth.addAuthStateListener(mAuthListener);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot vacSnapshot : dataSnapshot.getChildren()) {
                    Vacina vacina = vacSnapshot.getValue(Vacina.class);
                    if (vacina.getId() == id) {
                        vacinaG = vacina;
                        break;
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        return vacinaG;
    }

    public void persistirDose(Vacina vacina, Dose dose){
        FirebaseAuth.AuthStateListener mAuthListener;
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
            }
        };
        vacina.adicionarDose(dose);
        int dosestomadas = vacina.getDosesTomadas() + 1;
        vacina.setDosesTomadas(dosestomadas);
        mDatabase.child("users").child(mUserId).child("vacinas").child(vacina.getId()).setValue(vacina);
        mDatabase.keepSynced(true);
    }
}
