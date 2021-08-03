package com.example.mangaspt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditarActivity extends AppCompatActivity {

    String nome,email,password,telem;
    EditText nomeTxt,emailTxt,telemTxt,passwordTxt;
    DBHelper dbHelper;
    Button atualizar;
    Utilizadores utilizadores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar);

        dbHelper = new DBHelper(this);

        nome = getIntent().getStringExtra("Username");
        email = getIntent().getStringExtra("Email");
        telem = getIntent().getStringExtra("telemovel");
        password = getIntent().getStringExtra("password");


        final int userPos = getIntent().getExtras().getInt("position");
        utilizadores = new UtilizadoresDAO(getApplicationContext()).getUsers().get(userPos);

        nomeTxt = findViewById(R.id.user_edit_text);
        emailTxt = findViewById(R.id.user_edit_email);
        telemTxt = findViewById(R.id.user_edit_telem);
        passwordTxt = findViewById(R.id.user_edit_pass);
        atualizar = findViewById(R.id.btn_edit);

        nomeTxt.setText(nome);
        emailTxt.setText(email);
        passwordTxt.setText(password);
        telemTxt.setText(telem);


        atualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                utilizadores.setUser(nomeTxt.getText().toString());
                utilizadores.setEmail(emailTxt.getText().toString());
                utilizadores.setTelem(Integer.parseInt(telemTxt.getText().toString()));
                utilizadores.setPassword(passwordTxt.getText().toString());

                Toast.makeText(getApplicationContext(),"User atualizado com sucesso",Toast.LENGTH_SHORT).show();

                dbHelper.editUser(utilizadores);
                Intent voltar = new Intent(EditarActivity.this,MainPagActivity.class);
                startActivity(voltar);
            }
        });
    }
}