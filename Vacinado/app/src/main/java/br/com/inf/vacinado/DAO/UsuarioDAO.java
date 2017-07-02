package br.com.inf.vacinado.DAO;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import br.com.inf.vacinado.Model.Usuario;

public class UsuarioDAO {

    //Variaveis para realizar a autenticacao
    static private FirebaseAuth mFirebaseAuth;
    static private FirebaseUser mFirebaseUser;

    //Variaveis para persistir dados
    static private DatabaseReference mDatabase;
    static private String mUserId;


    public static void persistirUsuario(Usuario usuario) {

        // Initialize Firebase Auth and Database Reference
        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mUserId = mFirebaseUser.getUid();

        //Realizando a persistencia offline
        DatabaseReference referencia = FirebaseDatabase.getInstance().getReference("cadastro");
        referencia.keepSynced(true);

        String id = mDatabase.child("users").child(mUserId).child("cadastro").child("id").push().getKey();
        usuario.setId(id);
        mDatabase.child("users").child(mUserId).child("vacinas").child(id).setValue(usuario);
        mDatabase.keepSynced(true);
    }
}
