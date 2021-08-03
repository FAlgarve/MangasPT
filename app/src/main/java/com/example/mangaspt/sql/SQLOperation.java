package com.example.mangaspt.sql;

public class SQLOperation {

    private static final String TABLE_NAME = "user_table";

    public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME
            + "(Id integer primary key Autoincrement,"
            + "user text not null,"
            + "password text not null,"
            + "email email not null,"
            + "telem num )";
}
