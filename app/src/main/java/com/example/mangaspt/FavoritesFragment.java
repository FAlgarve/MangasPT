package com.example.mangaspt;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FavoritesFragment extends Fragment {

    DBHelper db;
    MangasDAO mangasDAO;
    List<Mangas> mangas_list;
    RecyclerView mangaList;
    RecyclerViewAdapterMangasFav adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorites, container, false);
        return inflater.inflate(R.layout.fragment_favorites, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        db = new DBHelper(FavoritesFragment.super.getActivity());

        mangasDAO = new MangasDAO(FavoritesFragment.super.getActivity());

        mangaList = view.findViewById(R.id.manga_fav_recycler);

        mangas_list = mangasDAO.getMangas_list();

        adapter = new RecyclerViewAdapterMangasFav(FavoritesFragment.super.getActivity(), mangas_list);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(FavoritesFragment.super.getActivity(), 2, RecyclerView.VERTICAL, false);

        mangaList.setLayoutManager(gridLayoutManager);
        mangaList.setAdapter(adapter);

    }
}
