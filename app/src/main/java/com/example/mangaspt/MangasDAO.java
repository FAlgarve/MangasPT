package com.example.mangaspt;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class MangasDAO {
    private final SQLiteDatabase db;

    private final DBHelper dbhelper;

    private List<Mangas> mangas_list;

    private Mangas mangas;

    public MangasDAO(Context context) {
        dbhelper = new DBHelper(context);
        db = dbhelper.getWritableDatabase();
    }

    public List<Mangas> getMangas_list() {
        String comando_sql = "select * from mangas";
        mangas_list = new ArrayList<Mangas>();
        SQLiteDatabase db = dbhelper.getReadableDatabase();
        Cursor c = db.rawQuery(comando_sql, null);

        if (c.moveToFirst()) {
            do {

                mangas = new Mangas();

                mangas.setId(c.getInt(c.getColumnIndex("id")));
                mangas.setTitulo(c.getString(c.getColumnIndex("titulo")));
                mangas.setDescricao(c.getString(c.getColumnIndex("descricao")));
                mangas.setCapitulos(c.getString(c.getColumnIndex("capitulos")));
                mangas.setCapa((byte) c.getInt(c.getColumnIndex("capa")));

                mangas_list.add(mangas);
            } while (c.moveToNext());
        }
        return mangas_list;
    }
}