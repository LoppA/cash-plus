package com.cashpp.cash.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper{
    public static final String TAG = "sql";

    //Nome do banco e sua versão
    private static final String DATABASE_NAME = "cashpp.sqlite";
    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        //context, nome do banco, factory e versão
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG, "Creating tables...");

        //Tabela de entradas(receitas/despesas)
        String sql = "CREATE TABLE entries("
                + "_id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "title TEXT NOT NULL,"
                + "value DECIMAL(19,2) NOT NULL,"
                + "type TEXT NOT NULL,"
                + "date TEXT NOT NULL,"
                + "recurrence INTEGER,"
                + "category_id INTEGER NOT NULL,"
                + ")";

        db.execSQL(sql);

        //Tabela de categorias de entradas
        sql = "CREATE TABLE categories("
                + "_id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "title TEXT NOT NULL,"
                + ")";

        db.execSQL(sql);

        //Tabela de metas
        sql = "CREATE TABLE goals("
                + "_id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "title TEXT NOT NULL,"
                + "value DECIMAL(19,2) NOT NULL,"
                + "date TEXT NOT NULL,"
                + ")";

        db.execSQL(sql);

        //Tabela de lembretes
        sql = "CREATE TABLE reminders("
                + "_id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "title TEXT NOT NULL,"
                + "value DECIMAL(19,2) NOT NULL,"
                + "date TEXT NOT NULL,"
                + "recurrence INTEGER,"
                + ")";

        db.execSQL(sql);

        Log.d(TAG, "Tables created successfully.");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Caso a versão do banco de dados mude, executar um SQL aqui
    }

    public static class Entries {
        public static final String TABLE = "entries";
        public static final String _ID = "_id";
        public static final String TITLE = "title";
        public static final String VALUE = "value";
        public static final String TYPE = "type";
        public static final String DATE = "date";
        public static final String RECURRENCE = "recurrence";
        public static final String CATEGORY_ID = "category_id";

        public static final String[] COLUMNS = new String[] {
                _ID, TITLE, VALUE, TYPE, DATE, RECURRENCE, CATEGORY_ID
        };
    }

    public static class Categories {
        public static final String TABLE = "categories";
        public static final String _ID = "_id";
        public static final String TITLE = "title";

        public static final String[] COLUMNS = new String[] {
                _ID, TITLE
        };
    }

    public static class Goals {
        public static final String TABLE = "goals";
        public static final String _ID = "_id";
        public static final String TITLE = "title";
        public static final String VALUE = "value";
        public static final String DATE = "date";

        public static final String[] COLUMNS = new String[] {
                _ID, TITLE, VALUE, DATE
        };
    }

    public static class Reminders {
        public static final String TABLE = "reminders";
        public static final String _ID = "_id";
        public static final String TITLE = "title";
        public static final String VALUE = "value";
        public static final String DATE = "date";
        public static final String RECURRENCE = "recurrence";

        public static final String[] COLUMNS = new String[] {
                _ID, TITLE, VALUE, DATE, RECURRENCE
        };
    }
    
}
