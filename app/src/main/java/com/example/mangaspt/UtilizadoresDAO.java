package com.example.mangaspt;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.List;

public class UtilizadoresDAO {
    private final SQLiteDatabase db;

    private final DBHelper dbhelper;

    private List<Utilizadores> users;

    private Utilizadores utilizadores;

    public UtilizadoresDAO(Context context) {
        dbhelper = new DBHelper(context);
        db = dbhelper.getWritableDatabase();
    }

    public List<Utilizadores> getUsers() {
        String comando_sql = "select * from users";
        users = new ArrayList<Utilizadores>();
        SQLiteDatabase db = dbhelper.getReadableDatabase();
        Cursor c = db.rawQuery(comando_sql, null);

        if (c.moveToFirst()) {
            do {

                utilizadores = new Utilizadores();

                utilizadores.setId(c.getInt(c.getColumnIndex("id")));
                utilizadores.setUser(c.getString(c.getColumnIndex("username")));
                utilizadores.setEmail(c.getString(c.getColumnIndex("email")));
                utilizadores.setPassword(c.getString(c.getColumnIndex("password")));
                utilizadores.setTelem(c.getInt(c.getColumnIndex("telem")));

                users.add(utilizadores);
            } while (c.moveToNext());
        }
        return users;
    }
}