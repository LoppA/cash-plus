package com.cashpp.cash.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import model.Goal;

public class GoalDB {
    private DatabaseHelper databaseHelper;
    private SQLiteDatabase db;

    public GoalDB(Context context) {
        databaseHelper = new DatabaseHelper(context);
    }

    private SQLiteDatabase getDatabase() {
        if (db == null) {
            db = databaseHelper.getWritableDatabase();
        }

        return db;
    }

    private Goal createGoal(Cursor cursor) {
        Goal model = new Goal(
                cursor.getInt(cursor.getColumnIndex(DatabaseHelper.Goals._ID)),
                cursor.getString(cursor.getColumnIndex(DatabaseHelper.Goals.TITLE)),
                cursor.getDouble(cursor.getColumnIndex(DatabaseHelper.Goals.VALUE)),
                cursor.getString(cursor.getColumnIndex(DatabaseHelper.Goals.DATE))
        );

        return model;
    }

    public List<Goal> listGoals() {
        Cursor cursor = getDatabase().query(DatabaseHelper.Goals.TABLE,
                DatabaseHelper.Goals.COLUMNS, null, null, null, null, null);

        List<Goal> goals = new ArrayList<Goal>();

        while (cursor.moveToNext()) {
            Goal model = createGoal(cursor);
            goals.add(model);
        }

        cursor.close();

        return goals;
    }

    public long saveGoal(Goal model) {
        //Cria um ContentValues e relaciona o nome do campo com o seu novo valor
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.Goals.TITLE, model.getTitle());
        values.put(DatabaseHelper.Goals.VALUE, model.getValue());
        values.put(DatabaseHelper.Goals.DATE, model.getDate());

        //Caso o registro já exista, atualiza; senão, insere um novo
        if (model.get_id() != null) {
            return getDatabase().update(DatabaseHelper.Goals.TABLE, values,
                    "_id = ?", new String[] { model.get_id().toString() });
        } else {
            return getDatabase().insert(DatabaseHelper.Goals.TABLE, null, values);
        }
    }

    public boolean removeGoal(int id) {
        return getDatabase().delete(DatabaseHelper.Goals.TABLE,
                "_id = ?", new String[]{Integer.toString(id)}) > 0;
    }

    public Goal findGoalById(int id) {
        Cursor cursor = getDatabase().query(DatabaseHelper.Goals.TABLE,
                DatabaseHelper.Goals.COLUMNS, "_id = ?", new String[]{Integer.toString(id)},
                null, null, null);

        if (cursor.moveToNext()) {
            Goal model = createGoal(cursor);
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