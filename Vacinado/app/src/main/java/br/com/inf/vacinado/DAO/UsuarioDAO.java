package br.com.inf.vacinado.DAO;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import br.com.inf.vacinado.Model.UsuarioInfo;

public class UsuarioDAO {

    //Variaveis para realizar a autenticacao
    static private FirebaseAuth mFirebaseAuth;
    static private FirebaseUser mFirebaseUser;

    //Variaveis para persistir dados
    static private DatabaseReference mDatabase;
    static private String mUserId;



    public static void persistirUsuario(UsuarioInfo usuarioInfo) {

        // Initialize Firebase Auth and Database Reference
        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mUserId = mFirebaseUser.getUid();

        //Realizando a persistencia offline
        DatabaseReference referencia = FirebaseDatabase.getInstance().getReference("cadastro");
        referencia.keepSynced(true);

        mDatabase.child("users").child(mUserId).child("cadastro").child("nome").setValue(usuarioInfo.getNome());
        mDatabase.child("users").child(mUserId).child("cadastro").child("email").setValue(usuarioInfo.getEmail());
        mDatabase.child("users").child(mUserId).child("cadastro").child("cpf").setValue(usuarioInfo.getCpf());
        mDatabase.child("users").child(mUserId).child("cadastro").child("sexo").setValue(usuarioInfo.getSexo());
        mDatabase.child("users").child(mUserId).child("cadastro").child("dia").setValue(usuarioInfo.getDiaNascimento());
        mDatabase.child("users").child(mUserId).child("cadastro").child("mes").setValue(usuarioInfo.getMesNascimento());
        mDatabase.child("users").child(mUserId).child("cadastro").child("ano").setValue(usuarioInfo.getAnoNascimento());
    }
}
