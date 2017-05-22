package br.com.inf.vacinado.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class BancoOfflineController {


    private SQLiteDatabase db;
    private CriaBancoOffline banco;

    public BancoOfflineController(Context context) {
        banco = new CriaBancoOffline(context);
    }

    public String insereDado(String nome, String email, String cpf) {
        ContentValues valores;
        long resultado;

        db = banco.getWritableDatabase();
        valores = new ContentValues();
        valores.put(CriaBancoOffline.NOME, nome);
        valores.put(CriaBancoOffline.EMAIL, email);
        valores.put(CriaBancoOffline.CPF, cpf);

        resultado = db.insert(CriaBancoOffline.TABELA, null, valores);
        db.close();

        if (resultado == -1) {
            return "Erro ao inserir registro";
        } else {
            return "Registro Inserido com sucesso";
        }
    }

    public Cursor carregaDados() {
        Cursor cursor;
        String[] campos = {banco.getUserInstance(), banco.NOME, banco.EMAIL, banco.EMAIL};
        db = banco.getReadableDatabase();
        cursor = db.query(banco.TABELA, campos, null, null, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }
}
