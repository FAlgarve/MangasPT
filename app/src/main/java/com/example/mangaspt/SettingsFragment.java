package com.example.mangaspt;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SettingsFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        CardView edit_user = (CardView) view.findViewById(R.id.edit_user);

        CardView add_manga = (CardView) view.findViewById(R.id.add_manga_card);
        CardView delete_manga = (CardView) view.findViewById(R.id.delete_manga_card);

        //edit_user.setOnClickListener(new View.OnClickListener() {
            //@Override
            //public void onClick(View v) {
              //  Intent intent = new Intent(SettingsFragment.super.getActivity() , MainPagActivity.class);
            //    startActivity(intent);
          //  }
        //});

        edit_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingsFragment.super.getActivity() , MainDeleteUser.class);
                startActivity(intent);
            }
        });

        add_manga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingsFragment.super.getActivity() , MainAddManga.class);
                startActivity(intent);
            }
        });

        delete_manga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingsFragment.super.getActivity() , MainDeleteManga.class);
                startActivity(intent);
            }
        });

    }
}
