package com.example.mangaspt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainEditManga extends AppCompatActivity {

    String titulo, descricao, capitulos;
    int img;
    EditText tituloTxt, descricaoTxt, capitulosTxt;
    DBHelper dbHelper;
    Button atualizar;
    Mangas mangas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_edit_manga);

        dbHelper = new DBHelper(this);

        titulo = getIntent().getStringExtra("Titulo");
        descricao = getIntent().getStringExtra("Descricao");
        capitulos = getIntent().getStringExtra("Capitulos");

        final int mangaPos = getIntent().getExtras().getInt("position");
        mangas = new MangasDAO(getApplicationContext()).getMangas_list().get(mangaPos);

        tituloTxt = findViewById(R.id.edit_titulo_manga);
        descricaoTxt = findViewById(R.id.edit_descricao_manga);
        capitulosTxt = findViewById(R.id.edit_capitulo_manga);
        atualizar = findViewById(R.id.btn_edit_manga);

        tituloTxt.setText(titulo);
        descricaoTxt.setText(descricao);
        capitulosTxt.setText(capitulos);

        atualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mangas.setTitulo(tituloTxt.getText().toString());
                mangas.setDescricao(descricaoTxt.getText().toString());
                mangas.setCapitulos(capitulosTxt.getText().toString());

                Toast.makeText(getApplicationContext(),"Manga atualizado com sucesso",Toast.LENGTH_SHORT).show();

                dbHelper.editManga(mangas);
                Intent voltar = new Intent(MainEditManga.this, MainPagActivity.class);
                startActivity(voltar);
            }
        });
    }
}