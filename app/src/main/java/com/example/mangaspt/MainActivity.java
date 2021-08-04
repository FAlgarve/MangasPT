package com.example.mangaspt;

import android.content.Intent;
import android.media.MediaPlayer;
import android.media.audiofx.BassBoost;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button login;
    TextView registar;
    EditText username, password;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DB = new DBHelper(this);
        DB.getReadableDatabase();

        username = (EditText) findViewById(R.id.user_login);
        password = (EditText) findViewById(R.id.password_login);

        login = findViewById(R.id.login);

        login.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, MainPagActivity.class);
            startActivity(intent);
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String user = username.getText().toString();
                String pass = password.getText().toString();

                if(user.equals("")||pass.equals(""))
                    Toast.makeText(MainActivity.this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show();
                else{
                    Boolean checkuserpass = DB.checkusernamepassword(user, pass);
                    if(checkuserpass == true){
                        Toast.makeText(MainActivity.this, "Login feito com sucesso!", Toast.LENGTH_SHORT).show();

                        Intent intent  = new Intent(getApplicationContext(), MainPagActivity.class);
                        startActivity(intent);

                    }else{
                        Toast.makeText(MainActivity.this, "Credenciais Invalidas!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        registar = findViewById(R.id.registar);

        registar.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, MainRegistarActivity.class);
            startActivity(intent);
        });

        MediaPlayer player = MediaPlayer.create(this, R.raw.music);
        player.setLooping(true);
        player.start();
    }
}