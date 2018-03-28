package ga.interlli.apps.futebolizando.Model.DAO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import ga.interlli.apps.futebolizando.Util.Data.DataBase;

public class AmigosDAO {

    private SQLiteDatabase conexao;
    private DataBase banco;

    public AmigosDAO(Context context) {
        banco = new DataBase(context);
    }

    public void open(){
        conexao = banco.getWritableDatabase();
    }

    public void close(){
        conexao.close();
    }

}
