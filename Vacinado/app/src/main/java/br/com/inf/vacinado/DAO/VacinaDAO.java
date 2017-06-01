package br.com.inf.vacinado.DAO;

import android.util.Log;

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
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mUserId = mFirebaseUser.getUid();

        //Realizando a persistencia offline
        DatabaseReference referencia = FirebaseDatabase.getInstance().getReference("vacinas");
        referencia.keepSynced(true);

        mDatabase.child("users").child(mUserId).child("vacinas").child("id").push().setValue(vacina.getId());
        mDatabase.child("users").child(mUserId).child("vacinas").child("nome").push().setValue(vacina.getNome());
        mDatabase.child("users").child(mUserId).child("vacinas").child("informacoes").push().setValue(vacina.getInformacoes());
        mDatabase.child("users").child(mUserId).child("vacinas").child("quantidadeDoses").push().setValue(vacina.getQuantidadeDoses());
        mDatabase.child("users").child(mUserId).child("vacinas").child("dosesTomadas").push().setValue(vacina.getDosesTomadas());
    }

    public static DatabaseReference getmDatabase() {
        mDatabase = FirebaseDatabase.getInstance().getReference();
        return mDatabase;
    }

    public static String getmUserId() {
        Log.e("Id do usuario: ", mUserId);
        return mUserId;
    }
}
