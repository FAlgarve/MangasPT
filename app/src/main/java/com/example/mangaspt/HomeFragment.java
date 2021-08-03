package com.example.mangaspt;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class HomeFragment extends Fragment {
    DBHelper db;
    MangasDAO mangasDAO;
    List<Mangas> mangas_list;
    RecyclerView mangaList;
    RecyclerViewAdapterMangasHome adapter;
    



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        db = new DBHelper(HomeFragment.super.getActivity());

        mangasDAO = new MangasDAO(HomeFragment.super.getActivity());

        mangaList = view.findViewById(R.id.manga_recycler);

        mangas_list = mangasDAO.getMangas_list();

        adapter = new RecyclerViewAdapterMangasHome(HomeFragment.super.getActivity(), mangas_list);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(HomeFragment.super.getActivity(), 2, RecyclerView.VERTICAL, false);

        mangaList.setLayoutManager(gridLayoutManager);
        mangaList.setAdapter(adapter);



    }
}