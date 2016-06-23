package com.cashpp.cash.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import com.cashpp.cash.model.Category;

public class CategoryDB {
    private DatabaseHelper databaseHelper;
    private SQLiteDatabase db;

    public CategoryDB(Context context) {
        databaseHelper = new DatabaseHelper(context);
    }

    private SQLiteDatabase getDatabase() {
        if (db == null) {
            db = databaseHelper.getWritableDatabase();
        }

        return db;
    }

    private Category createCategory(Cursor cursor) {
        Category model = new Category(
                cursor.getInt(cursor.getColumnIndex(DatabaseHelper.Categories._ID)),
                cursor.getString(cursor.getColumnIndex(DatabaseHelper.Categories.TITLE))
        );

        return model;
    }

    public List<Category> listCategories() {
        Cursor cursor = getDatabase().query(DatabaseHelper.Categories.TABLE,
                DatabaseHelper.Categories.COLUMNS, null, null, null, null, null);

        List<Category> categories = new ArrayList<Category>();

        while (cursor.moveToNext()) {
            Category model = createCategory(cursor);
            categories.add(model);
        }

        cursor.close();

        return categories;
    }

    public long saveCategory(Category model) {
        //Cria um ContentValues e relaciona o nome do campo com o seu novo valor
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.Categories.TITLE, model.getTitle());

        //Caso o registro já exista, atualiza; senão, insere um novo
        if (model.get_id() != null) {
            return getDatabase().update(DatabaseHelper.Categories.TABLE, values,
                    "_id = ?", new String[] { model.get_id().toString() });
        } else {
            return getDatabase().insert(DatabaseHelper.Categories.TABLE, null, values);
        }
    }

    public boolean removeCategory(int id) {
        return getDatabase().delete(DatabaseHelper.Categories.TABLE,
                "_id = ?", new String[]{Integer.toString(id)}) > 0;
    }

    public Category findCategoryById(int id) {
        Cursor cursor = getDatabase().query(DatabaseHelper.Categories.TABLE,
                DatabaseHelper.Categories.COLUMNS, "_id = ?", new String[]{Integer.toString(id)},
                null, null, null);

        if (cursor.moveToNext()) {
            Category model = createCategory(cursor);
            cursor.close();
            return model;
        }

        return null;
    }

    public Cursor getCursorForSpinner() {
        if (db == null) db = databaseHelper.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT _id, title FROM categories", null);
        return c;
    }

    public void close() {
        databaseHelper.close();
        db = null;
    }
}