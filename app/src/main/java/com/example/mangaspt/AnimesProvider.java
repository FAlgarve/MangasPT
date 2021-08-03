package com.example.mangaspt;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.provider.BaseColumns;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class AnimesProvider extends ContentProvider {
    public static final String DATABASENAME = "Animes.db";

    public static String AUTHORITY = "com.example.mangaspt";
    public static final Uri BASECONTENTURI = Uri.parse("content://" + AUTHORITY);

    public static final int ANIMES = 0;
    public static final int ANIME = 1;

    public static UriMatcher uriMatcher;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY, AnimesContrato.PATH, ANIMES);
        uriMatcher.addURI(AUTHORITY, AnimesContrato.PATH + "/#", ANIME);

    }

    public static class AnimesContrato implements BaseColumns {

        public static final String PATH = "animes";
        public static Uri CONTENTURI = BASECONTENTURI.buildUpon().appendPath(PATH).build();
        public static final String TABLENAME = "animes";
        public static final String COL_1 = "_ID";
        public static final String COL_2 = "titulo";
        public static final String COL_3 = "descricao";
        public static final String COL_4 = "episodios";
        public static final String COL_5 = "foto";

        public static String CriarTabela() {
            return "create Table " + TABLENAME + "("
                    + COL_1 + " integer primary key, "
                    + COL_2 + " TEXT, "
                    + COL_3 + " TEXT, "
                    + COL_4 + " integer, "
                    + COL_5 + " BLOB)";
        }

        public static String EliminarTabela() {
            return "DROP TALBE IF EXISTS" + TABLENAME;
        }
    }

    public class MyBD extends SQLiteOpenHelper {

        public MyBD(@Nullable Context context) {
            super(context, DATABASENAME, null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(AnimesContrato.CriarTabela());
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL(AnimesContrato.EliminarTabela());
            onCreate(db);
        }
    }


    SQLiteDatabase db;

    @Override
    public boolean onCreate() {

        MyBD myBD = new MyBD(getContext());
        db = myBD.getWritableDatabase();
        return (db == db) ? false : true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        Cursor cur = null;
        switch (uriMatcher.match(uri)) {
            case ANIMES:
                cur = db.query(AnimesContrato.TABLENAME, null, null, null, null, null, null);
                cur.setNotificationUri(getContext().getContentResolver(), uri);
                return cur;

            case ANIME:
                SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
                long id = ContentUris.parseId(uri);
                qb.appendWhere(AnimesContrato.COL_1 + "=" + id);
                cur = qb.query(db, null, null, null, null, null, null);
                cur.setNotificationUri(getContext().getContentResolver(), uri);
                return cur;

            default:
                throw new IllegalArgumentException("Erro! Uri Errada");
        }
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues cv) {
        switch (uriMatcher.match(uri)) {
            case ANIMES:
                long novo = db.insert(AnimesContrato.TABLENAME, null, cv);
                Uri _uri = ContentUris.withAppendedId(uri, novo);
                getContext().getContentResolver().notifyChange(_uri, null);
                return _uri;
            default:
                throw new IllegalArgumentException("Erro Insert");
        }
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String where, @Nullable String[] whereArgs) {
        switch (uriMatcher.match(uri)) {
            case ANIMES:
                int total = db.delete(AnimesContrato.TABLENAME, where, whereArgs);
                if (total > 0){
                    getContext().getContentResolver().notifyChange(uri, null);
                }
                return total;
            default:
                throw new IllegalArgumentException("Erro Delete");
        }
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues cv, @Nullable String where, @Nullable String[] whereArgs) {
        switch (uriMatcher.match(uri)) {
            case ANIMES:
                int total = db.update(AnimesContrato.TABLENAME, cv, where, whereArgs);
                if (total > 0){
                    getContext().getContentResolver().notifyChange(uri, null);
                }
                return total;
            default:
                throw new IllegalArgumentException("Erro Delete");
        }
    }
}
