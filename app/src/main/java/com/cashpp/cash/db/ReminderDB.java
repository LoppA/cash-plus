package com.cashpp.cash.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import model.Reminder;

public class ReminderDB {
    private DatabaseHelper databaseHelper;
    private SQLiteDatabase db;

    public ReminderDB(Context context) {
        databaseHelper = new DatabaseHelper(context);
    }

    private SQLiteDatabase getDatabase() {
        if (db == null) {
            db = databaseHelper.getWritableDatabase();
        }

        return db;
    }

    private Reminder createReminder(Cursor cursor) {
        Reminder model = new Reminder(
                cursor.getInt(cursor.getColumnIndex(DatabaseHelper.Reminders._ID)),
                cursor.getString(cursor.getColumnIndex(DatabaseHelper.Reminders.TITLE)),
                cursor.getDouble(cursor.getColumnIndex(DatabaseHelper.Reminders.VALUE)),
                cursor.getString(cursor.getColumnIndex(DatabaseHelper.Reminders.DATE)),
                cursor.getInt(cursor.getColumnIndex(DatabaseHelper.Reminders.RECURRENCE))
        );

        return model;
    }

    public List<Reminder> listReminders() {
        Cursor cursor = getDatabase().query(DatabaseHelper.Reminders.TABLE,
                DatabaseHelper.Reminders.COLUMNS, null, null, null, null, null);

        List<Reminder> Reminders = new ArrayList<Reminder>();

        while (cursor.moveToNext()) {
            Reminder model = createReminder(cursor);
            Reminders.add(model);
        }

        cursor.close();

        return Reminders;
    }

    public long saveReminder(Reminder model) {
        //Cria um ContentValues e relaciona o nome do campo com o seu novo valor
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.Reminders.TITLE, model.getTitle());
        values.put(DatabaseHelper.Reminders.VALUE, model.getValue());
        values.put(DatabaseHelper.Reminders.DATE, model.getDate());
        values.put(DatabaseHelper.Reminders.RECURRENCE, model.getRecurrence());

        //Caso o registro já exista, atualiza; senão, insere um novo
        if (model.get_id() != null) {
            return getDatabase().update(DatabaseHelper.Reminders.TABLE, values,
                    "_id = ?", new String[] { model.get_id().toString() });
        } else {
            return getDatabase().insert(DatabaseHelper.Reminders.TABLE, null, values);
        }
    }

    public boolean removeReminder(int id) {
        return getDatabase().delete(DatabaseHelper.Reminders.TABLE,
                "_id = ?", new String[]{Integer.toString(id)}) > 0;
    }

    public Reminder findReminderById(int id) {
        Cursor cursor = getDatabase().query(DatabaseHelper.Reminders.TABLE,
                DatabaseHelper.Reminders.COLUMNS, "_id = ?", new String[]{Integer.toString(id)},
                null, null, null);

        if (cursor.moveToNext()) {
            Reminder model = createReminder(cursor);
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