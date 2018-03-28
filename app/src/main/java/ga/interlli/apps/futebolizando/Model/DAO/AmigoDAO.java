package ga.interlli.apps.futebolizando.Model.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import ga.interlli.apps.futebolizando.Model.Amigo;
import ga.interlli.apps.futebolizando.Util.Data.DataBase;

public class AmigoDAO {

    private SQLiteDatabase conexao;
    private DataBase banco;

    public AmigoDAO(Context context) {
        banco = new DataBase(context);
    }

    public void open(){
        conexao = banco.getWritableDatabase();
    }

    public void close(){
        conexao.close();
    }

    public void gravarAmigo(Amigo amigo){
        ContentValues value = new ContentValues();
        value.put(DataBase.FIELD_AMIGOS_NOME, amigo.getNome());
        conexao.insert(DataBase.TABELA_AMIGOS, null, value);
    }

    public Amigo getById(int id){
        Amigo amigo = null;
        String[] colunas = {DataBase.FIELD_AMIGOS_ID, DataBase.FIELD_AMIGOS_NOME};
        String whereClause = DataBase.FIELD_AMIGOS_ID + " = '"+ id +"'";
        Cursor cursor = conexao.query(DataBase.TABELA_AMIGOS, colunas, whereClause, null, null, null, null, null);

        cursor.moveToFirst();
        if(cursor.moveToNext()){
            amigo = new Amigo();
            amigo.setId(cursor.getInt(cursor.getColumnIndex(DataBase.FIELD_AMIGOS_ID)));
            amigo.setNome(cursor.getString(cursor.getColumnIndex(DataBase.FIELD_AMIGOS_NOME)));
        }

        return amigo;
    }

    public long rowCount(){
        return DatabaseUtils.queryNumEntries(conexao, DataBase.TABELA_AMIGOS);
    }

    public List<Amigo> findAll(){
        List<Amigo> lista = new ArrayList<>();
        String[] colunas = {DataBase.FIELD_AMIGOS_ID, DataBase.FIELD_AMIGOS_NOME};
        Cursor cursor = conexao.query(DataBase.TABELA_AMIGOS, colunas,null,null,null,null,null,null);

        cursor.moveToFirst();
        while(cursor.moveToNext()){
            Amigo amigo = new Amigo();
            amigo.setId(cursor.getInt(cursor.getColumnIndex(DataBase.FIELD_AMIGOS_ID)));
            amigo.setNome(cursor.getString(cursor.getColumnIndex(DataBase.FIELD_AMIGOS_NOME)));
            lista.add(amigo);
        }

        return lista;
    }

}
