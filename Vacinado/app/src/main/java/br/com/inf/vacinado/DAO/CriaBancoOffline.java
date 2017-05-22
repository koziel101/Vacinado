package br.com.inf.vacinado.DAO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CriaBancoOffline extends SQLiteOpenHelper {

    private static final String NOME_BANCO = "banco.db";
    protected static final String TABELA = "usuarios";
    private static final String ID = "_id";
    protected static final String NOME = "nome";
    protected static final String EMAIL = "email";
    protected static final String CPF = "senha";
    private static final int VERSAO = 1;

    //Variaveis para realizar a autenticacao
    static private FirebaseAuth mFirebaseAuth;
    static private FirebaseUser mFirebaseUser;
    static private DatabaseReference mDatabase;
    static private String mUserId;

    public CriaBancoOffline(Context context) {
        super(context, NOME_BANCO, null, VERSAO);
    }

    public String getUserInstance() {
        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mUserId = mFirebaseUser.getUid();

        return mUserId;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        mUserId = getUserInstance();

        String sql = "CREATE TABLE " + TABELA + " ("
                + ID + " integer primary key autoincrement, "
                + mUserId + " text, "
                + NOME + " text, "
                + EMAIL + " text, "
                + CPF + " text"
                + ")";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABELA);
        onCreate(db);
    }
}
