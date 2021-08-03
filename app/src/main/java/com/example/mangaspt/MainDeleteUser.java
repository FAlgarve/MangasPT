package com.example.mangaspt;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

public class MainDeleteUser extends AppCompatActivity {

    DBHelper db;
    UtilizadoresDAO utilizadoresDAO;
    List<Utilizadores> usernames;
    RecyclerViewAdapter recyclerViewAdapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_delete_user);

        db = new DBHelper(this);

        utilizadoresDAO = new UtilizadoresDAO(MainDeleteUser.this);

        usernames = utilizadoresDAO.getUsers();

        recyclerView = findViewById(R.id.recycler_view);

        recyclerViewAdapter = new RecyclerViewAdapter(MainDeleteUser.this, usernames);

        RecyclerView.LayoutManager layoutManager =  new LinearLayoutManager(MainDeleteUser.this);

        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.setAdapter(recyclerViewAdapter);
    }
}