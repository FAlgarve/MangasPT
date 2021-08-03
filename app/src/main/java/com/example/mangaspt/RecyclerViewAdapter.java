package com.example.mangaspt;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    private Context context;
    private List<Utilizadores> user;

    RecyclerViewAdapter(Context context, List<Utilizadores> users) {
        this.context = context;
        this.user = users;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.layout_listitem,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final UtilizadoresDAO utilizadoresDAO;
        utilizadoresDAO = new UtilizadoresDAO(context);
        final Utilizadores utilizadores = user.get(position);

        holder.username_txt.setText(utilizadores.getUser());

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
                                int resultado = dbHelper.deleteUser(utilizadores.getId());
                                if (resultado > 0){
                                    Toast.makeText(context,"Utilizador eliminado com sucesso",Toast.LENGTH_SHORT).show();
                                    user.remove(utilizadores);
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
                Intent alterar = new Intent(context,EditarActivity.class);
                alterar.putExtra("Username",utilizadores.getUser());
                alterar.putExtra("Email",utilizadores.getEmail());
                alterar.putExtra("password",utilizadores.getPassword());
                alterar.putExtra("telem",utilizadores.getTelem());

                alterar.putExtra("position",position);
                context.startActivity(alterar);
            }
        });
    }

    @Override
    public int getItemCount() {
        return user.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView username_txt;
        ImageView delete, editar;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            username_txt = itemView.findViewById(R.id.txt_user_delete);
            editar = itemView.findViewById(R.id.edit_user);
            delete = itemView.findViewById(R.id.delete_img);
        }
    }
}