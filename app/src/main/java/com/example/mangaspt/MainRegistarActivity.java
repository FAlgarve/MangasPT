package com.example.mangaspt;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainRegistarActivity extends AppCompatActivity {

    ImageView voltar;
    Button registar;

    DBHelper DB;

    EditText user_registar, pass_registar, pass_registar_conf, email_registar, telem_registar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_registar);

        voltar = findViewById(R.id.back_button);

        voltar.setOnClickListener(v -> {
            Intent intent = new Intent(MainRegistarActivity.this, MainActivity.class);
            startActivity(intent);
        });

        user_registar = (EditText) findViewById(R.id.username_registar);
        pass_registar = (EditText) findViewById(R.id.password_registar);
        pass_registar_conf = (EditText) findViewById(R.id.password_registar_confirm);
        email_registar = (EditText) findViewById(R.id.email);
        telem_registar = (EditText) findViewById(R.id.telem);
        DB = new DBHelper(this);

        registar = findViewById(R.id.btn_registar);

        registar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = user_registar.getText().toString();
                String pass = pass_registar.getText().toString();
                String repass = pass_registar_conf.getText().toString();
                String email = email_registar.getText().toString();
                String telem = telem_registar.getText().toString();

                if(user.equals("")||pass.equals("")||repass.equals(""))
                    Toast.makeText(MainRegistarActivity.this, "Por favor Preencha todos os campos", Toast.LENGTH_SHORT).show();
                else{
                    if(pass.equals(repass)){
                        Boolean checkuser = DB.checkusername(user);
                        if(checkuser==false){
                            Boolean insert = DB.insertUsers(user, pass, email, telem);
                            if(insert==true){
                                Toast.makeText(MainRegistarActivity.this, "Registado com Sucesso!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
                                startActivity(intent);
                            }else{
                                Toast.makeText(MainRegistarActivity.this, "Registo Falhou!", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            Toast.makeText(MainRegistarActivity.this, "Nome Utilizador já existente!", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(MainRegistarActivity.this, "As Passwords devem ser iguais.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}