package com.example.mangaspt;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    private static final int splash_scren_time = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        new Handler().postDelayed(() -> {
            Intent homeIntent = new Intent(HomeActivity.this, MainActivity.class);
            startActivity(homeIntent);
            finish();
        }, splash_scren_time);

    }
}