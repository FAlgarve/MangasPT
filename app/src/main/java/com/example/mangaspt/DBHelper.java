package com.example.mangaspt;

import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.provider.BaseColumns;

import static com.example.mangaspt.MangasProvider.BASECONTENTURI;

public class DBHelper extends SQLiteOpenHelper implements BaseColumns {

    public static final String DBNAME = "Mangas_PT";
    public static final String TABLE_NAME_USERS= "users";
    public static final String TABLE_NAME_MANGAS= "manga";


    public static final String PATH = "manga";
    public static final Uri CONTENTURI = BASECONTENTURI.buildUpon().appendPath(PATH).build();
    public static String AUTHORITY = "com.istec.turmaB";
    public static final int MANGAS=0;
    public static final int MANGA=1;
    public static final int USERS = 3;
    public static final int USER = 4;
    public static UriMatcher uriMatcher;
    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY, MangasProvider.MangasContrato.PATH, MANGAS);
        uriMatcher.addURI(AUTHORITY, DBHelper.MangasContrato.PATH + "/#", MANGA);
        uriMatcher.addURI(AUTHORITY, DBHelper.UserContrato.PATH, USERS);
        uriMatcher.addURI(AUTHORITY, DBHelper.UserContrato.PATH + "/#", USER);
    }

    public static class MangasContrato implements BaseColumns {
        public static final String PATH = "manga";
        public static final Uri CONTENTURI = BASECONTENTURI.buildUpon().appendPath(PATH).build();
        public static final String TABLENAME = "mangas";
        public static final String COL_1 = "id";
        public static final String COL_2 = "titulo";
        public static final String COL_3 = "descricao";
        public static final String COL_4 = "capitulos";

        public static String CriarTabela() {

            String mysql = "CREATE TABLE " + TABLE_NAME_MANGAS + " (";
            mysql += COL_1 + " INTEGER  PRIMARY KEY AUTOINCREMENT, ";
            mysql += COL_2 + " VARCHAR  (80),";
            mysql += COL_3 + " VARCHAR  (180),";
            mysql += COL_4 + " INTEGER );";

            return mysql;
        }

        public static String EliminarTabela() {
            String mysql = "DROP TABLE IF EXISTS" + TABLE_NAME_MANGAS + ";";
            return mysql;
        }

    }
    public static class UserContrato implements BaseColumns {
        public static final String PATH = "users";
        public static final Uri CONTENTURI = BASECONTENTURI.buildUpon().appendPath(PATH).build();
        public static final String TABLENAME = "users";
        public static final String COL_1 = "id";
        public static final String COL_2 = "username";
        public static final String COL_3 = "password";
        public static final String COL_4 = "email";
        public static final String COL_5 = "telem";

        public static String CriarTabela() {

            String mysql = "CREATE TABLE " + TABLE_NAME_USERS + " (";
            mysql += COL_1 + " INTEGER  PRIMARY KEY AUTOINCREMENT, ";
            mysql += COL_2 + " VARCHAR  (80),";
            mysql += COL_3 + " VARCHAR  (80),";
            mysql += COL_4 + " VARCHAR  (80),";
            mysql += COL_5 + " INTEGER ); ";

            return mysql;
        }

        public static String EliminarTabela() {
            String mysql = "DROP TABLE IF EXISTS" + TABLE_NAME_USERS + ";";
            return mysql;
        }
    }

    public DBHelper(Context context) {
        super(context, DBNAME, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
    }
    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL(DBHelper.UserContrato.CriarTabela());
        DB.execSQL(DBHelper.MangasContrato.CriarTabela());
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL(DBHelper.UserContrato.EliminarTabela());
        MyDB.execSQL(DBHelper.MangasContrato.EliminarTabela());
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