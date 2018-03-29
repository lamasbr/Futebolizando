package ga.interlli.apps.futebolizando.Util.Data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBase extends SQLiteOpenHelper {

    public static String DBName = "Futebolizando.db";
    public static int DBVersion = 1;

    public DataBase(Context context) {
        super(context, DBName, null, DBVersion);

    }

    @Override
    public synchronized void close() {
        super.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
