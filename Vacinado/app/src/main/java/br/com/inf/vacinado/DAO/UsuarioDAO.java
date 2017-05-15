package br.com.inf.vacinado.DAO;

import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import br.com.inf.vacinado.Model.UsuarioInfo;

/**
 * Created by hiago on 15-May-17.
 */

public class UsuarioDAO {


    //Variaveis para realizar a autenticacao
    static private FirebaseAuth mFirebaseAuth;
    static private FirebaseUser mFirebaseUser;

    //Variaveis para persistir dados
    static private DatabaseReference mDatabase;
    static private String mUserId;

    public static boolean persistirUsuario(UsuarioInfo usuarioInfo) {


        try {
            // Initialize Firebase Auth and Database Reference
            mFirebaseAuth = FirebaseAuth.getInstance();
            mFirebaseUser = mFirebaseAuth.getCurrentUser();
            mDatabase = FirebaseDatabase.getInstance().getReference();

            mUserId = mFirebaseUser.getUid();
        } catch (Exception e) {
            mFirebaseAuth = null;
            return false;
        }

//        Log.e("Nome", usuarioInfo.getNome());
//        Log.e("email", usuarioInfo.getEmail());
//        Log.e("cpf", usuarioInfo.getCpf());
//        Log.e("sexo", usuarioInfo.getSexo());
//        Log.e("dia", String.valueOf(usuarioInfo.getDiaNascimento()));
//        Log.e("mes", String.valueOf(usuarioInfo.getMesNascimento()));
//        Log.e("ano", String.valueOf(usuarioInfo.getAnoNascimento()));



        mDatabase.child("users").child(mUserId).child("cadastro").child("nome").push().setValue(usuarioInfo.getNome());
        mDatabase.child("users").child(mUserId).child("cadastro").child("email").push().setValue(usuarioInfo.getEmail());
        mDatabase.child("users").child(mUserId).child("cadastro").child("cpf").push().setValue(usuarioInfo.getCpf());
        mDatabase.child("users").child(mUserId).child("cadastro").child("sexo").push().setValue(usuarioInfo.getSexo());
        mDatabase.child("users").child(mUserId).child("cadastro").child("dia").push().setValue(usuarioInfo.getDiaNascimento());
        mDatabase.child("users").child(mUserId).child("cadastro").child("mes").push().setValue(usuarioInfo.getMesNascimento());
        mDatabase.child("users").child(mUserId).child("cadastro").child("ano").push().setValue(usuarioInfo.getAnoNascimento());


        return true;
    }


}
