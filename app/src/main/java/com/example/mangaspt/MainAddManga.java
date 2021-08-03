package com.example.mangaspt;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainAddManga extends AppCompatActivity {

    Button adicionar;

    ImageView capa;

    DBHelper DB;

    EditText addmanga_txt, editmanga_txt, deletemanga_txt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main_add_manga);

        addmanga_txt = findViewById(R.id.add_titulo_manga);
        editmanga_txt = findViewById(R.id.add_descricao_manga);
        deletemanga_txt = findViewById(R.id.add_capitulo_manga);

        DB = new DBHelper(this);

        adicionar = findViewById(R.id.btn_add_manga);

        adicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String titulo = addmanga_txt.getText().toString();
                String descricao = editmanga_txt.getText().toString();
                String capitulos = deletemanga_txt.getText().toString();


                if(titulo.equals("")||descricao.equals("")||capitulos.equals(""))
                    Toast.makeText(MainAddManga.this, "Por favor Preencha todos os campos", Toast.LENGTH_SHORT).show();
                else{
                        Boolean checkuser = DB.checkmanga(titulo);
                        if(checkuser==false){
                            Boolean insert = DB.insertManga(titulo, descricao, capitulos);
                            if(insert==true){
                                Toast.makeText(MainAddManga.this, "Manga Adicionado com Sucesso!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), MainPagActivity.class);
                                startActivity(intent);
                            }else{
                                Toast.makeText(MainAddManga.this, "Registo Falhou!", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            Toast.makeText(MainAddManga.this, "O Manga já existente!", Toast.LENGTH_SHORT).show();
                        }
                }
            }
        });

        capa = findViewById(R.id.ADD_capa);
        capa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(Intent.ACTION_GET_CONTENT);
                it.setType("image/*");
                startActivityForResult(it, GETCONTENT);
            }
        });
    }

    public static final int GETCONTENT = 1;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null && requestCode == GETCONTENT) {
            Uri uri = Uri.parse(data.getData().toString());
            capa.setImageURI(uri);
        }

    }
}