package ga.interlli.apps.futebolizando.Util.Data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBase extends SQLiteOpenHelper {

    public static String DBName = "Futebolizando.db";
    public static int DBVersion = 1;

    // Tabela de amigos
    public static final String TABELA_AMIGOS = "AMIGOS";
    public static final String FIELD_AMIGOS_ID = "id";
    public static final String FIELD_AMIGOS_NOME = "nome";

    // Tabela de times
    public static final String TABELA_TIMES = "TIMES";
    public static final String FIELD_TIMES_ID = "id";
    public static final String FIELD_TIMES_IDAMIGO = "idAmigo";
    public static final String FIELD_TIMES_NOMETIME = "nomeTime";

    // Tabela de jogadores
    public static final String TABELA_JOGADORES = "JOGADORES";
    public static final String FIELD_JOGADORES_ID = "id";
    public static final String FIELD_JOGADORES_IDTIME = "idTime";
    public static final String FIELD_JOGADORES_NOMEJOGADOR = "nomeJogador";

    // Tabela de partidas
    public static final String TABELA_PARTIDAS = "PARTIDAS";
    public static final String FIELD_PARTIDAS_ID = "id";
    public static final String FIELD_PARTIDAS_IDTIMEA = "idTimeA";
    public static final String FIELD_PARTIDAS_IDTIMEB = "idTimeB";
    public static final String FIELD_PARTIDAS_GOLSTIMEA = "golsTimeA";
    public static final String FIELD_PARTIDAS_GOLSTIMEB = "golsTimeB";

    // Tabela de gols por jogador
    public static final String TABELA_GOLS = "GOLS";
    public static final String FIELD_GOLS_IDPARTIDA = "idPartida";
    public static final String FIELD_GOLS_IDJOGADOR = "idJogador";
    public static final String FIELD_GOLS_QTDGOLS = "qtdGols";

    public DataBase(Context context) {
        super(context, DBName, null, DBVersion);


    }

    String createAmigos = "CREATE TABLE "+ TABELA_AMIGOS +" ("
            + FIELD_AMIGOS_ID +" integer primary key autoincrement, "
            + FIELD_AMIGOS_NOME + " text not null);";

    String createTimes = "CREATE TABLE "+ TABELA_TIMES +" ("
            + FIELD_TIMES_ID +" integer primary key autoincrement, "
            + FIELD_TIMES_IDAMIGO + " integer not null, "
            + FIELD_TIMES_NOMETIME + " text not null, " +
            "FOREIGN KEY ("+ FIELD_TIMES_IDAMIGO +") REFERENCES "+ TABELA_AMIGOS +" ("+ FIELD_AMIGOS_ID +")" +
            "ON DELETE CASCADE ON UPDATE NO ACTION" +
            ")";

    String createJogadores = "CREATE TABLE "+ TABELA_JOGADORES +" (" +
            FIELD_JOGADORES_ID +" integer primary key autoincrement, " +
            FIELD_JOGADORES_IDTIME +" integer not null, " +
            FIELD_JOGADORES_NOMEJOGADOR +" text not null, " +
            "FOREIGN KEY ("+ FIELD_JOGADORES_IDTIME +") REFERENCES "+ TABELA_TIMES +" ("+ FIELD_TIMES_ID +") " +
            "ON DELETE CASCADE ON UPDATE NO ACTION" +
            ")";

    String createPartidas = "CREATE TABLE "+ TABELA_PARTIDAS +" (" +
            FIELD_PARTIDAS_ID +" integer primary key autoincrement, " +
            FIELD_PARTIDAS_IDTIMEA +" integer not null, " +
            FIELD_PARTIDAS_IDTIMEB +" integer not null, " +
            FIELD_PARTIDAS_GOLSTIMEA +" integer not null, " +
            FIELD_PARTIDAS_GOLSTIMEB +" integer not null, " +
            "FOREIGN KEY ("+ FIELD_PARTIDAS_IDTIMEA +") REFERENCES "+ TABELA_TIMES +" ("+ FIELD_TIMES_ID +") " +
            "ON DELETE CASCADE ON UPDATE NO ACTION, " +
            "FOREIGN KEY ("+ FIELD_PARTIDAS_IDTIMEB +") REFERENCES "+ TABELA_TIMES +" ("+ FIELD_TIMES_ID +") " +
            "ON DELETE CASCADE ON UPDATE NO ACTION" +
            ")";

    String createGols = "CREATE TABLE "+ TABELA_GOLS +" (" +
            FIELD_GOLS_IDPARTIDA +" integer, " +
            FIELD_GOLS_IDJOGADOR +" integer, " +
            FIELD_GOLS_QTDGOLS +" integer, " +
            "PRIMARY KEY ("+ FIELD_GOLS_IDPARTIDA +", "+ FIELD_GOLS_IDJOGADOR +"), " +
            "FOREIGN KEY ("+ FIELD_GOLS_IDPARTIDA +") REFERENCES "+ TABELA_PARTIDAS +" ("+ FIELD_PARTIDAS_ID +") " +
            "ON DELETE CASCADE ON UPDATE NO ACTION, " +
            "FOREIGN KEY ("+ FIELD_GOLS_IDJOGADOR +") REFERENCES "+ TABELA_JOGADORES +" ("+ FIELD_JOGADORES_ID +") " +
            "ON DELETE CASCADE ON UPDATE NO ACTION" +
            ")";

    @Override
    public synchronized void close() {
        super.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createAmigos);
        db.execSQL(createTimes);
        db.execSQL(createJogadores);
        db.execSQL(createPartidas);
        db.execSQL(createGols);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE " + TABELA_AMIGOS);
        db.execSQL("DROP TABLE " + TABELA_TIMES);
        db.execSQL("DROP TABLE " + TABELA_JOGADORES);
        db.execSQL("DROP TABLE " + TABELA_PARTIDAS);
        db.execSQL("DROP TABLE " + TABELA_GOLS);

        onCreate(db);

    }
}
