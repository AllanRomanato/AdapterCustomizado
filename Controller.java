package br.com.home.adaptercustomizado;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by allanromanato on 6/2/15.
 */
public class Controller {
    private SQLiteDatabase db;
    private DBHelper banco;

    public Controller(Context context){
        banco = new DBHelper(context);
    }

    public String insereDado(String nome, String dono, byte[] foto){
        ContentValues valores;
        long resultado;
        db = banco.getWritableDatabase();

        valores = new ContentValues();
        valores.put(banco.NOME,nome);
        valores.put(banco.DONO,dono);
        valores.put(banco.FOTO,foto);
        resultado = db.insert(banco.TABELA,null,valores);
        db.close();

        if(resultado==-1){
            return "ERRO";
        }else{
            return "Inserido com Sucesso";
        }
    }
    public Cursor carregaDados(){
        Cursor cursor;
        String[] campos =  {banco.ID,banco.NOME,banco.DONO,banco.FOTO};
        db = banco.getReadableDatabase();
        cursor = db.query(banco.TABELA, campos, null, null, null, null, null, null);

        if(cursor!=null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }


}
