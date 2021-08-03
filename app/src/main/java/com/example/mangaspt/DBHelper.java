package com.example.mangaspt;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DBNAME = "Mangas_PT";

    public DBHelper(Context context) {
        super(context, DBNAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table users(id integer primary key, username TEXT, password TEXT, email TEXT, telem integer)");
        DB.execSQL("create Table mangas(id integer primary key, titulo TEXT, descricao TEXT, capitulos integer)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop Table if exists users");
    }

    public Boolean insertUsers(String username, String password, String email, String telem) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("username", username);
        cv.put("password", password);
        cv.put("email", email);
        cv.put("telem", telem);
        long result = DB.insert("users", null, cv);
        if (result == -1) return false;
        else
            return true;
    }

    public Boolean insertManga(String titulo, String descricao, String capitulos) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("titulo", titulo);
        cv.put("descricao", descricao);
        cv.put("capitulos", capitulos);
        long result = DB.insert("mangas", null, cv);
        if (result == -1) return false;
        else
            return true;
    }

    Cursor viewUsers() {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = null;
        if (db != null) {
            db.rawQuery("SELECT username FROM users", null);
        }
        return cursor;
    }

    Cursor viewMangas() {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = null;
        if (db != null) {
            db.rawQuery("SELECT username FROM mangas", null);
        }
        return cursor;
    }

    public void editUser(Utilizadores utilizadores) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("username", utilizadores.getUser());
        contentValues.put("email", utilizadores.getEmail());
        contentValues.put("telem", utilizadores.getTelem());
        contentValues.put("password", utilizadores.getPassword());

        db.update("users", contentValues, "id=" + utilizadores.getId(), null);
    }

    public void editManga(Mangas mangas) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("titulo", mangas.getTitulo());
        contentValues.put("descricao", mangas.getDescricao());
        contentValues.put("capitulos", mangas.getCapitulos());

        db.update("mangas", contentValues, "id=" + mangas.getId(), null);
    }

    public int deleteUser(int id) {
        SQLiteDatabase db = getWritableDatabase();

        return db.delete("users", "id=?", new String[]{String.valueOf(id)});
    }

    public int deleteManga(int id) {
        SQLiteDatabase db = getWritableDatabase();

        return db.delete("mangas", "id=?", new String[]{String.valueOf(id)});
    }

    public Boolean checkusername(String username) {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from users where username = ?", new String[]{username});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public Boolean checkmanga(String titulo) {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from mangas where titulo = ?", new String[]{titulo});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public Boolean checkusernamepassword(String username, String password) {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from users where username = ? and password = ?", new String[]{username, password});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }
}