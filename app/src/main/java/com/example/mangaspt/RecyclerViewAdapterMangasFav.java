package com.example.mangaspt;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewAdapterMangasFav extends RecyclerView.Adapter<RecyclerViewAdapterMangasFav.ViewHolder> {

    List<Mangas> titulos_fav;
    //Context context;
    LayoutInflater inflater;

    public RecyclerViewAdapterMangasFav(Context context, List<Mangas> titulos_fav){
        //this.context = context;
        this.titulos_fav = titulos_fav;
        this.inflater = LayoutInflater.from(context);

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.layout_carditem, parent, false);
        return new RecyclerViewAdapterMangasFav.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Mangas mangas_mangas = titulos_fav.get(position);
        holder.titulo_fav.setText(mangas_mangas.getTitulo());
    }

    @Override
    public int getItemCount() {
        return titulos_fav.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView titulo_fav;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titulo_fav = itemView.findViewById(R.id.card_titulo);
        }
    }
}
