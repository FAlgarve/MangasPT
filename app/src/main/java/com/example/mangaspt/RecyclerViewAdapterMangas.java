package com.example.mangaspt;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewAdapterMangas extends RecyclerView.Adapter<RecyclerViewAdapterMangas.ViewHolder> {

    private Context context;
    private List<Mangas> mangas;

    RecyclerViewAdapterMangas(Context context, List<Mangas> mangas) {
        this.context = context;
        this.mangas = mangas;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.layout_listitem_manga,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final MangasDAO mangasDAO;
        mangasDAO = new MangasDAO(context);
        final Mangas mangas_mangas = mangas.get(position);

        holder.manga_txt.setText(mangas_mangas.getTitulo());

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(v.getContext())
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Eliminar")
                        .setMessage("Quer eliminar este utilizador")
                        .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                DBHelper dbHelper = new DBHelper(context);
                                int resultado = dbHelper.deleteManga(mangas_mangas.getId());
                                if (resultado > 0){
                                    Toast.makeText(context,"Utilizador eliminado com sucesso",Toast.LENGTH_SHORT).show();
                                    mangas.remove(mangas_mangas);
                                    notifyDataSetChanged();
                                }
                            }
                        })
                        .setNegativeButton("Não",null)
                        .show();
            }
        });

        holder.editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent alterar = new Intent(context, MainEditManga.class);
                alterar.putExtra("Titulo",mangas_mangas.getTitulo());
                alterar.putExtra("Descricao",mangas_mangas.getDescricao());
                alterar.putExtra("Capitulos",mangas_mangas.getCapitulos());

                alterar.putExtra("position",position);
                context.startActivity(alterar);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mangas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView manga_txt;
        ImageView delete, editar;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            manga_txt = itemView.findViewById(R.id.txt_manga_recycler);
            editar = itemView.findViewById(R.id.edit_manga_recycler);
            delete = itemView.findViewById(R.id.delete_manga_recycler);
        }
    }
}