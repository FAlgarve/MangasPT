package com.example.mangaspt;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainPagActivity extends AppCompatActivity{

    private final BottomNavigationView.OnNavigationItemSelectedListener navListener = item -> {
        Fragment selectFragment = null;
        switch (item.getItemId()) {
            case R.id.homeFragment:
                selectFragment = new HomeFragment();
                break;
            case R.id.favFragment:
                selectFragment = new FavoritesFragment();
                break;
            case R.id.settingsFragment:
                selectFragment = new SettingsFragment();
                break;
        }
        Bundle bundle = new Bundle();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment, selectFragment).commit();
        return true;
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_pag);

        BottomNavigationView bottomnav = findViewById(R.id.bottom_nav_view);

        bottomnav.setOnNavigationItemSelectedListener(navListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment, new HomeFragment()).commit();

        getSupportFragmentManager().beginTransaction().replace(R.id.framelayout, new HomeFragment()).commit();
    }
}