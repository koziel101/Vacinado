package com.xpmets.vacinadoadm.DAO;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.xpmets.vacinadoadm.Model.Notificacao;

public class NotificacaoDAO {

    //Variaveis para realizar a autenticacao
    static private FirebaseAuth mFirebaseAuth = FirebaseAuth.getInstance();
    static private FirebaseUser mFirebaseUser = mFirebaseAuth.getCurrentUser();

    static private DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
    static private String mUserId = mFirebaseUser.getUid();

    public static void persistirNotificacao(Notificacao notificacao) {
        String id = mDatabase.child("notificacoes").push().getKey();
        notificacao.setId(id);
        mDatabase.child("notificacoes").child(id).setValue(notificacao);
        mDatabase.keepSynced(true);
    }
}
