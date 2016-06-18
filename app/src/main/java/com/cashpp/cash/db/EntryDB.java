package com.cashpp.cash.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import com.cashpp.cash.model.Entry;

public class EntryDB {
    private DatabaseHelper databaseHelper;
    private SQLiteDatabase db;

    public EntryDB(Context context) {
        databaseHelper = new DatabaseHelper(context);
    }

    private SQLiteDatabase getDatabase() {
        if (db == null) {
            db = databaseHelper.getWritableDatabase();
        }

        return db;
    }

    private Entry createEntry(Cursor cursor) {
        Entry model = new Entry(
                cursor.getInt(cursor.getColumnIndex(DatabaseHelper.Entries._ID)),
                cursor.getString(cursor.getColumnIndex(DatabaseHelper.Entries.TITLE)),
                cursor.getDouble(cursor.getColumnIndex(DatabaseHelper.Entries.VALUE)),
                cursor.getString(cursor.getColumnIndex(DatabaseHelper.Entries.TYPE)),
                cursor.getString(cursor.getColumnIndex(DatabaseHelper.Entries.DATE)),
                cursor.getInt(cursor.getColumnIndex(DatabaseHelper.Entries.RECURRENCE)),
                cursor.getInt(cursor.getColumnIndex(DatabaseHelper.Entries.CATEGORY_ID))
        );

        return model;
    }

    public List<Entry> listEntries() {
        Cursor cursor = getDatabase().query(DatabaseHelper.Entries.TABLE,
                DatabaseHelper.Entries.COLUMNS, null, null, null, null, null);

        List<Entry> entries = new ArrayList<Entry>();

        while (cursor.moveToNext()) {
            Entry model = createEntry(cursor);
            entries.add(model);
        }

        cursor.close();

        return entries;
    }

    public long saveEntry(Entry model) {
        //Cria um ContentValues e relaciona o nome do campo com o seu novo valor
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.Entries.TITLE, model.getTitle());
        values.put(DatabaseHelper.Entries.VALUE, model.getValue());
        values.put(DatabaseHelper.Entries.TYPE, model.getType());
        values.put(DatabaseHelper.Entries.DATE, model.getDate());
        values.put(DatabaseHelper.Entries.RECURRENCE, model.getRecurrence());
        values.put(DatabaseHelper.Entries.CATEGORY_ID, model.getCategory_id());

        //Caso o registro já exista, atualiza; senão, insere um novo
        if (model.get_id() != null) {
            return getDatabase().update(DatabaseHelper.Entries.TABLE, values,
                    "_id = ?", new String[]{model.get_id().toString()});
        } else {
            return getDatabase().insert(DatabaseHelper.Entries.TABLE, null, values);
        }
    }

    public boolean removeEntry(int id) {
        return getDatabase().delete(DatabaseHelper.Entries.TABLE,
                "_id = ?", new String[]{Integer.toString(id)}) > 0;
    }

    public Entry findEntryById(int id) {
        Cursor cursor = getDatabase().query(DatabaseHelper.Entries.TABLE,
                DatabaseHelper.Entries.COLUMNS, "_id = ?", new String[]{Integer.toString(id)},
                null, null, null);

        if (cursor.moveToNext()) {
            Entry model = createEntry(cursor);
            cursor.close();
            return model;
        }

        return null;
    }

    public void close() {
        databaseHelper.close();
        db = null;
    }

}