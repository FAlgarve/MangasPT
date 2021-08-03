package com.example.mangaspt;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.List;

public class MainDeleteManga extends AppCompatActivity {

    DBHelper db;
    MangasDAO mangasDAO;
    List<Mangas> mangasList;
    RecyclerViewAdapterMangas recyclerViewAdapterMangas;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_delete_manga);

        db = new DBHelper(this);

        mangasDAO = new MangasDAO(MainDeleteManga.this);

        mangasList = mangasDAO.getMangas_list();

        recyclerView = findViewById(R.id.recycler_view);

        recyclerViewAdapterMangas = new RecyclerViewAdapterMangas(MainDeleteManga.this, mangasList);

        RecyclerView.LayoutManager layoutManager =  new LinearLayoutManager(MainDeleteManga.this);

        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.setAdapter(recyclerViewAdapterMangas);
    }
}