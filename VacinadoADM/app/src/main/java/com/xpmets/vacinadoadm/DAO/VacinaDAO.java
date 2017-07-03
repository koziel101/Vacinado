package com.xpmets.vacinadoadm.DAO;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.xpmets.vacinadoadm.Model.Vacina;

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

}