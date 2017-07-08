package br.com.inf.vacinadoadm.DAO;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import br.com.inf.vacinadoadm.Model.Vacina;

public class VacinaDAO {

    //Variaveis para realizar a autenticacao
    static private FirebaseAuth mFirebaseAuth = FirebaseAuth.getInstance();
    static private FirebaseUser mFirebaseUser = mFirebaseAuth.getCurrentUser();

    static private DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
    static private String mUserId = mFirebaseUser.getUid();

    public static void persistirVacina(Vacina vacina) {
        String id = mDatabase.child("vacinas").push().getKey();
        vacina.setId(id);
        mDatabase.child("vacinas").child(id).setValue(vacina);
        mDatabase.keepSynced(true);
    }

}