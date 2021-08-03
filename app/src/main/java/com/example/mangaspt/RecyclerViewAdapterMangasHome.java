package com.example.mangaspt;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import static com.example.mangaspt.R.layout.layout_carditem;

public class RecyclerViewAdapterMangasHome extends RecyclerView.Adapter<RecyclerViewAdapterMangasHome.ViewHolder> {

    List<Mangas> titulos;
    //Context context;
    LayoutInflater inflater;

    public RecyclerViewAdapterMangasHome(Context context, List<Mangas> titulos){
        //this.context = context;
        this.titulos = titulos;
        this.inflater = LayoutInflater.from(context);

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.layout_carditem, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Mangas mangas_mangas = titulos.get(position);
        holder.titulo.setText(mangas_mangas.getTitulo());
    }

    @Override
    public int getItemCount() {
        return titulos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView titulo;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titulo = itemView.findViewById(R.id.card_titulo);
        }
    }
}