package br.com.inf.vacinado.DAO;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import br.com.inf.vacinado.Model.Vacina;

public class VacinaDAO {

    //Variaveis para realizar a autenticacao
    static private FirebaseAuth mFirebaseAuth;
    static private FirebaseUser mFirebaseUser;

    //Variaveis para persistir dados
    static private DatabaseReference mDatabase;
    static private String mUserId;

    public static void persistirVacina(Vacina vacina) {

        // Initialize Firebase Auth and Database Reference
        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();
        mUserId = mFirebaseUser.getUid();

        mDatabase = FirebaseDatabase.getInstance().getReference();

        String id = mDatabase.child("users").child(mUserId).child("vacinas").child("id").push().getKey();
        vacina.setId(id);
        mDatabase.child("users").child(mUserId).child("vacinas").child(id).setValue(vacina);
        mDatabase.keepSynced(true);
    }
}
