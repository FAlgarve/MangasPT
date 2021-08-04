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

public class MangasProvider extends ContentProvider {
    public static final String DATABASENAME = "Mangas_PT";

    public static String AUTHORITY = "com.istec.turmaB";
    public static final Uri BASECONTENTURI = Uri.parse("content://" + AUTHORITY);

    public static final int MANGAS = 0;
    public static final int MANGA = 1;

    public static UriMatcher uriMatcher;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY, MangasContrato.PATH, MANGAS);
        uriMatcher.addURI(AUTHORITY, MangasContrato.PATH + "/#", MANGA);

    }

    public static class MangasContrato implements BaseColumns {

        public static final String PATH = "mangas";
        public static Uri CONTENTURI = BASECONTENTURI.buildUpon().appendPath(PATH).build();
        public static final String TABLENAME = "mangas";
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
            db.execSQL(MangasContrato.CriarTabela());
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL(MangasContrato.EliminarTabela());
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
            case MANGAS:
                cur = db.query(MangasContrato.TABLENAME, null, null, null, null, null, null);
                cur.setNotificationUri(getContext().getContentResolver(), uri);
                return cur;

            case MANGA:
                SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
                long id = ContentUris.parseId(uri);
                qb.appendWhere(MangasContrato.COL_1 + "=" + id);
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
            case MANGAS:
                long novo = db.insert(MangasContrato.TABLENAME, null, cv);
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
            case MANGAS:
                int total = db.delete(MangasContrato.TABLENAME, where, whereArgs);
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
            case MANGAS:
                int total = db.update(MangasContrato.TABLENAME, cv, where, whereArgs);
                if (total > 0){
                    getContext().getContentResolver().notifyChange(uri, null);
                }
                return total;
            default:
                throw new IllegalArgumentException("Erro Delete");
        }
    }
}